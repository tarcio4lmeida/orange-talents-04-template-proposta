package br.com.zupacademy.tarcio.proposta.cadastro_bloqueio;

import javax.validation.constraints.NotBlank;

public class NotificaBloqueioRequest {
	
	@NotBlank(message = "{campo.sistema.obrigatorio}")
	private String sistemaResponsavel;

	public NotificaBloqueioRequest() {
	}

	public NotificaBloqueioRequest(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
	
	
}
