package br.com.zupacademy.tarcio.proposta.feign.cartao;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zupacademy.tarcio.proposta.cadastro_aviso_viagem.AvisoViagemRequest;

@FeignClient(name = "bloqueio", url = "${cartao.client}")
public interface CartaoClient {
	
	@GetMapping(value = "/api/cartoes")
    public NumeroCartaoResponse getNumeroCartao(@RequestParam("idProposta") Long idProposta);

	@PostMapping(value = "/api/cartoes/{id}/bloqueios")
    public NotificaBloqueioResponse getResultadoSolicitacao(@PathVariable("id") String id, @RequestBody @Valid NotificaBloqueioRequest request);
	
	@PostMapping(value = "/api/cartoes/{id}/avisos")
    public NotificaAvisoResponse getResultadoAviso(@PathVariable("id") String id, @RequestBody @Valid AvisoViagemRequest request);
}
