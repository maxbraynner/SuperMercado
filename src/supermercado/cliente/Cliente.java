package supermercado.cliente;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import supermercado.pessoa.Pessoa;
import supermercado.venda.Venda;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends Pessoa {

	private static final long serialVersionUID = 3984565902206261802L;
	
	private String matricula;

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
}
