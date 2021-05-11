package br.com.zupacademy.tarcio.proposta.cadastro_cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cartao", url = "http://localhost:8888")
public interface VerificaExistenciaNumeroCartaoClient{

	@PostMapping(value = "/api/cartoes")
    public NumeroCartaoResponse getNumeroCartao(NumeroCartaoRequest request);

}