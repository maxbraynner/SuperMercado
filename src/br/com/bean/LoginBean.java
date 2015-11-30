package br.com.bean;

import javax.faces.application.FacesMessage; 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.model.Funcionario;
import br.com.regranegocio.FuncionarioRN; 

@ManagedBean(name = "LoginBean") 
@RequestScoped
public class LoginBean {
	private Funcionario funcionario = new Funcionario();
	
	@ManagedProperty(name="funcionarioRN", value="#{funcionarioRN}")
	private FuncionarioRN funcionarioRN;
	
	public String login(){
		
		if(funcionarioRN.funcinarioLogin(funcionario.getMatricula(), funcionario.getSenha()).isEmpty()){
			funcionario = new Funcionario(); 
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Funcionário não encontrado!", "Erro no Login!"));

			
			return null;
		}else{
			return "/menu/menuPrincipal";
		}
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public void setFuncionarioRN(FuncionarioRN funcionarioRN) {
		this.funcionarioRN = funcionarioRN;
	}

}
