package br.com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.model.Cliente;

/**
 * Classe responsável por acesso a entidade Cliente 
 */
@Repository
public class ClienteDAO extends DaoGeneric<Cliente> {
	
	@Autowired
	public ClienteDAO(SessionFactory sesseionFactory) {
		super(Cliente.class, sesseionFactory);
	}
}
