package br.com.zupacademy.tarcio.proposta.cadastro_biometria;

import javax.validation.constraints.NotBlank;

import org.apache.tomcat.util.codec.binary.Base64;

import br.com.zupacademy.tarcio.proposta.cadastro_cartao.Cartao;

public class BiometriaRequest {
	
	@NotBlank(message = "{campo.biometria.obrigatorio}")
	private String biometria;
	
	public BiometriaRequest() {
	}

	public String getBiometria() {
		return biometria;
	}

	public Biometria toModel(Cartao cartao) {
		return new Biometria(biometria, cartao);
	}
	
	public boolean validaBase64() {
		return Base64.isBase64(this.biometria);
	}
	
}
