package br.com.bean;

import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.model.Venda;
import br.com.regranegocio.VendaRN;

@ManagedBean
public class VendaBean {
	private Venda venda = new Venda();
	
	@ManagedProperty(name="vendaRN", value="#{vendaRN}")
	private VendaRN vendaRN;
	
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
	
	
}
