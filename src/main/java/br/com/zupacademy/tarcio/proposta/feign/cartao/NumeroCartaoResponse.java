package br.com.zupacademy.tarcio.proposta.feign.cartao;

import java.io.Serializable;

public class NumeroCartaoResponse  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	public NumeroCartaoResponse() {
		super();
	}

	public String getId() {
		return id;
	}
	
}
