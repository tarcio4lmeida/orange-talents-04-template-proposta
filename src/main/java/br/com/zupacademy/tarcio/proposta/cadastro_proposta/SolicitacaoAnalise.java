package br.com.zupacademy.tarcio.proposta.cadastro_proposta;

public class SolicitacaoAnalise {
	
	private String documento;
	private String nome;
	private Long idProposta;
	
	@Deprecated
	public SolicitacaoAnalise() {
	}

	public SolicitacaoAnalise(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}
	
}
