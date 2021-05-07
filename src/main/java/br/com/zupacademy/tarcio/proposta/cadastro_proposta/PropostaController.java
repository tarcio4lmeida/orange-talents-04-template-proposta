package br.com.zupacademy.tarcio.proposta.cadastro_proposta;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/propostas")
public class PropostaController {
	
	@Autowired
	private PropostaRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PropostaResponse> cadastrar(@RequestBody @Valid PropostaRequest request) {
		this.validaDocumento(request);
		
		Proposta proposta = request.toModel();
		repository.save(proposta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
	}
	
	public void validaDocumento(PropostaRequest request) {
		boolean existe = repository.existsByDocumento(request.getDocumento());
		if (existe) {
			throw new CadastradoDocumentoException("Documento já cadastrado");
		}
	}

}
