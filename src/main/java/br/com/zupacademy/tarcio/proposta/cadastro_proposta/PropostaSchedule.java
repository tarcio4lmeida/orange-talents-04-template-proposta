package br.com.zupacademy.tarcio.proposta.cadastro_proposta;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.tarcio.proposta.cadastro_cartao.NumeroCartaoResponse;
import br.com.zupacademy.tarcio.proposta.cadastro_cartao.NumeroCartaoRequest;
import br.com.zupacademy.tarcio.proposta.cadastro_cartao.VerificaExistenciaNumeroCartaoClient;
import feign.FeignException;

@Component
@EnableScheduling
public class PropostaSchedule {

	@Autowired
	private PropostaRepository repository;

	static final Logger logger = LogManager.getLogger(PropostaSchedule.class.getName());

	@Autowired
	private VerificaExistenciaNumeroCartaoClient verificador;

	@Scheduled(cron = "*/30 * * * * *") 
	private void associarCartaoComPropostasAprovadas() {
		List<Proposta> propostasParaAnalise = repository.propostasAprovadasSemCartao();
		logger.info("Existem {} propostas para analise", propostasParaAnalise.size());
		if (propostasParaAnalise.size() < 5) {
			logger.info("Não há propostas suficientes para rodar o job");
			return ;
		}
		logger.info("Iniciando associação de Proposta com cartão");
		for (Proposta proposta : propostasParaAnalise) {
			try {
				NumeroCartaoResponse cartaoResponse = verificador.getNumeroCartao(proposta.getId());
				logger.info("Asssociando cartão {} para a proposta {} ", cartaoResponse.getId(), proposta.getId());

				proposta.setIdCartao(cartaoResponse.getId());
				repository.save(proposta);
			} catch (FeignException.UnprocessableEntity e) {
				logger.error("Falha na atualizacao para a proposta de id {}", proposta.getId());
			}
		}
		logger.info("Fim Associação!");
	}

}
