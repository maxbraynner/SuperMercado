package supermercado.cliente;

import supermercado.util.DAOFactory;

public class ClienteRN {
	
	private ClienteDAO clienteDAO;
	
	// Instanciação do DAOFactory a partir do construtor
	public ClienteRN() { 
		this.clienteDAO = DAOFactory.criarClienteDAO();
	}
	
	public void salvar(Cliente cliente) {
		clienteDAO.salvar(cliente);
	}
}
