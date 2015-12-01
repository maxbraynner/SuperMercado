package br.com.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
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
	
	public List<Funcionario> consultarFuncionarioLogin(String Matricula, String Senha){
		try{
			return this.getSessionFactory().getCurrentSession().createSQLQuery("select * from supermercado.funcionario where funcionario.matricula = :matricula and funcionario.password = :senha").setParameter("matricula", Matricula).setParameter("senha", Senha).list();
		}catch(NoResultException e){
			return null;
		}		
	}
	
	public Funcionario consultaPorCPF(String cpf) {
		String consulta = " select funcionario.ativo, funcionario.matricula, pessoa.*"
				+ " from supermercado.funcionario as funcionario"
				+ " join supermercado.pessoa as pessoa on pessoa.id = funcionario.id "
				+ " where pessoa.cpf = :cpf";
		
		SQLQuery sqlQuery = this.getSessionFactory().getCurrentSession().createSQLQuery(consulta);
		sqlQuery.setParameter("cpf", cpf);
		
		Object[] result = (Object[]) sqlQuery.uniqueResult();
		
		Funcionario funcionario = new Funcionario();
		
		funcionario = consultarPorID(Integer.parseInt(result[2].toString()));
		
		return funcionario;
	}
	
	public Funcionario consultaPorMatricula(String matricula) {
		String consulta = " select funcionario.ativo, funcionario.matricula, pessoa.*"
				+ " from supermercado.funcionario as funcionario"
				+ " join supermercado.pessoa as pessoa on pessoa.id = funcionario.id "
				+ " where funcionario.matricula = :matricula";
		
		SQLQuery sqlQuery = this.getSessionFactory().getCurrentSession().createSQLQuery(consulta);
		sqlQuery.setParameter("matricula", matricula);
		
		Object[] result = (Object[]) sqlQuery.uniqueResult();
		
		Funcionario funcionario = new Funcionario();
		
		funcionario = consultarPorID(Integer.parseInt(result[2].toString()));
		
		return funcionario;
	}
}
