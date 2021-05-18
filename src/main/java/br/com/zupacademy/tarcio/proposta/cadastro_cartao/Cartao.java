package br.com.zupacademy.tarcio.proposta.cadastro_cartao;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import br.com.zupacademy.tarcio.proposta.cadastro_proposta.Proposta;

@Entity
@Table(name = "tb_cartao")
public class Cartao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numero; 
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;
	
	private LocalDateTime  dataBloqueio;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Proposta proposta;
	
	public Cartao(String numero, Proposta proposta) {
		this.numero = numero;
		this.proposta = proposta;
	}
	
	@Deprecated
	public Cartao() {
	}
	
	public boolean isBloqueado() {
		return this.status.name() == Status.BLOQUEADO.name() ? true : false;
	}
	
	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime  getDataBloqueio() {
		return dataBloqueio;
	}
	
	public Proposta getProposta() {
		return proposta;
	}

	public void setDataBloqueio(LocalDateTime dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}
	
	public boolean isDonoCartao() {
		Jwt token = (Jwt) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		String email = (String) token.getClaims().get("email");
		if(this.proposta.getEmail().equals(email)) {
			return true;
		}
		return false;
	}
}
