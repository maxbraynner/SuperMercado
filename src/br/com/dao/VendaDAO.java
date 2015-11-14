package br.com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.model.Venda;

/**
 * Classe responsável por acesso a entidade Venda
 */
@Repository
public class VendaDAO extends DaoGeneric<Venda> {
	
	@Autowired
	public VendaDAO(SessionFactory sesseionFactory) {
		super(Venda.class, sesseionFactory);
	}
	
}
