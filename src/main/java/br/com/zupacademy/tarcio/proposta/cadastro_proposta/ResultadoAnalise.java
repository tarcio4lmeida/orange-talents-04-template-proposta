package br.com.zupacademy.tarcio.proposta.cadastro_proposta;

public class ResultadoAnalise {
	
	private String documento;
	private String nome;
	private String resultadoSolicitacao;
	private Long idProposta;
	
	@Deprecated
	public ResultadoAnalise() {
	}
	
	public ResultadoAnalise(Proposta proposta) {
		this.idProposta = proposta.getId();
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.resultadoSolicitacao = proposta.getSituacao().name();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public Long getIdProposta() {
		return idProposta;
	}
	
}
