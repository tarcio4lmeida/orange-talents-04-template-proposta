package br.com.zupacademy.tarcio.proposta.feign.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "post", url = "${analise.client}")
public interface AnaliseClient {

	@PostMapping(value = "/api/solicitacao")
    public ResultadoAnalise getResultadoSolicitacao( SolicitacaoAnalise request);
	
}
