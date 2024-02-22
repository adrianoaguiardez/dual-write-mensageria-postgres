package br.com.aguiar.dualwrites.model;

public enum StatusPedido {

	A("Aberto"), 
	C("Concluída");

	private String descricao;

	StatusPedido(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}