package br.com.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.model.Fornecedor;
import br.com.regranegocio.FornecedorRN;

@ManagedBean
public class FornecedorBean {
	private Fornecedor fornecedor = new Fornecedor();
	private List<Fornecedor> listFornecedor;
	private List<Fornecedor> fornecedoresFiltrados;
	FornecedorRN fornecedorRN; 

	public FornecedorBean() throws InstantiationException, IllegalAccessException {
		fornecedorRN = new FornecedorRN();
	}
	
	public String salvar() throws InstantiationException, IllegalAccessException{		
		fornecedorRN.salvar(fornecedor);
		
		return "/fornecedor/listar?faces-redirect=true";
	}
	
	public List<Fornecedor> getListFornecedor() {
		if (listFornecedor == null) {
			listFornecedor = fornecedorRN.listar();
		}
		
		return listFornecedor;
	}

	public void setListFornecedor(List<Fornecedor> listFornecedor) {
		this.listFornecedor = listFornecedor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedoresFiltrados() {
		return fornecedoresFiltrados;
	}

	public void setFornecedoresFiltrados(List<Fornecedor> fornecedoresFiltrados) {
		this.fornecedoresFiltrados = fornecedoresFiltrados;
	}


}