package supermercado.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import supermercado.cliente.Cliente;
import supermercado.cliente.ClienteRN;
import supermercado.endereco.Endereco;

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
