package br.com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.model.Produto;
import br.com.model.Venda;
import br.com.regranegocio.ProdutoRN;
import br.com.regranegocio.VendaRN;

@ManagedBean(name = "vendaBean")
@RequestScoped
public class VendaBean {
	private Integer produtoId;
	private List<Produto> listProduto = new ArrayList<Produto>();
	private Venda venda = new Venda();
	private Produto produto = new Produto();
	
	@Autowired
	private VendaRN vendaRN;
	
	@ManagedProperty(name="produtoRN", value="#{produtoRN}")
	private ProdutoRN produtoRN;
	
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
}
