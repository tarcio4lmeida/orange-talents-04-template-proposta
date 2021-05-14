package br.com.zupacademy.tarcio.proposta.cadastro_bloqueio;

import java.time.LocalDateTime;

import br.com.zupacademy.tarcio.proposta.cadastro_cartao.Cartao;

public class BloqueioResponse {
	
	private String statusCartao;
	private LocalDateTime dataBloqueio;

	
	@Deprecated
	public BloqueioResponse() {
	}
	
	public BloqueioResponse(Cartao cartao) {
		this.statusCartao = cartao.getStatus().toString();
		this.dataBloqueio = cartao.getDataBloqueio();
		
	}

	public String getStatusCartao() {
		return statusCartao;
	}

	public LocalDateTime getDataBloqueio() {
		return dataBloqueio;
	}
	

	
}
