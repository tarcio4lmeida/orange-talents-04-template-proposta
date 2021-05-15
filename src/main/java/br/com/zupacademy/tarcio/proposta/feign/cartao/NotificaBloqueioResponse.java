package br.com.zupacademy.tarcio.proposta.feign.cartao;

public class NotificaBloqueioResponse {
	
	private String resultado;
	
	public NotificaBloqueioResponse() {
	}

	public NotificaBloqueioResponse(String resultado) {
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}
	
}
