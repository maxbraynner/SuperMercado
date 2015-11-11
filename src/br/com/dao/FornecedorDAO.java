package br.com.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.model.Fornecedor;

/**
 * Classe responsável por acesso a entidade Fornecedor 
 */
public class FornecedorDAO extends DaoGeneric<Fornecedor> {
	
	public FornecedorDAO(Session session) {
		super(session);
	}
	
	public FornecedorDAO() {
		super();
	}
	
}
