package br.com.dao;

import br.com.model.Produto;

/**
 * Classe responsável por acesso a entidade Produto 
 */
public class ProdutoDAO extends DaoGeneric<Produto> {
	
	public ProdutoDAO() {
		super(Produto.class);
	}
}
