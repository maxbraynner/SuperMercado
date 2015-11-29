package br.com.bean;

import java.util.List;

import javax.annotation.PostConstruct;
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
	private List<Funcionario> listGerentes;

	@ManagedProperty(name = "funcionarioRN", value = "#{funcionarioRN}")
	private FuncionarioRN funcionarioRN;

	@ManagedProperty(name = "cargoRN", value = "#{cargoRN}")
	private CargoRN cargoRN;
	
	@PostConstruct
	public void init() {
		funcionario.setGerente(new Funcionario());
	}

	public String salvar() throws InstantiationException, IllegalAccessException {
		try {
			// consulta o cargo que será inserido
			cargo = cargoRN.consultarPorId(cargo.getId());

			funcionario.setCargo(cargo);
			funcionario.setEndereco(endereco);
			
			// seta o gerente para nulo caso não tenha
			if (funcionario.getGerente().getId() == null || funcionario.getGerente().getId() == 0) {
				funcionario.setGerente(null);
			}else {
				Funcionario gerente = funcionarioRN.consularPorId(funcionario.getGerente().getId());
				
				// consulta o gerente completo, pois o objeto contem apenas o ID
				funcionario.setGerente(gerente);
			}

			funcionarioRN.salvar(funcionario);
			
			JSFUtil.adicionarMensagemSucesso("Funcionario cadastrado com sucesso. ");
			return "/funcionario/listar";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao tentar cadastrar funcionário. ");
			return "/funcionario/cadastrar";
		}

	}

	// Edição de funcionario
	public String editar() throws InstantiationException, IllegalAccessException {
		try {
			/*
			 * O endereco é novamente instanciado para possibilitar a
			 * exibição em tela
			 */
			this.setEndereco(getFuncionario().getEndereco());
			this.setCargo(funcionario.getCargo());
			
			// inicializa o gerente caso necessário
			if (funcionario.getGerente() == null) {
				funcionario.setGerente(new Funcionario());
			}
			
			return "/funcionario/cadastrar";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao tentrar editar funcionário. ");
			return "/funcionario/listar";
		}

	}

	// Exclusão do funcionario
	public String excluir() throws InstantiationException, IllegalAccessException {
		try {
			funcionarioRN.excluir(funcionario);
			JSFUtil.adicionarMensagemSucesso("Funcionário excluído com sucesso. s");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar excluir funcionário. ");
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

	public List<Funcionario> getListGerentes() {
		if (listGerentes==null) {
			listGerentes = funcionarioRN.consultarGerentes();
		}
		return listGerentes;
	}

	public void setListGerentes(List<Funcionario> listGerentes) {
		this.listGerentes = listGerentes;
	}
}
