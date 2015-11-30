package br.com.regranegocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dao.ClienteDAO;
import br.com.model.Cliente;

@Repository("clienteRN")
@Transactional
public class ClienteRN {
	
	private ClienteDAO clienteDAO;
	
	@Autowired	
	public ClienteRN(ClienteDAO clienteDAO){ 
		this.clienteDAO = clienteDAO;
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
		return clienteDAO.listar();
	}
	
	// Realiza a exclusão de um cliente do banco de dados
	public void excluir(Cliente cliente) {
		clienteDAO.remover(cliente);
	}
	
	public Cliente consultaPorCPF(String cpf) {
		return clienteDAO.consultaPorCPF(cpf);
	}
}
