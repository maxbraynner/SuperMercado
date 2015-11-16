package br.com.regranegocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dao.ProdutoDAO;
import br.com.model.Produto;

@Repository("produtoRN")
@Transactional
public class ProdutoRN {

	ProdutoDAO produtoDAO;
	
	@Autowired
	public ProdutoRN(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
	}
	
	public void salvar(Produto produto) {
		if (produto.getId()==null || produto.getId()==0) {
			produtoDAO.inserir(produto);
		}else{
			produtoDAO.alterar(produto);
		}
	}
	
	public void excluir(Produto produto) {
		produtoDAO.remover(produto);
	}
	
	public List<Produto> listar() {
		return produtoDAO.listar();
	}
	
	public void inativar(Produto produto) {
		produto.setAtivo(false);
		
		produtoDAO.alterar(produto);
	}
}
