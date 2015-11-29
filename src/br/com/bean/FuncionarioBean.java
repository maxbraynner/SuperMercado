package br.com.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.model.Cargo;
import br.com.model.Endereco;
import br.com.model.Funcionario;
import br.com.regranegocio.CargoRN;
import br.com.regranegocio.FuncionarioRN;

@ManagedBean(name = "funcionarioBean")
@RequestScoped
public class FuncionarioBean {
	private Funcionario funcionario = new Funcionario();
	private Endereco endereco = new Endereco();
	private Cargo cargo = new Cargo();
	private List<Cargo> listCargo;
	
	private List<Funcionario> funcionariosFiltrados;
	private List<Funcionario> listaFuncionarios;
	
	@ManagedProperty(name="funcionarioRN", value="#{funcionarioRN}")
	private FuncionarioRN funcionarioRN;
	
	@ManagedProperty(name = "cargoRN", value="#{cargoRN}")
	private CargoRN cargoRN;

	public String salvar() throws InstantiationException, IllegalAccessException {
		// consulta o cargo que será inserido
		cargo = cargoRN.consultarPorId(cargo.getId());

		funcionario.setCargo(cargo);
		funcionario.setEndereco(endereco);
		
		// Funcionario ao ser cadastrado está automaticamente ativo no sistema	
		funcionario.setAtivo(true);
		funcionarioRN.salvar(funcionario);
		
		return "/funcionario/listar";
	}
	
	// Edição de funcionario
	public String editar() throws InstantiationException, IllegalAccessException {
		/*
		 * O endereco é novamente instanciado para possibilitar a exibição
		 * em tela
		 */
		this.setEndereco(getFuncionario().getEndereco());
		this.setCargo(funcionario.getCargo());
		
		return "/funcionario/cadastrar";
	}

	// Exclusão do funcionario
	public String excluir() throws InstantiationException, IllegalAccessException {
		funcionarioRN.excluir(funcionario);
		
		return "/funcionario/listar?faces-redirect=true";
	}

	// Lista de todos os funcionarios cadastrados
	public List<Funcionario> getListaFuncionarios() throws InstantiationException, IllegalAccessException {
		this.listaFuncionarios = funcionarioRN.listar();
		return this.listaFuncionarios;
	}

	// Ativação OU inativação de um funcionario
	public void ativar() throws InstantiationException, IllegalAccessException {
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

	public List<Funcionario> getFuncionariosFiltrados() {
		return funcionariosFiltrados;
	}

	public void setFuncionariosFiltrados(List<Funcionario> funcionariosFiltrados) {
		this.funcionariosFiltrados = funcionariosFiltrados;
	}

	public void setFuncionarioRN(FuncionarioRN funcionarioRN) {
		this.funcionarioRN = funcionarioRN;
	}

	public List<Cargo> getListCargo() {
		listCargo = cargoRN.listar();
		return listCargo;
	}

	public void setListCargo(List<Cargo> listCargo) {
		this.listCargo = listCargo;
	}

	public CargoRN getCargoRN() {
		return cargoRN;
	}

	public void setCargoRN(CargoRN cargoRN) {
		this.cargoRN = cargoRN;
	}
}
