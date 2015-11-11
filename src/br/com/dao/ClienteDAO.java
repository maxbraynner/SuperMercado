package br.com.dao;

import org.hibernate.Session;

import br.com.model.Cliente;

/**
 * Classe responsável por consultas referentes ao Cliente 
 */
public class ClienteDAO extends DaoGeneric<Cliente> {
	
	public ClienteDAO(Session session) {
		super(session);
	}
	
	public ClienteDAO() {
		super();
	}
}
