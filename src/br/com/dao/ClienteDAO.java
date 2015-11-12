package br.com.dao;

import br.com.model.Cliente;

/**
 * Classe responsável por acesso a entidade Cliente 
 */
public class ClienteDAO extends DaoGeneric<Cliente> {
	
	public ClienteDAO() {
		super(Cliente.class);
	}
}
