package br.com.zupacademy.tarcio.proposta.cadastro_cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartao", url = "http://localhost:8888")
public interface VerificaExistenciaNumeroCartaoClient{

	@GetMapping(value = "/api/cartoes/{id}")
    public NumeroCartaoResponse getNumeroCartao(@RequestParam("idProposta") Long idProposta);

}