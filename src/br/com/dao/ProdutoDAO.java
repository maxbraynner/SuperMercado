package br.com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.model.Produto;

/**
 * Classe responsável por acesso a entidade Produto 
 */
@Repository
public class ProdutoDAO extends DaoGeneric<Produto> {
	
	@Autowired
	public ProdutoDAO(SessionFactory sesseionFactory) {
		super(Produto.class, sesseionFactory);
	}
}
