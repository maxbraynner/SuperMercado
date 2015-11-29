package br.com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.model.Produto;
import br.com.model.Venda;
import br.com.regranegocio.ProdutoRN;
import br.com.regranegocio.VendaRN;

@ManagedBean(name = "vendaBean")
@SessionScoped
public class VendaBean {
	private Integer produtoId;
	private List<Produto> listProduto = new ArrayList<Produto>();
	private Venda venda = new Venda();
	private Produto produto = new Produto();
	private double totalVenda;
	
	@ManagedProperty(name="vendaRN", value="#{vendaRN}")
	private VendaRN vendaRN;
	
	@ManagedProperty(name="produtoRN", value="#{produtoRN}")
	private ProdutoRN produtoRN;
	
	/**
	 * A cada novo produto adicionado na Venda, a lista de produtos da venda
	 * é incrementada com aquele produto.
	 * É também realizado o cálculo do valor parcial da compra.
	 */
	public void adicionarProduto(){
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
	 * O produto é excluído da lista de venda.
	 * E um novo cálculo do valor total é realizado.
	 */
	public void deletarProduto() {
		if(this.produto != null) {
			try {
				listProduto.remove(produto);
				this.decrementarTotal(produto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * O valor da venda dos produtos é calculado de forma dinâmica.
	 * A cada produto adicionado na lista de compras o valor total é recalculado,
	 * ocorre uma adição no total.
	 */
	private void incrementarTotal(Produto produto) {
		this.setTotalVenda(this.totalVenda + produto.getValor()); 
	}
	
	/**
	 * O valor da venda dos produtos é calculado dinâmicamente.
	 * A cada produto excluído na lista de compras o valor total é recalculado,
	 * ocorre uma redução no total. 
	 */
	private void decrementarTotal(Produto produto) {
		this.setTotalVenda(this.totalVenda - produto.getValor());
	}
	
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
}
