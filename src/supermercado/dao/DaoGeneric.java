package supermercado.dao;

import java.util.List;

import org.hibernate.Session;

import supermercado.util.HibernateUtil;

public class DaoGeneric<T> implements Dao<T> {
	private Session session;
	
	public DaoGeneric(Session session) {
		this.session = session;
	}
	
	public DaoGeneric() {
		this.session = HibernateUtil.getSessionfactory().getCurrentSession();
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
	public T consultarPorID(Class<T> clas, Long id) {
		return (T) this.session.get(clas, id);
	}

	@Override
	public List<T> listar(Class<T> clas) {
		return this.session.createCriteria(clas).list();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
