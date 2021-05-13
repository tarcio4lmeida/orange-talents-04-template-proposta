package br.com.zupacademy.tarcio.proposta.cadastro_proposta;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.zupacademy.tarcio.proposta.cadastro_cartao.Cartao;

@Entity
@Table(name = "tb_proposta")
public class Proposta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique=true)
	private String documento;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String endereco;
	
	@Column(nullable = false)
	private BigDecimal salario;
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao = Situacao.NAO_AVALIADO;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Cartao cartao;
	
	@Deprecated
	public Proposta() {
	}

	public Proposta(String documento, String nome, String email, String endereco, BigDecimal salario) {
		this.documento = documento;
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public String getDocumento() {
		return documento;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	
}
