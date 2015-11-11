package br.com.dao;

import org.hibernate.Session;

import br.com.model.Funcionario;

/**
 * Classe responsável por acesso a entidade Funcionario 
 */
public class FuncionarioDAO extends DaoGeneric<Funcionario> {
	
	public FuncionarioDAO(Session session) {
		super(session);
	}
	
	public FuncionarioDAO() {
		super();
	}
}
