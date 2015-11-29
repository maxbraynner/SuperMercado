package br.com.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.model.Fornecedor;
import br.com.regranegocio.FornecedorRN;
import br.com.util.JSFUtil;

@ManagedBean
public class FornecedorBean {
	private Fornecedor fornecedor = new Fornecedor();
	private List<Fornecedor> listFornecedor;

	@ManagedProperty(name = "fornecedorRN", value = "#{fornecedorRN}")
	private FornecedorRN fornecedorRN;

	// atributo usado pelo componente "dataTable"
	private List<Fornecedor> fornecedoresFiltrados;

	public String salvar() {
		try {
			fornecedorRN.salvar(fornecedor);
			JSFUtil.adicionarMensagemSucesso("fornecedor cadastrado com sucesso. ");
			// retorna para tela de listagem
			return "/fornecedor/listar?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("erro ao tentar cadastrar fornecedor. ");
			return "/fornecedor/cadastrar";
		}
	}

	public void excluir(Fornecedor fornecedor) {
		try {
			// exclui o fornecedor
			fornecedorRN.excluir(fornecedor);
			JSFUtil.adicionarMensagemSucesso("Fornecedor exclu�do com sucesso. ");
			// recarrega a lista para não conter o fornecedor excluído
			listFornecedor = fornecedorRN.listar();
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao tentar excluir fornecedor. ");
		}

	}

	public List<Fornecedor> getListFornecedor() {
		if (listFornecedor == null) {
			listFornecedor = fornecedorRN.listar();
		}

		return listFornecedor;
	}

	public void setListFornecedor(List<Fornecedor> listFornecedor) {
		this.listFornecedor = listFornecedor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedoresFiltrados() {
		return fornecedoresFiltrados;
	}

	public void setFornecedoresFiltrados(List<Fornecedor> fornecedoresFiltrados) {
		this.fornecedoresFiltrados = fornecedoresFiltrados;
	}

	public void setFornecedorRN(FornecedorRN fornecedorRN) {
		this.fornecedorRN = fornecedorRN;
	}

}
