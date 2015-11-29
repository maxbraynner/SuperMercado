package br.com.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.model.Cliente;
import br.com.model.Endereco;
import br.com.regranegocio.ClienteRN;
import br.com.util.JSFUtil;
import jdk.nashorn.internal.scripts.JS;

@ManagedBean(name = "clienteBean")
@RequestScoped
public class ClienteBean {
	private Cliente cliente = new Cliente();
	private Endereco endereco = new Endereco();
	private List<Cliente> listaClientes;

	// Lista usada para organização da tela listar de clientes
	private List<Cliente> clientesFiltrados;

	@ManagedProperty(name = "clienteRN", value = "#{clienteRN}")
	private ClienteRN clienteRN;

	// Salva um cliente no banco
	public String salvar() {
		try {
			// Cliente quando é cadastrado está automaticamente ativo no
			// sistema
			cliente.setEndereco(endereco);
			clienteRN.salvar(cliente);
			JSFUtil.adicionarMensagemSucesso("Cliente cadastrado com sucesso. ");
			return "/cliente/listar";
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar cadastrar cliente. ");
			return "/cliente/cadastrar";
		}
	}

	// Edição de cliente
	public String editar() {
		try {
			// O endereço é novamente instanciado para possibilitar a
			// exibição
			// em tela
			this.setEndereco(getCliente().getEndereco());
			this.getCliente();
			return "/cliente/cadastrar";
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar editar cliente. ");
			return "/cliente/listar";
		}

	}

	// Exclusão do cliente
	public String excluir() throws InstantiationException, IllegalAccessException {
		try {
			clienteRN.excluir(cliente);
			JSFUtil.adicionarMensagemSucesso("Cliente exclu�do com sucesso. ");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar excluir cliente. ");
		}
		return "/cliente/listar";
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
