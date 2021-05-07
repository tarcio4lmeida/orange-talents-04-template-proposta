package br.com.zupacademy.tarcio.proposta.compartilhado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErroValidacaoCampos extends ErroPadrao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<CampoMensagem> erros = new ArrayList<>();

	public List<CampoMensagem> getErrors() {
		return erros;
	}
	
	public void addErro(String nomeCampo, String mensagem) {
		erros.add(new CampoMensagem(nomeCampo, mensagem));
	}
}
