package br.com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.model.Cargo;

/**
 * Classe responsável por acesso a entidade Cargo 
 */
@Repository
public class CargoDAO extends DaoGeneric<Cargo> {
	
	@Autowired
	public CargoDAO(SessionFactory sesseionFactory) {
		super(Cargo.class, sesseionFactory);
	}
}
