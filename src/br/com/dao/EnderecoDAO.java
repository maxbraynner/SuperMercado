package br.com.dao;

import org.hibernate.Session;

import br.com.model.Endereco;

/**
 * Classe responsável por acesso a entidade Endereço 
 */
public class EnderecoDAO extends DaoGeneric<Endereco> {
	
	public EnderecoDAO(Session session) {
		super(session);
	}
	
	public EnderecoDAO() {
		super();
	}
}
