package br.com.zupacademy.tarcio.proposta.cadastro_bloqueio;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bloqueio", url = "${cartao.client}")
public interface NotificaBloqueioCartaoClient {

	@PostMapping(value = "/api/cartoes/{id}/bloqueios")
    public NotificaBloqueioResponse getResultadoSolicitacao(@PathVariable("id") String id, @RequestBody @Valid NotificaBloqueioRequest request);
	
}
