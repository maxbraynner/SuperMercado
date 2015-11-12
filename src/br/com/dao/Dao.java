package br.com.dao;

import java.util.List;

public interface Dao<T> {
	public void inserir(T t);
	
	public void alterar(T t);
	
	public void remover(T t);
	
	public T consultarPorID(Integer id);
	
	public List<T> listar();
}