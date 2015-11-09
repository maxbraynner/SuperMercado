package supermercado.util;

import supermercado.cliente.ClienteDAO;
import supermercado.cliente.ClienteDAOHibernate;

/**
 * Classe onde estão todas as conexões realizadas no banco de dados.
 * Usada para diminuir o acoplamento na camada de acesso a dados.
 * @author Matheus Protázio
 *
 */
public class DAOFactory {
	
	// Cria uma conexão com banco de dados para ClienteDAO
	public static ClienteDAO criarClienteDAO() {
		ClienteDAOHibernate clienteDAO = new ClienteDAOHibernate();
		clienteDAO.setSession(HibernateUtil.getSessionfactory().getCurrentSession());
		return clienteDAO;
	}
}
