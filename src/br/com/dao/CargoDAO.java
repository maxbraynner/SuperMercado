package br.com.dao;

import org.hibernate.Session;

import br.com.model.Cargo;

/**
 * Classe responsável por acesso a entidade Cargo 
 */
public class CargoDAO extends DaoGeneric<Cargo> {
	
	public CargoDAO(Session session) {
		super(session);
	}
	
	public CargoDAO() {
		super();
	}
}
