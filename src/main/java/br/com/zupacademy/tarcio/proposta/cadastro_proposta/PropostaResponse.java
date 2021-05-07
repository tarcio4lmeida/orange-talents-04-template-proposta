package br.com.zupacademy.tarcio.proposta.cadastro_proposta;

import java.math.BigDecimal;

public class PropostaResponse {

	private Long id;
	
	private String documento;
	
	private String nome;
	
	private String email;
	
	private String endereco;
	
	private BigDecimal salario;
	
	PropostaResponse(Proposta proposta){
		this.id = proposta.getId();
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.email = proposta.getEmail();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getDocumento() {
		return documento;
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
	
}
