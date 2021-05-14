package br.com.zupacademy.tarcio.proposta.cadastro_cartao;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.tarcio.proposta.cadastro_bloqueio.Bloqueio;
import br.com.zupacademy.tarcio.proposta.cadastro_bloqueio.BloqueioRepository;
import br.com.zupacademy.tarcio.proposta.cadastro_bloqueio.BloqueioResponse;

@RestController
@RequestMapping(value="/cartoes")
public class CartaoController {

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private BloqueioRepository bloqueioRepository;
	
	@PutMapping("/{numero}")
	@Transactional
	public ResponseEntity<?> bloquearCartao(@PathVariable("numero") @Valid String numero, HttpServletRequest request) {
		
		Optional<Cartao> optCartao = cartaoRepository.findByNumero(numero);
		if(optCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Cartao cartao = optCartao.get();
		if(cartao.isBloqueado()) {
			return ResponseEntity.unprocessableEntity().body("O cartão já está bloqueado!");
		}
		cartao.setStatus(Status.BLOQUEADO);
		cartao.setDataBloqueio(LocalDateTime.now());
		
		Bloqueio bloqueio = new Bloqueio(cartao, request.getRemoteAddr(), request.getHeader("user-agent"));
		bloqueioRepository.save(bloqueio);
		
		return ResponseEntity.ok(new BloqueioResponse(cartao));
		
	}
	
	
}
