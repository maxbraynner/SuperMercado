package br.com.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.util.HibernateUtil;

/**
 * Classe de acesso básico para qualquer entidade 
 * @param <T>
 */
public class DaoGeneric<T> implements Dao<T> {
	private Session session;
	private Class<T> clas;
	
	public DaoGeneric(Class<T> clas) {
		this.session = HibernateUtil.getSessionfactory().getCurrentSession();
		this.clas = clas;
	}
	
	@Override
	public void inserir(T t) {
		this.session.save(t);
	}

	@Override
	public void alterar(T t) {
		this.session.update(t);
	}
	
	@Override
	public void remover(T t) {
		this.session.delete(t);
	}

	@Override
	public T consultarPorID(Integer id) {
		return (T) this.session.get(clas, id);
	}

	@Override
	public List<T> listar() {
		return this.session.createCriteria(clas).list();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
