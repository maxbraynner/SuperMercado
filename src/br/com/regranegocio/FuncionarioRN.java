package br.com.regranegocio;

import java.util.List;

import br.com.dao.FuncionarioDAO;
import br.com.model.Cargo;
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
	
	/**
	 * Quando um novo cargo for atribuído a um determinado funcionário,
	 * o cargo anterior ainda permanecerá na tabela de cargos que ele possui na empresa.
	 * Assim é necessário haver um controle do cargo atual do funcionário, por isso ao ser
	 * adicionado um novo cargo a determinado funcionário o cargo anterior deve ter seu atribuito 
	 * alterado para false, e o atual para true.
	 * @param funcionario
	 */
	public void alterarCargoAtual(Funcionario funcionario) {
		for (Cargo cargo : funcionario.getCargos()) {
			if(cargo.isAtual()) {
				cargo.setAtual(false);
				break;
			}
		}
	}
	
	/**
	 * O cargo atual do funcionário é retornado.
	 * @param funcionario
	 * @return Cargo
	 */
	public Cargo buscaCargoAtual(Funcionario funcionario) {
		Cargo cargoAtual = null;
		for (Cargo cargo : funcionario.getCargos()) {
			if(cargo.isAtual()) {
				cargoAtual = cargo;
				break;
			}
		}
		
		return cargoAtual;
	}
}
