package br.com.bean;

import java.util.List;

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
	private List<Cliente> listaClientes;
	
	// Salva um cliente no banco
	public String salvar() throws InstantiationException, IllegalAccessException {
		ClienteRN clienteRN = new ClienteRN();
		
		// Cliente quando é cadastrado está automaticamente ativo no sistema
		cliente.setAtivo(true);
		cliente.setEndereco(endereco);
		
		clienteRN.salvar(cliente);
		return "/cliente/listar?faces-redirect=true";
	}
	
	// Edição de cliente
	public String editar() {
		// O endereço é novamente instanciado para possibilitar a exibição em tela
		this.setEndereco(getCliente().getEndereco());
		
		return "/cliente/cadastrar?faces-redirect=true";
		
	}
	
	// Exclusão do cliente
	public void excluir() throws InstantiationException, IllegalAccessException {
		ClienteRN clienteRN = new ClienteRN();
		clienteRN.excluir(cliente);
		this.listaClientes = null;
	}
	
	// Ativação OU inativação de um cliente
	public void ativar() throws InstantiationException, IllegalAccessException {
		ClienteRN clienteRN = new ClienteRN();
		
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
		if (this.listaClientes == null) {
			ClienteRN clienteRN = new ClienteRN();
			this.listaClientes = clienteRN.listar();
		}
		return this.listaClientes;
	}

}
