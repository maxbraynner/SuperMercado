package br.com.regranegocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dao.VendaDAO;

@Repository("vendaRN")
@Transactional
public class VendaRN {
	
	private VendaDAO vendaDAO;
	
	@Autowired	
	public VendaRN(VendaDAO vendaDAO){ 
		this.vendaDAO = vendaDAO;
	}
}
