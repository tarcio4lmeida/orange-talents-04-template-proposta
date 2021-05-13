package br.com.zupacademy.tarcio.proposta.cadastro_biometria;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.zupacademy.tarcio.proposta.cadastro_cartao.Cartao;

@Entity
@Table(name = "tb_biometria")
public class Biometria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, columnDefinition = "BLOB")
	private String biometria; 
	
	@Column(nullable = false)
	private Instant dataCadastro = Instant.now();
	
	@ManyToOne
	private Cartao cartao;
	
	public Biometria(String biometria, Cartao cartao) {
		this.biometria = biometria;
		this.cartao = cartao;
	}

	@Deprecated
	public Biometria() {
	}

	public Long getId() {
		return id;
	}

	public String getBiometria() {
		return biometria;
	}
	
	
}
