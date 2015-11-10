package supermercado.cliente;

import supermercado.util.DAOFactory;

public class ClienteRN {
	
	private ClienteDAO clienteDAO;
	
	// Instanciação do DAOFactory a partir do construtor
	public ClienteRN() throws InstantiationException, IllegalAccessException { 
		DAOFactory factory = new DAOFactory(); 
		
		this.clienteDAO = (ClienteDAO) factory.getDao(ClienteDAO.class);
	}
	
	public void salvar(Cliente cliente) {
		clienteDAO.inserir(cliente);
	}
}
