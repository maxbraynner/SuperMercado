package br.com.regranegocio;

import java.util.List;

import br.com.dao.FornecedorDAO;
import br.com.model.Fornecedor;
import br.com.util.DAOFactory;

public class FornecedorRN {
	
	private FornecedorDAO fornedorDAO;
	
	// Instanciação do DAOFactory a partir do construtor
	public FornecedorRN() throws InstantiationException, IllegalAccessException { 
		DAOFactory factory = new DAOFactory(); 
		
		this.fornedorDAO = (FornecedorDAO) factory.getDao(FornecedorDAO.class);
	}
	
	public void salvar(Fornecedor fornecedor) {
		fornedorDAO.inserir(fornecedor);
	}
	
	public List<Fornecedor> listar() {
		return fornedorDAO.listar();
	}
}
