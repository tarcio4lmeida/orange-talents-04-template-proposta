package br.com.zupacademy.tarcio.proposta.cadastro_aviso_viagem;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.zupacademy.tarcio.proposta.cadastro_cartao.Cartao;

@Entity
@Table(name = "tb_aviso_viagem")
public class AvisoViagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String destino; 
	
	@Column(nullable = false)
	private LocalDate validoAte;
	
	@Column(nullable = false)
	private Instant dataCriacao = Instant.now();
	
	@Column(nullable = false)
	private String ipCliente;
	
	@Column(nullable = false)
	private String userAgent;
	
	@ManyToOne
	private Cartao cartao;
	
	@Deprecated
	public AvisoViagem() {
	}
	
	public AvisoViagem(String destino, LocalDate validoAte, String ipCliente, String userAgent,
			Cartao cartao) {
		this.destino = destino;
		this.validoAte = validoAte;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

	public Instant getDataCriacao() {
		return dataCriacao;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public Long getId() {
		return id;
	}
	
}
