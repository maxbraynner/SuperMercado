package br.com.dao;

import org.hibernate.Session;

import br.com.model.Produto;

/**
 * Classe responsável por acesso a entidade Produto 
 */
public class ProdutoDAO extends DaoGeneric<Produto> {
	
	public ProdutoDAO(Session session) {
		super(session);
	}
	
	public ProdutoDAO() {
		super();
	}
}
