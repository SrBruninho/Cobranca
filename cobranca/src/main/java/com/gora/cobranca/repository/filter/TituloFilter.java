package com.gora.cobranca.repository.filter;

public class TituloFilter {
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String descricaoValida() {
		return this.descricao == null ? "" : this.descricao;
	}
	
	
}
