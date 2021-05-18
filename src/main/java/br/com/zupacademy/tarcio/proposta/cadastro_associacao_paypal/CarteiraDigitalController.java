package br.com.zupacademy.tarcio.proposta.cadastro_associacao_paypal;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zupacademy.tarcio.proposta.cadastro_cartao.Cartao;
import br.com.zupacademy.tarcio.proposta.cadastro_cartao.CartaoRepository;
import br.com.zupacademy.tarcio.proposta.feign.cartao.CartaoClient;
import br.com.zupacademy.tarcio.proposta.feign.cartao.NotificaCarteiraDigitalResponse;
import feign.FeignException;

@RestController
@RequestMapping(value = "/carteira-digitais")
public class CarteiraDigitalController {

	@Autowired
	private CarteiraDigitalRepository carteiraDigitalRepository;

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private CartaoClient carteiraDigitalClient;
	
	static final Logger logger = LogManager.getLogger(CarteiraDigitalController.class.getName());

	@PostMapping("/{numero}")
	public ResponseEntity<?> cadastrar(@PathVariable("numero") String numero,
			@RequestBody @Valid CarteiraDigitalRequest request) {
		Optional<Cartao> optCartao = cartaoRepository.findByNumero(numero);
		if (optCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		if(carteiraDigitalRepository.buscaCarteiraComCartaoAssociado(request.retornaCarteira(), optCartao.get().getNumero())) {
			return ResponseEntity.unprocessableEntity().body("Cartão já está associado com a carteira "+ request.getCarteira());
		}
		NotificaCarteiraDigitalResponse notificaCarteiraDigitalResponse = null;
		CarteiraDigital carteiraDigital = null;
		try {
			logger.info("Iniciando a tentativa de associacao do cartão {} com o {}", numero, request.getCarteira());

			notificaCarteiraDigitalResponse = carteiraDigitalClient.getCarteiras(numero, request);

			carteiraDigital = request.toModel(optCartao.get());

			logger.info("Associacao criada com sucesso!");
			carteiraDigitalRepository.save(carteiraDigital);

		} catch (FeignException e) {
			logger.error("Falha na tentativa de associacao para o cartão {}", numero);
			logger.error("Erro: {}", e.getMessage());
			return ResponseEntity.unprocessableEntity().build();
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carteiraDigital.getId())
				.toUri();
		return ResponseEntity.created(uri).body(notificaCarteiraDigitalResponse);

	}

}
