package br.com.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Pessoa {

	private static final long serialVersionUID = 8380268096714238308L;
	
	private boolean ativo = true;

	@Column(unique = true, nullable = false)
	private String matricula;

	// Coleção com os funcionários que determina funcionário gerencia
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "funcionarios_gerenciados", nullable = true)
	@org.hibernate.annotations.ForeignKey(name = "fk_funcionario_gerenciados")
	private Set<Funcionario> gerenciados = new HashSet<Funcionario>();

	// todos os cargos que o funcionário já exerceu
	@ManyToOne
	@JoinColumn(name = "id_cargo")
	@org.hibernate.annotations.ForeignKey(name = "fk_funcionario_cargo")
	private Cargo cargo;
	
	// Vendas realizadas pelo funcionário
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "funcionario")
	private Set<Venda> vendas = new HashSet<Venda>(0);
	
	@Column(name = "data_contratacao", nullable = false, updatable = false)
	private Date dataContratacao;
	
	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

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
	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
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
