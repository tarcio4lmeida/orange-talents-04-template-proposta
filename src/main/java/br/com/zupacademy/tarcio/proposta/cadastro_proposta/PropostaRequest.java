package br.com.zupacademy.tarcio.proposta.cadastro_proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.tarcio.proposta.compartilhado.CpfCnpj;

public class PropostaRequest {

	@CpfCnpj(domainClass = Proposta.class, fieldName = "documento")
	@NotBlank(message = "{campo.documento.obrigatorio}")
	private String documento;
	
	@NotBlank(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@NotBlank(message = "{campo.email.obrigatorio}")
	@Email(message = "{campo.email.invalido}")
	private String email;
	
	@NotBlank(message = "{campo.endereco.obrigatorio}")
	private String endereco;
	
	@NotNull(message = "{campo.salario.obrigatorio}")
	@Positive(message = "{campo.salario.positivo}")
	private BigDecimal salario;
	
	public Proposta toModel() {
		return new Proposta(this.documento, this.nome, this.email, this.endereco, this.salario);
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
