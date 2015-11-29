package br.com.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.model.Cliente;
import br.com.model.Endereco;
import br.com.regranegocio.ClienteRN;

@ManagedBean(name = "clienteBean")
@RequestScoped
public class ClienteBean {
	private Cliente cliente = new Cliente();
	private Endereco endereco = new Endereco();
	private List<Cliente> listaClientes;
	
	// Lista usada para organização da tela listar de clientes
	private List<Cliente> clientesFiltrados;
	
	@ManagedProperty(name="clienteRN", value="#{clienteRN}")
	private ClienteRN clienteRN;
	
	// Salva um cliente no banco
	public String salvar() {
		// Cliente quando é cadastrado está automaticamente ativo no sistema
		cliente.setEndereco(endereco);
		
		clienteRN.salvar(cliente);
		return "/cliente/listar";
	}
	
	// Edição de cliente
	public String editar() {
		// O endereço é novamente instanciado para possibilitar a exibição em tela
		this.setEndereco(getCliente().getEndereco());
		this.getCliente();
		return "/cliente/cadastrar";
		
	}
	
	// Exclusão do cliente
	public void excluir() throws InstantiationException, IllegalAccessException {
		clienteRN.excluir(cliente);
	}
	
	// Ativação OU inativação de um cliente
	public void ativar() throws InstantiationException, IllegalAccessException {
		if (cliente.isAtivo()) {
			cliente.setAtivo(false);
		} else {
			cliente.setAtivo(true);
		}
		
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
	
	// Lista de todos os clientes cadastrados
	public List<Cliente> getListaClientes() throws InstantiationException, IllegalAccessException {
			this.listaClientes = clienteRN.listar();
		
		return this.listaClientes;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

	public void setClienteRN(ClienteRN clienteRN) {
		this.clienteRN = clienteRN;
	}

}
