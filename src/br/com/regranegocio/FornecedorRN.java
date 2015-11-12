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
		
		// recupera o dao
		this.fornedorDAO = (FornecedorDAO) factory.getDao(FornecedorDAO.class);
	}
	
	public void salvar(Fornecedor fornecedor) {
		if (fornecedor.getId() == null || fornecedor.getId().intValue() == 0) {
			fornedorDAO.inserir(fornecedor);
		}else{
			fornedorDAO.alterar(fornecedor);
		}
	}
	
	public List<Fornecedor> listar() {
		return fornedorDAO.listar();
	}
	
	public void excluir(Fornecedor fornecedor){
		this.fornedorDAO.remover(fornecedor);
	}
}
