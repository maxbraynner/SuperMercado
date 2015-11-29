package br.com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "pagamentoBean")
public class PagamentoBean {
	List<String> listaPagamentos = new ArrayList<String>();
	
	@ManagedProperty(name="vendaBean", value="#{vendaBean}")
	private VendaBean vendaBean;
	
	private String tipoPagamento;
	
	public List<String> getListaPagamentos() {
		this.listaPagamentos.add("Dinheiro");
		this.listaPagamentos.add("Cartão de Crédito");
		this.listaPagamentos.add("Cartão de Débito");
		this.listaPagamentos.add("Vale Alimentação");
		return listaPagamentos;
	}

	public VendaBean getVendaBean() {
		return vendaBean;
	}

	public void setVendaBean(VendaBean vendaBean) {
		this.vendaBean = vendaBean;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
}
