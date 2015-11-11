package br.com.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

public class ConexaoHibernateFilter implements Filter {
	private SessionFactory sessionFactory;
	
	@Override
	public void destroy() {	
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
			throws IOException, ServletException {
		try {
			// recupera sessão
			Transaction transaction = this.sessionFactory.getCurrentSession().beginTransaction();
			
			// executa o request
			filterChain.doFilter(servletRequest, servletResponse);
			
			// efetiva a transação
			transaction.commit();
		} catch (Throwable ex) {
			try {
				// caso ocorra erro efetua o rollback
				if (this.sessionFactory.getCurrentSession().getTransaction().isActive()) {
					this.sessionFactory.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
			throw new ServletException();
		}finally {
			this.sessionFactory.getCurrentSession().close();
		}
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.sessionFactory = HibernateUtil.getSessionfactory();
		
	}

}
