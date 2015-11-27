package br.com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import br.com.model.Produto;
import br.com.model.Venda;
import br.com.regranegocio.ProdutoRN;
import br.com.regranegocio.VendaRN;

@ManagedBean
@Scope("request")
public class VendaBean {
	private Integer produtoId;
	private List<Produto> listProduto = new ArrayList<Produto>();
	private Venda venda = new Venda();
	
	@Autowired
	private VendaRN vendaRN;
	
	@Autowired
	private ProdutoRN produtoRN;
	
	public Venda getVenda() {
		return venda;
	}
	
	public void addProduto(){
		if (produtoId!=null && produtoId.intValue() != 0) {
			try {
				Produto produto = produtoRN.consultarPorID(produtoId);
				listProduto.add(produto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public List<Produto> getListProduto() {
		return listProduto;
	}

	public void setListProduto(List<Produto> listProduto) {
		this.listProduto = listProduto;
	} 
}
