package br.com.zupacademy.tarcio.proposta.cadastro_associacao_paypal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.zupacademy.tarcio.proposta.cadastro_cartao.Cartao;

@Entity
@Table(name = "tb_carteira_digital")
public class CarteiraDigital implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String email; 
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoCarteira carteira; 
	
	@ManyToOne
	@Column(nullable = false)
	private Cartao cartao;
	
	@Deprecated
	public CarteiraDigital() {
	}
	
	public CarteiraDigital(String email, TipoCarteira carteira, Cartao cartao) {
		this.email = email;
		this.carteira = carteira;
		this.cartao = cartao;
	}

	public String getEmail() {
		return email;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public Long getId() {
		return id;
	}

	public TipoCarteira getCarteira() {
		return carteira;
	}
	
}
