package br.com.zupacademy.tarcio.proposta.cadastro_cartao;

import br.com.zupacademy.tarcio.proposta.cadastro_proposta.Proposta;

public class NumeroCartaoRequest {
	
	String documento;
	String nome;
	String idProposta;
	
	public NumeroCartaoRequest(Proposta proposta ) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId().toString();
	}
	
	public String getDocumento() {
		return documento;
	}
	public String getNome() {
		return nome;
	}
	public String getIdProposta() {
		return idProposta;
	}
	
	
}
