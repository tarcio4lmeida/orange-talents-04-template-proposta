package br.com.zupacademy.tarcio.proposta.cadastro_proposta;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
//Importando classes do log4j
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zupacademy.tarcio.proposta.feign.analise.AnaliseClient;
import br.com.zupacademy.tarcio.proposta.feign.analise.ResultadoAnalise;
import br.com.zupacademy.tarcio.proposta.feign.analise.SolicitacaoAnalise;
import feign.FeignException;

@RestController
@RequestMapping(value="/propostas")
public class PropostaController {
	
	@Autowired
	private PropostaRepository repository;
	
	@Autowired
	private AnaliseClient solicitacao;

	static final Logger logger = LogManager.getLogger(PropostaController.class.getName());

	@PostMapping
	public ResponseEntity<ResultadoAnalise> cadastrar(@RequestBody @Valid PropostaRequest request) {
		this.validaDocumento(request);
		
		Proposta proposta = request.toModel();
		repository.save(proposta);
		
		this.analisaResultadoProposta(proposta);
		repository.save(proposta);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(new ResultadoAnalise(proposta));
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<PropostaResponse> detalhar(@PathVariable("id") Long id) {
		Optional<Proposta> optProposta = repository.findById(id);
		if(optProposta.isPresent()) {
			return ResponseEntity.ok(new PropostaResponse(optProposta.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	private void validaDocumento(PropostaRequest request) {
		boolean existe = repository.existsByDocumento(request.getDocumento());
		if (existe) {
			throw new CadastradoDocumentoException("Documento já cadastrado");
		}
	}
	
	private void analisaResultadoProposta(Proposta proposta) {
		try {
			logger.info("Enviando proposta {} para análise financeira", proposta.getId());
			solicitacao.getResultadoSolicitacao(new SolicitacaoAnalise(proposta));
			
			logger.info("Proposta {} elegível!", proposta.getId());
			proposta.setSituacao(Situacao.ELEGIVEL);
		
		} catch (FeignException.UnprocessableEntity e) {
			logger.error("Proposta {} não elegível!", proposta.getId());
			proposta.setSituacao(Situacao.NAO_ELEGIVEL);
		}
	}
}
