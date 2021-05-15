package br.com.zupacademy.tarcio.proposta.cadastro_aviso_viagem;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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

import br.com.zupacademy.tarcio.proposta.cadastro_cartao.Cartao;
import br.com.zupacademy.tarcio.proposta.cadastro_cartao.CartaoRepository;
import br.com.zupacademy.tarcio.proposta.feign.cartao.CartaoClient;
import br.com.zupacademy.tarcio.proposta.feign.cartao.NotificaAvisoResponse;
import feign.FeignException;

@RestController
@RequestMapping(value = "/aviso-viagens")
public class AvisoViagemController {

	@Autowired
	private AvisoViagemRepository avisoViagemRepository;

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private CartaoClient notificaAvisoCartaoClient;

	static final Logger logger = LogManager.getLogger(AvisoViagemController.class.getName());

	@PostMapping("/{numero}")
	public ResponseEntity<?> cadastrar(@PathVariable("numero") String numero,
			@RequestBody @Valid AvisoViagemRequest request, HttpServletRequest httpRequest) {

		Optional<Cartao> optCartao = cartaoRepository.findByNumero(numero);
		if (!optCartao.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		NotificaAvisoResponse notificaAvisoResponse = null;
		try {
			logger.info("Iniciando a tentativa de notificação ao sistema bancário");
			
			notificaAvisoResponse = notificaAvisoCartaoClient.getResultadoAviso(numero, request);
			logger.info("Notificaçao enviada ao sistema bancário da criacao do aviso viagem para o cartão {} - Status: {}", numero, notificaAvisoResponse.getResultado());

			AvisoViagem avisoViagem = request.toModel(optCartao.get(), httpRequest.getRemoteAddr(),
					httpRequest.getHeader("user-agent"));
			logger.info("Notificação de aviso ciagem criada com sucesso!");
			avisoViagemRepository.save(avisoViagem);

		} catch (FeignException e) {
			logger.error("Falha na tentativa de criação do aviso viagem para o cartão {}", numero);
			logger.error("Erro: {}", e.getMessage());
			return ResponseEntity.unprocessableEntity().build();
		}

		return ResponseEntity.ok().build();
	}

}
