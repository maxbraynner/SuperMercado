package br.com.dao;

import java.util.List;

import org.hibernate.SessionFactory;

/**
 * Classe de acesso básico para qualquer entidade 
 * @param <T>
 */
public class DaoGeneric<T> implements Dao<T> {	
	private Class<T> clas;
	
	/**
	 * Fábrica de sessões do Hibernate
	 */
	private SessionFactory sessionFactory;
	
	public DaoGeneric(Class<T> clas, SessionFactory sessionFactory) {
		this.clas = clas;
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void inserir(T t) {
		this.sessionFactory.getCurrentSession().save(t);
	}

	@Override
	public void alterar(T t) {
		this.sessionFactory.getCurrentSession().update(t);
	}
	
	@Override
	public void remover(T t) {
		this.sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public T consultarPorID(Integer id) {
		return (T) this.sessionFactory.getCurrentSession().get(clas, id);
	}

	@Override
	public List<T> listar() {
		return this.sessionFactory.getCurrentSession().createCriteria(clas).list();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
