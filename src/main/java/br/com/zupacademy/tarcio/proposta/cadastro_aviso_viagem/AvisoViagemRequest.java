package br.com.zupacademy.tarcio.proposta.cadastro_aviso_viagem;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zupacademy.tarcio.proposta.cadastro_cartao.Cartao;

public class AvisoViagemRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "{campo.destino.obrigatorio}")
	private String destino; 
	
	@FutureOrPresent(message = "{campo.dataTermino.tempo}")
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate dataTermino;
	
	@Deprecated
	public AvisoViagemRequest() {
	}
	
	public AvisoViagemRequest(String destino, LocalDate dataTermino) {
		this.destino = destino;
		this.dataTermino = dataTermino;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public AvisoViagem toModel(Cartao cartao, String ipCliente, String userAgent) {
		return new AvisoViagem(this.destino, this.dataTermino, ipCliente, userAgent, cartao);
	}

}
