package br.com.dao;

import org.hibernate.Session;

import br.com.model.Venda;

/**
 * Classe responsável por acesso a entidade Venda
 */
public class VendaDAO extends DaoGeneric<Venda> {
	
	public VendaDAO(Session session) {
		super(session);
	}
	
	public VendaDAO() {
		super();
	}
}
