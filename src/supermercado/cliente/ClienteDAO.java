package supermercado.cliente;

import org.hibernate.Session;

import supermercado.dao.DaoGeneric;

/**
 * Classe responsável por consultas referentes ao Cliente 
 */
public class ClienteDAO extends DaoGeneric<Cliente> {
	
	public ClienteDAO(Session session) {
		super(session);
	}
	
	public ClienteDAO() {
		super();
	}
}
