package br.com.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "id")
public class Funcionario extends Pessoa {

	private static final long serialVersionUID = 8380268096714238308L;
	
	private boolean ativo;

	@NaturalId
	@Column(unique = true, nullable = false)
	private String matricula;

	// Coleção com os funcionários que determina funcionário gerencia
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "funcionarios_gerenciados", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "fk_funcionario_gerenciados")
	private Set<Funcionario> gerenciados = new HashSet<Funcionario>();

	// todos os cargos que o funcionário já exerceu
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_funcionario")
	@org.hibernate.annotations.ForeignKey(name = "fk_funcionario_cargo")
	private Set<Cargo> cargos = new HashSet<Cargo>();
	
	// Vendas realizadas pelo funcionário
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "funcionario")
	private Set<Venda> vendas = new HashSet<Venda>(0);
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Set<Funcionario> getGerenciados() {
		return gerenciados;
	}

	public void setGerenciados(Set<Funcionario> gerenciados) {
		this.gerenciados = gerenciados;
	}

	public Set<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(Set<Cargo> cargos) {
		this.cargos = cargos;
	}

	public Set<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(Set<Venda> vendas) {
		this.vendas = vendas;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
}
