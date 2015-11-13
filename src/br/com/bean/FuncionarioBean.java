package br.com.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.model.Cargo;
import br.com.model.Endereco;
import br.com.model.Funcionario;
import br.com.regranegocio.FuncionarioRN;

@ManagedBean(name = "funcionarioBean")
@RequestScoped
public class FuncionarioBean {
	private Funcionario funcionario = new Funcionario();
	private Endereco endereco = new Endereco();
	private Cargo cargo = new Cargo();
	
	private List<Funcionario> funcionarioFiltrados;
	private List<Funcionario> listaFuncionarios;

	public String salvar() throws InstantiationException, IllegalAccessException {
		FuncionarioRN funcionarioRN = new FuncionarioRN();

		funcionario.setEndereco(endereco);
		cargo.setAtual(true);
		funcionario.getCargos().add(cargo);
		funcionarioRN.salvar(funcionario);
		
		return "/funcionario/listar";
	}
	
	// Edição de funcionario
	public String editar() throws InstantiationException, IllegalAccessException {
		FuncionarioRN funcionarioRN = new FuncionarioRN();
		/*
		 * O endereco é novamente instanciado para possibilitar a exibição
		 * em tela
		 */
		this.setEndereco(getFuncionario().getEndereco());
		Cargo cargoAtual = funcionarioRN.buscaCargoAtual(funcionario);
		this.setCargo(cargoAtual);
		
		return "/funcionario/cadastrar";
	}

	// Exclusão do funcionario
	public String excluir() throws InstantiationException, IllegalAccessException {
		FuncionarioRN funcionarioRN = new FuncionarioRN();
		funcionarioRN.excluir(funcionario);
		
		return "/funcionario/listar?faces-redirect=true";
	}

	// Lista de todos os funcionarios cadastrados
	public List<Funcionario> getListaFuncionarios() throws InstantiationException, IllegalAccessException {
		FuncionarioRN funcionarioRN = new FuncionarioRN();

		this.listaFuncionarios = funcionarioRN.listar();
		return this.listaFuncionarios;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Funcionario> getFuncionarioFiltrados() {
		return funcionarioFiltrados;
	}

	public void setFuncionarioFiltrados(List<Funcionario> funcionarioFiltrados) {
		this.funcionarioFiltrados = funcionarioFiltrados;
	}

}
