package br.com.zupacademy.tarcio.proposta.cadastro_cartao;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.tarcio.proposta.cadastro_bloqueio.Bloqueio;
import br.com.zupacademy.tarcio.proposta.cadastro_bloqueio.BloqueioRepository;
import br.com.zupacademy.tarcio.proposta.cadastro_bloqueio.NotificaBloqueioCartaoClient;
import br.com.zupacademy.tarcio.proposta.cadastro_bloqueio.NotificaBloqueioRequest;
import br.com.zupacademy.tarcio.proposta.cadastro_bloqueio.NotificaBloqueioResponse;
import feign.FeignException;

@RestController
@RequestMapping(value="/cartoes")
public class CartaoController {

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private BloqueioRepository bloqueioRepository;
	
	@Autowired
	private NotificaBloqueioCartaoClient notificaBloqueioCartaoClient;
	
	static final Logger logger = LogManager.getLogger(CartaoController.class.getName());
	
	@PutMapping("/{numero}")
	@Transactional
	public ResponseEntity<?> bloquearCartao(@PathVariable("numero") String numero, @RequestBody @Valid NotificaBloqueioRequest notificaBloqueioRequest, HttpServletRequest request) {
		
		Optional<Cartao> optCartao = cartaoRepository.findByNumero(numero);
		if(optCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Cartao cartao = optCartao.get();
		if(cartao.isBloqueado()) {
			return ResponseEntity.unprocessableEntity().body("O cartão já está bloqueado!");
		}
		NotificaBloqueioResponse notificaBloqueioResponse = null;
		try {
			logger.info("Notificando o sistema legado da tentativa de bloqueio do Cartão {}", numero);
			notificaBloqueioResponse = notificaBloqueioCartaoClient.getResultadoSolicitacao(numero, notificaBloqueioRequest);
			
			cartao.setStatus(Status.BLOQUEADO);
			cartao.setDataBloqueio(LocalDateTime.now());
			Bloqueio bloqueio = new Bloqueio(cartao, request.getRemoteAddr(), request.getHeader("user-agent"));
			
			logger.info("Cartão {} bloqueado!", numero);
			bloqueioRepository.save(bloqueio);
		}catch(FeignException e){
			logger.error("Falha no bloqueio do cartão {}", numero);
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(notificaBloqueioResponse);
		
	}
	
	
}
