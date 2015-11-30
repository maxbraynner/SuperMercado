package br.com.model;

public enum TipoPagamento {
	CRÉDITO("Cartão de Crédito"), DÉBITO("Cartão de Débito"), DINHEIRO("Dinheiro"), VALE("Vale Alimentação");

	private final String descricao;
	
	public String getDescricao(){
		return descricao;
	}

	private TipoPagamento(String descricao) {
		this.descricao = descricao;
	}
}
