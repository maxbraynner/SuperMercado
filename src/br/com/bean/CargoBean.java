package br.com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.model.Cargo;
import br.com.regranegocio.CargoRN;

@ManagedBean
public class CargoBean {
	private Cargo cargo = new Cargo();
	private List<Cargo> listCargo;
	private List<Cargo> listCargoFiltro = new ArrayList<Cargo>();

	@ManagedProperty(name = "cargoRN", value="#{cargoRN}")
	private CargoRN cargoRN;

	public String salvar() {
		cargoRN.salvar(cargo);
		
		return "/cargo/listar?faces-redirect=true";
	}
	
	public String excluir(Cargo cargo) {
		cargoRN.excluir(cargo);
		
		return "/cargo/listar?faces-redirect=true";
	}
	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Cargo> getListCargo() {
		if (listCargo==null) {
			listCargo = cargoRN.listar();
		}
		
		return listCargo;
	}

	public void setListCargo(List<Cargo> listCargo) {
		this.listCargo = listCargo;
	}


	public List<Cargo> getListCargoFiltro() {
		return listCargoFiltro;
	}

	public void setListCargoFiltro(List<Cargo> listCargoFiltro) {
		this.listCargoFiltro = listCargoFiltro;
	}

	public CargoRN getCargoRN() {
		return cargoRN;
	}

	public void setCargoRN(CargoRN cargoRN) {
		this.cargoRN = cargoRN;
	}
}
