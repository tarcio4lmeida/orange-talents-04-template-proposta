package br.com.zupacademy.tarcio.proposta.cadastro_cartao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cartao")
public class Cartao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numero; 
	
	public Cartao(String numero) {
		this.numero = numero;
	}

	@Deprecated
	public Cartao() {
	}

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}
	
	
}
