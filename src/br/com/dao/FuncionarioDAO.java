package br.com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
	
	public List<Funcionario> consultarGerentes() {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Funcionario.class);
		criteria.createAlias("cargo", "cargo");
		criteria.add(Restrictions.like("cargo.nome", "Gerente"));
		
		return criteria.list();
	}
}
