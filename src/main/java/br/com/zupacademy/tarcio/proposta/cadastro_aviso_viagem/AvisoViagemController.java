package br.com.zupacademy.tarcio.proposta.cadastro_aviso_viagem;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.tarcio.proposta.cadastro_cartao.Cartao;
import br.com.zupacademy.tarcio.proposta.cadastro_cartao.CartaoRepository;

@RestController
@RequestMapping(value="/aviso-viagens")
public class AvisoViagemController {
	
	@Autowired
	private AvisoViagemRepository avisoViagemRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@PostMapping("/{numero}")
	public ResponseEntity<?> cadastrar(@PathVariable("numero") String numero, @RequestBody @Valid AvisoViagemRequest request, HttpServletRequest httpRequest) {
		
		Optional<Cartao> optCartao = cartaoRepository.findByNumero(numero);
		if(!optCartao.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		AvisoViagem avisoViagem = request.toModel(optCartao.get(), httpRequest.getRemoteAddr(), httpRequest.getHeader("user-agent"));
		avisoViagemRepository.save(avisoViagem);
		
		return ResponseEntity.ok().build();
	}
	
	
}
