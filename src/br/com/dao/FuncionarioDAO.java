package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.model.Cargo;
import br.com.model.Funcionario;

/**
 * Classe responsável por acesso a entidade Funcionario 
 */
public class FuncionarioDAO extends DaoGeneric<Funcionario> {
	
	public FuncionarioDAO() {
		super(Funcionario.class);
	}
}
