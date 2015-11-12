package br.com.dao;

import br.com.model.Fornecedor;

/**
 * Classe responsável por acesso a entidade Fornecedor 
 */
public class FornecedorDAO extends DaoGeneric<Fornecedor> {
	
	public FornecedorDAO() {
		super(Fornecedor.class);
	}
	
}
