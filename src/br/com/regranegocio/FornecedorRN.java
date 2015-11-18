package br.com.regranegocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dao.FornecedorDAO;
import br.com.model.Fornecedor;

@Repository("fornecedorRN")
@Transactional
public class FornecedorRN {
	
	private FornecedorDAO fornecedorDAO;
	
	@Autowired
	public FornecedorRN(FornecedorDAO fornecedorDAO) { 
		this.fornecedorDAO = fornecedorDAO;
	}
	
	public void salvar(Fornecedor fornecedor) {
		if (fornecedor.getId() == null || fornecedor.getId().intValue() == 0) {
			fornecedorDAO.inserir(fornecedor);
		}else{
			fornecedorDAO.alterar(fornecedor);
		}
	}
	
	public List<Fornecedor> listar() {
		return fornecedorDAO.listar();
	}
	
	public void excluir(Fornecedor fornecedor){
		this.fornecedorDAO.remover(fornecedor);
	}

	public Fornecedor consultarPorId(Integer id) {
		return this.fornecedorDAO.consultarPorID(id);
	}
}
