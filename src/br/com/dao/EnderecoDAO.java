package br.com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.model.Endereco;

/**
 * Classe responsável por acesso a entidade Endereço 
 */
@Repository
public class EnderecoDAO extends DaoGeneric<Endereco> {
	
	@Autowired
	public EnderecoDAO(SessionFactory sesseionFactory) {
		super(Endereco.class, sesseionFactory);
	}
}
