package br.com.zupacademy.tarcio.proposta.cadastro_biometria;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

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

@RestController
@RequestMapping(value="/biometrias")
public class BiometriaController {
	
	@Autowired
	private BiometriaRepository biometriaRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@PostMapping("/{numero}")
	public ResponseEntity<?> cadastrar(@PathVariable("numero") String numero, @RequestBody @Valid BiometriaRequest request) {
		
		if (!request.validaBase64()) {
			return ResponseEntity.status(400).body("Base64 inválido!");
		}
		
		Optional<Cartao> optCartao = cartaoRepository.findByNumero(numero);
		if(!optCartao.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Biometria biometria = request.toModel(optCartao.get());
		biometriaRepository.save(biometria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(biometria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
}
