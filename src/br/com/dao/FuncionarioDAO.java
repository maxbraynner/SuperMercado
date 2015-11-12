package br.com.dao;

import br.com.model.Funcionario;

/**
 * Classe responsável por acesso a entidade Funcionario 
 */
public class FuncionarioDAO extends DaoGeneric<Funcionario> {
	
	public FuncionarioDAO() {
		super(Funcionario.class);
	}
}
