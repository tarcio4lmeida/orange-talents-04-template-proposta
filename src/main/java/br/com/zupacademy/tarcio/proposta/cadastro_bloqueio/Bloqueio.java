package br.com.zupacademy.tarcio.proposta.cadastro_bloqueio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.zupacademy.tarcio.proposta.cadastro_cartao.Cartao;

@Entity
@Table(name = "tb_bloqueio")
public class Bloqueio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cartao cartao;
	
	@Column(nullable = false)
	private String ipCliente;
	
	@Column(nullable = false)
	private String userAgent;
	
	
	@Deprecated
	public Bloqueio() {
	}
	
	public Bloqueio(Cartao cartao, String ipCliente, String userAgent) {
		this.cartao = cartao;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
	}

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getUserAgent() {
		return userAgent;
	}
	
}
