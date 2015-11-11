package br.com.regranegocio;

import java.util.List;

import br.com.dao.ClienteDAO;
import br.com.model.Cliente;
import br.com.util.DAOFactory;

public class ClienteRN {
	
	private ClienteDAO clienteDAO;
	
	// Instanciação do DAOFactory a partir do construtor
	public ClienteRN() throws InstantiationException, IllegalAccessException { 
		DAOFactory factory = new DAOFactory(); 
		
		this.clienteDAO = (ClienteDAO) factory.getDao(ClienteDAO.class);
	}
	
	// Salva ou atualiza um cliente no banco de dados
	public void salvar(Cliente cliente) {
		Integer id = cliente.getId();
		
		if (id == null || id == 0) {
			this.clienteDAO.inserir(cliente);
		} else {
			this.clienteDAO.alterar(cliente);
		}
	}
	
	// Realiza a listagem de todos clientes cadastrados no banco de dados
	public List<Cliente> listar() {
		return clienteDAO.listar(Cliente.class);
	}
	
	// Realiza a exclusão de um cliente do banco de dados
	public void excluir(Cliente cliente) {
		clienteDAO.remover(cliente);
	}
}
