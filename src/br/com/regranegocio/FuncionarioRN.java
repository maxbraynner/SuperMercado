package br.com.regranegocio;

import java.util.List;

import br.com.dao.FuncionarioDAO;
import br.com.model.Funcionario;
import br.com.util.DAOFactory;


public class FuncionarioRN {

private FuncionarioDAO funcionarioDAO;
	
	// Instanciação do DAOFactory a partir do construtor
	public FuncionarioRN() throws InstantiationException, IllegalAccessException { 
		DAOFactory factory = new DAOFactory(); 
		
		this.funcionarioDAO = (FuncionarioDAO) factory.getDao(FuncionarioDAO.class);
	}
	
	public void salvar(Funcionario funcionario) {
		Integer id = funcionario.getId();
		
		if (id == null || id == 0) {
			this.funcionarioDAO.inserir(funcionario);
		} else {
			this.funcionarioDAO.alterar(funcionario);
		}
	}
	
	public List<Funcionario> listar(){
		return funcionarioDAO.listar();
	}
	
	public void excluir(Funcionario funcionario){
		funcionarioDAO.remover(funcionario);
	}
}
