package br.com.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.model.Cargo;
import br.com.model.Endereco;
import br.com.model.Funcionario;
import br.com.regranegocio.ClienteRN;
import br.com.regranegocio.FuncionarioRN;

@ManagedBean(name = "funcionarioBean")
@RequestScoped
public class FuncionarioBean {
	private Funcionario funcionario = new Funcionario();
	private Endereco endereco = new Endereco();
	private Cargo cargo = new Cargo();
	private List<Funcionario> listFuncionario;
	private List<Funcionario> funcionarioFiltrados;
	private List<Funcionario> listaFuncionarios;
	FuncionarioRN funcionarioRN;

	public String salvar() throws InstantiationException, IllegalAccessException {
		FuncionarioRN funcionarioRN = new FuncionarioRN();

		funcionario.setEndereco(endereco);

		funcionario.getCargos().add(cargo);

		funcionarioRN.salvar(funcionario);
		return "/funcionario/listar";

	}

	public FuncionarioBean() throws InstantiationException, IllegalAccessException {
		funcionarioRN = new FuncionarioRN();
	}

	// Edição de funcionario
	public String editar() {
		// O endereço é novamente instanciado para possibilitar a exibição
		// em tela
		this.setEndereco(getFuncionario().getEndereco());
		return "/funcionario/cadastrar?faces-redirect=true";
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

	
	// AtivaÃ§Ã£o OU inativaÃ§Ã£o de um cliente
		public void ativar() throws InstantiationException, IllegalAccessException {
			FuncionarioRN funcionarioRN = new FuncionarioRN();
			
			if (funcionario.isAtivo()) {
				funcionario.setAtivo(false);
			} else {
				funcionario.setAtivo(true);
			}
			
			funcionarioRN.salvar(funcionario);
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

	public List<Funcionario> getListFuncionario() {
		return listFuncionario;
	}

	public void setListFuncionario(List<Funcionario> listFuncionario) {
		this.listFuncionario = listFuncionario;
	}

	public List<Funcionario> getFuncionarioFiltrados() {
		return funcionarioFiltrados;
	}

	public void setFuncionarioFiltrados(List<Funcionario> funcionarioFiltrados) {
		this.funcionarioFiltrados = funcionarioFiltrados;
	}

}
