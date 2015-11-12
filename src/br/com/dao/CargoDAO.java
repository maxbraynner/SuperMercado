package br.com.dao;

import br.com.model.Cargo;

/**
 * Classe responsável por acesso a entidade Cargo 
 */
public class CargoDAO extends DaoGeneric<Cargo> {
	
	public CargoDAO() {
		super(Cargo.class);
	}
}
