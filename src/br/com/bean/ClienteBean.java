package br.com.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.model.Cliente;
import br.com.model.Endereco;
import br.com.regranegocio.ClienteRN;

@ManagedBean(name = "clienteBean")
@RequestScoped
public class ClienteBean {
	private Cliente cliente = new Cliente();
	private Endereco endereco = new Endereco();
	
	public void salvar() throws InstantiationException, IllegalAccessException {
		ClienteRN clienteRN = new ClienteRN();
		
		cliente.setEndereco(endereco);
		
		clienteRN.salvar(cliente);
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}
