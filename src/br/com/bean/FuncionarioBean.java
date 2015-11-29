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
import br.com.util.JSFUtil;

@ManagedBean(name = "funcionarioBean")
@RequestScoped
public class FuncionarioBean {
	private Funcionario funcionario = new Funcionario();
	private Endereco endereco = new Endereco();
	private Cargo cargo = new Cargo();
	private List<Cargo> listCargo;

	private List<Funcionario> funcionariosFiltrados;
	private List<Funcionario> listaFuncionarios;

	@ManagedProperty(name = "funcionarioRN", value = "#{funcionarioRN}")
	private FuncionarioRN funcionarioRN;

	@ManagedProperty(name = "cargoRN", value = "#{cargoRN}")
	private CargoRN cargoRN;

	public String salvar() throws InstantiationException, IllegalAccessException {
		try {
			// consulta o cargo que ser√° inserido
			cargo = cargoRN.consultarPorId(cargo.getId());

			funcionario.setCargo(cargo);
			funcionario.setEndereco(endereco);

			funcionarioRN.salvar(funcionario);
			JSFUtil.adicionarMensagemSucesso("Funcionario cadastrado com sucesso. ");
			return "/funcionario/listar";
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar cadastrar funcion·rio. ");
			return "/funcionario/cadastrar";
		}

	}

	// Edi√ß√£o de funcionario
	public String editar() throws InstantiationException, IllegalAccessException {
		try {
			/*
			 * O endereco √© novamente instanciado para possibilitar a
			 * exibi√ß√£o em tela
			 */
			this.setEndereco(getFuncionario().getEndereco());
			this.setCargo(funcionario.getCargo());
			return "/funcionario/cadastrar";
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao tentrar editar funcion·rio. ");
			return "/funcionario/listar";
		}

	}

	// Exclus√£o do funcionario
	public String excluir() throws InstantiationException, IllegalAccessException {
		try {
			funcionarioRN.excluir(funcionario);
			JSFUtil.adicionarMensagemSucesso("Funcion·rio excluÌdo com sucesso. s");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar excluir funcion·rio. ");
		}
		return "/funcionario/listar";
	}

	// Lista de todos os funcionarios cadastrados
	public List<Funcionario> getListaFuncionarios() throws InstantiationException, IllegalAccessException {
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
