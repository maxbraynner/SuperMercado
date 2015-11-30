package br.com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.model.Cliente;
import br.com.model.Funcionario;
import br.com.model.Produto;
import br.com.model.TipoPagamento;
import br.com.model.Venda;
import br.com.regranegocio.ClienteRN;
import br.com.regranegocio.FuncionarioRN;
import br.com.regranegocio.ProdutoRN;
import br.com.regranegocio.VendaRN;
import br.com.util.JSFUtil;

@ManagedBean(name = "vendaBean")
@SessionScoped
public class VendaBean {
	private Integer produtoId;
	private List<Produto> listProduto = new ArrayList<Produto>();
	private Venda venda = new Venda();
	private Produto produto = new Produto();
	private double totalVenda;
	private List<TipoPagamento> listTipoPagamento;

	@ManagedProperty(name = "vendaRN", value = "#{vendaRN}")
	private VendaRN vendaRN;

	@ManagedProperty(name = "produtoRN", value = "#{produtoRN}")
	private ProdutoRN produtoRN;

	@ManagedProperty(name = "clienteRN", value = "#{clienteRN}")
	private ClienteRN clienteRN;
	
	@ManagedProperty(name = "funcionarioRN", value = "#{funcionarioRN}")
	private FuncionarioRN funcionarioRN;

	@PostConstruct
	public void init() {
		// preenche a lista de tipos de pagamento
		listTipoPagamento = new ArrayList<TipoPagamento>();
		listTipoPagamento.add(TipoPagamento.CRÉDITO);
		listTipoPagamento.add(TipoPagamento.DÉBITO);
		listTipoPagamento.add(TipoPagamento.DINHEIRO);
		listTipoPagamento.add(TipoPagamento.VALE);

		// inicializa o cliente
		venda.setCliente(new Cliente());
	}

	public void consultaCliente() {
		try {
			if (venda.getCliente().getCpf() != null && !venda.getCliente().getCpf().equals("___.___.___-__")) {
				Cliente cliente = clienteRN.consultaPorCPF(venda.getCliente().getCpf());

				if (cliente != null) {
					venda.setCliente(cliente);
				}
			} else {
				venda.setCliente(new Cliente());
			}
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Cliente não encontrado");
			venda.setCliente(new Cliente());
		}
	}

	/**
	 * A cada novo produto adicionado na Venda, a lista de produtos da venda é
	 * incrementada com aquele produto. É também realizado o cálculo do valor
	 * parcial da compra.
	 */
	public void adicionarProduto() {
		Produto produto = produtoRN.consultarPorID(produtoId);

		if (produto != null && produto.isAtivo()) {
			try {
				listProduto.add(produto);
				this.incrementarTotal(produto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * O produto é excluído da lista de venda. E um novo cálculo do valor total
	 * é realizado.
	 */
	public void deletarProduto() {
		if (this.produto != null) {
			try {
				listProduto.remove(produto);
				this.decrementarTotal(produto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * O valor da venda dos produtos é calculado de forma dinâmica. A cada
	 * produto adicionado na lista de compras o valor total é recalculado,
	 * ocorre uma adição no total.
	 */
	private void incrementarTotal(Produto produto) {
		this.setTotalVenda(this.totalVenda + produto.getValor());
	}

	/**
	 * O valor da venda dos produtos é calculado dinâmicamente. A cada produto
	 * excluído na lista de compras o valor total é recalculado, ocorre uma
	 * redução no total.
	 */
	private void decrementarTotal(Produto produto) {
		this.setTotalVenda(this.totalVenda - produto.getValor());
	}

	/**
	 * A sessão em que a venda está é finalizada.
	 */
	public void cancelarVenda() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("vendaBean");
	}

	/**
	 * A inserção de produtos na lista de produtos é finalizada. Os atributos de
	 * venda são instanciados e o pagamento é iniciado.
	 */
	public String iniciarPagamento() {
		// verifica se possui itens na venda
		if (this.listProduto.isEmpty()) {
			JSFUtil.adicionarMensagemErro("Venda não possui Itens");
			return "/venda/realizarVenda";
		}

		// seta a data da venda
		this.getVenda().setDataVenda((new Date(System.currentTimeMillis())));

		// adiciona todos os produtos
		this.getVenda().setProdutos(this.getListProduto());

		// seta valor total da venda
		this.getVenda().setValor(this.getTotalVenda());

		return "/venda/realizarPagamento";
	}

	public String realizarPagamento() {
		try {

			Funcionario funcionario = funcionarioRN.consularPorId(3);
			
			venda.setFuncionario(funcionario);
			vendaRN.realizarVenda(venda);

			// limpa sessão
			cancelarVenda();

			JSFUtil.adicionarMensagemSucesso("Venda Realizada com sucesso");
			return "/venda/realizarVenda";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao efetuar pagamento: " + e.getLocalizedMessage());
			return "/venda/realizarVenda";
		}
	}

	// métodos de acesso
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public VendaRN getVendaRN() {
		return vendaRN;
	}

	public void setVendaRN(VendaRN vendaRN) {
		this.vendaRN = vendaRN;
	}

	public void setProdutoRN(ProdutoRN produtoRN) {
		this.produtoRN = produtoRN;
	}

	public List<Produto> getListProduto() {
		return listProduto;
	}

	public void setListProduto(List<Produto> listProduto) {
		this.listProduto = listProduto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoRN getProdutoRN() {
		return produtoRN;
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public double getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(double totalVenda) {
		this.totalVenda = totalVenda;
	}

	public List<TipoPagamento> getListTipoPagamento() {
		return listTipoPagamento;
	}

	public void setListTipoPagamento(List<TipoPagamento> listTipoPagamento) {
		this.listTipoPagamento = listTipoPagamento;
	}

	public ClienteRN getClienteRN() {
		return clienteRN;
	}

	public void setClienteRN(ClienteRN clienteRN) {
		this.clienteRN = clienteRN;
	}

	public FuncionarioRN getFuncionarioRN() {
		return funcionarioRN;
	}

	public void setFuncionarioRN(FuncionarioRN funcionarioRN) {
		this.funcionarioRN = funcionarioRN;
	}

	
}
