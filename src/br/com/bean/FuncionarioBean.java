package br.com.bean;

import java.util.List;
import java.util.Set;

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
	

	private List<Funcionario> listaFuncionarios;

	public void salvar() throws InstantiationException, IllegalAccessException {
		FuncionarioRN funcionarioRN = new FuncionarioRN();

		
		funcionario.setEndereco(endereco);
		
		funcionario.getCargos().add(cargo);
		
		funcionarioRN.salvar(funcionario);

	}


	// Edição de funcionario
	public String editar() {
		// O endereço é novamente instanciado para possibilitar a exibição
		// em tela
		this.setEndereco(getFuncionario().getEndereco());
		return "cadastrar";
	}

	// Exclusão do funcionario
	public void excluir() throws InstantiationException, IllegalAccessException {
		FuncionarioRN funcionarioRN = new FuncionarioRN();
		funcionarioRN.excluir(funcionario);

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

}
