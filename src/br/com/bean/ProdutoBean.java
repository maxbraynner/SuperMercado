package br.com.bean;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.model.Produto;
import br.com.regranegocio.ProdutoRN;

@ManagedBean
public class ProdutoBean {
	
	@ManagedProperty(name="produtoRN", value="#{produtoRN}")
	ProdutoRN produtoRN;
	Produto produto;
	List<Produto> listProduto;
	
	/**
	 * Inseri ou altera produto
	 * @return
	 */
	public String salvar() {
		produtoRN.salvar(produto);
		
		return "/produto/listar?faces-redirect=true";
	}
	
	/**
	 * Exclui o registro
	 */
	public void exluir() {
		produtoRN.excluir(produto);
		
		//consulta novamente para atualizar a listagem
		listProduto = produtoRN.listar();
	}
	
	public void inativar() {
		produtoRN.inativar(produto);
	}
	
	//métodos de acesso
	public ProdutoBean() {
		produto = new Produto();
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setProdutoRN(ProdutoRN produtoRN) {
		this.produtoRN = produtoRN;
	}
}
