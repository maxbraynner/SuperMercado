package br.com.regranegocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dao.CargoDAO;
import br.com.model.Cargo;

@Repository("cargoRN")
@Transactional
public class CargoRN {

	private CargoDAO cargoDAO;
	
	@Autowired
	public CargoRN(CargoDAO cargoDAO) {
		this.cargoDAO = cargoDAO;
	}
	
	public void salvar(Cargo cargo) {
		if (cargo.getId()==null || cargo.getId().intValue() == 0) {
			cargoDAO.inserir(cargo);
		}else {
			cargoDAO.alterar(cargo);
		}
	}
	
	public void excluir(Cargo cargo) {
		cargoDAO.remover(cargo);
	}
	
	public List<Cargo> listar() {
		return cargoDAO.listar();
	}
	
	public Cargo consultarPorId(Integer id) {
		return cargoDAO.consultarPorID(id);
	}
}
