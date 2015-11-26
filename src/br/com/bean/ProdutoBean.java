package br.com.bean;

import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.model.Fornecedor;
import br.com.model.Produto;
import br.com.regranegocio.FornecedorRN;
import br.com.regranegocio.ProdutoRN;

@ManagedBean
public class ProdutoBean {
	private Produto produto = new Produto();
	private String idFornecedor;
	private List<Produto> listProduto;
	private List<Produto> produtosFiltrados;
	private List<Fornecedor> listFornecedor;
	
	@Autowired
	private ProdutoRN produtoRN;
	
	@Autowired
	private FornecedorRN fornecedorRN; 
	
	/**
	 * Inseri ou altera produto
	 * @return
	 */
	public String salvar() {
		Fornecedor fornecedor = fornecedorRN.consultarPorId(Integer.parseInt(idFornecedor));
		
		produto.setFornecedor(fornecedor);
		produtoRN.salvar(produto);
		
		// limpa o produto para evitar erro no request
		produto = new Produto();
		fornecedor = new Fornecedor();
		
		// recarrega lista de produtos
		listProduto = produtoRN.listar();
		
		return "/produto/listar?faces-redirect=true";
	}
	
	/**
	 * Exclui o registro
	 */
	public void excluir(Produto produto) {
		produtoRN.excluir(produto);
		
		//consulta novamente para atualizar a listagem
		listProduto = produtoRN.listar();
	}
	
	public String exibirAlterar() {
		this.idFornecedor = produto.getFornecedor().getId().toString();
		
		return "/produto/cadastrar";
	}
	
	public void inativar() {
		produtoRN.inativar(produto);
	}
	
	//métodos de acesso
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setProdutoRN(ProdutoRN produtoRN) {
		this.produtoRN = produtoRN;
	}

	public List<Produto> getListProduto() {
		if (listProduto!=null) {
			return listProduto;
		}else{
			listProduto = produtoRN.listar();
			return listProduto;
		}
	}

	public void setListProduto(List<Produto> listProduto) {
		this.listProduto = listProduto;
	}

	public List<Fornecedor> getListFornecedor() {
		if(listFornecedor!=null){
			return listFornecedor;
		}else{
			listFornecedor = fornecedorRN.listar();
			return listFornecedor;
		}
	}

	public void setListFornecedor(List<Fornecedor> listFornecedor) {
		this.listFornecedor = listFornecedor;
	}

	public void setFornecedorRN(FornecedorRN fornecedorRN) {
		this.fornecedorRN = fornecedorRN;
	}

	public String getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(String idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}
}
