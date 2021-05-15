package br.com.zupacademy.tarcio.proposta.feign.cartao;

public class NotificaAvisoResponse {
	
	private String resultado;
	
	public NotificaAvisoResponse() {
	}

	public NotificaAvisoResponse(String resultado) {
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}
	
}
