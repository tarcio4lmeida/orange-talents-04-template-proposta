package br.com.zupacademy.tarcio.proposta.cadastro_associacao_paypal;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.tarcio.proposta.cadastro_cartao.Cartao;

public class CarteiraDigitalRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{campo.email.obrigatorio}")
	@Email(message = "{campo.email.invalido}")
	private String email; 
	
	@CarteirasPermitidas(domainClass = CarteiraDigital.class, fieldName = "carteira")
	@NotBlank(message = "{campo.carteira.obrigatorio}")
	private String carteira; 
	
	@Deprecated
	public CarteiraDigitalRequest() {
	}

	public CarteiraDigitalRequest(String email, String carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public CarteiraDigital toModel(Cartao cartao) {
		return new CarteiraDigital(this.email, this.retornaCarteira(), cartao);
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}
	
	public TipoCarteira retornaCarteira() {
		return TipoCarteira.valueOf(this.carteira.replace(" ", "_").toUpperCase());
	}

}
