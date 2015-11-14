package br.com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.model.Funcionario;

/**
 * Classe responsável por acesso a entidade Funcionario 
 */
@Repository
public class FuncionarioDAO extends DaoGeneric<Funcionario> {
	
	@Autowired
	public FuncionarioDAO(SessionFactory sesseionFactory) {
		super(Funcionario.class, sesseionFactory);
	}
}
