package br.com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;

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
	
	@Autowired
	private VendaRN vendaRN;
	
	@ManagedProperty(name="produtoRN", value="#{produtoRN}")
	private ProdutoRN produtoRN;
	
	/**
	 * A cada novo produto adicionado na Venda, a lista de produtos da venda
	 * é incrementada com aquele produto.
	 * É também realizado o cálculo do valor parcial da compra.
	 */
	public void addProduto(){
		Produto produto = produtoRN.consultarPorID(produtoId);
		
		if (produto != null && produto.isAtivo()) {
			try {
				listProduto.add(produto);
				this.calcularTotal(produto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * O valor da venda do produto é calculado de forma dinâmica.
	 * A cada valor adicionada na lista de compras o valor total é incrementado.
	 */
	private void calcularTotal(Produto produto) {
		this.setTotalVenda(this.totalVenda + produto.getValor()); 
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
