package br.com.dao;

import br.com.model.Venda;

/**
 * Classe responsável por acesso a entidade Venda
 */
public class VendaDAO extends DaoGeneric<Venda> {
	
	public VendaDAO() {
		super(Venda.class);
	}
	
}
