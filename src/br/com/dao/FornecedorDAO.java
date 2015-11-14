package br.com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.model.Fornecedor;

/**
 * Classe responsável por acesso a entidade Fornecedor 
 */
@Repository
public class FornecedorDAO extends DaoGeneric<Fornecedor> {
	
	@Autowired
	public FornecedorDAO(SessionFactory sesseionFactory) {
		super(Fornecedor.class, sesseionFactory);
	}
	
}
