package br.com.dao;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.model.Cliente;

/**
 * Classe responsável por acesso a entidade Cliente 
 */
@Repository
public class ClienteDAO extends DaoGeneric<Cliente> {
	
	@Autowired
	public ClienteDAO(SessionFactory sesseionFactory) {
		super(Cliente.class, sesseionFactory);
	}
	
	public Cliente consultaPorCPF(String cpf) {
		String consulta = " select cliente.ativo, cliente.matricula, pessoa.*"
				+ " from supermercado.cliente as cliente"
				+ " join supermercado.pessoa as pessoa on pessoa.id = cliente.id "
				+ " where pessoa.cpf = :cpf";
		
		SQLQuery sqlQuery = this.getSessionFactory().getCurrentSession().createSQLQuery(consulta);
		sqlQuery.setParameter("cpf", cpf);
		
		Object[] result = (Object[]) sqlQuery.uniqueResult();
		
		Cliente cliente = new Cliente();
		
		cliente = consultarPorID(Integer.parseInt(result[2].toString()));
		
		return cliente;
	}
}
