package br.com.dao;

import br.com.model.Endereco;

/**
 * Classe responsável por acesso a entidade Endereço 
 */
public class EnderecoDAO extends DaoGeneric<Endereco> {
	
	public EnderecoDAO() {
		super(Endereco.class);
	}
}
