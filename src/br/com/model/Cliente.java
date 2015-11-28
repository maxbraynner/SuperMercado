package br.com.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {

	private static final long serialVersionUID = 3984565902206261802L;
	
	private String matricula;

	private boolean ativo = true;
	
	// Compras que o cliente realizou
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
	private Set<Venda> compras = new HashSet<Venda>(0);
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Set<Venda> getCompras() {
		return compras;
	}

	public void setCompras(Set<Venda> compras) {
		this.compras = compras;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
