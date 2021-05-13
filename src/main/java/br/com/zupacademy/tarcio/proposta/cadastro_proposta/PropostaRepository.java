package br.com.zupacademy.tarcio.proposta.cadastro_proposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	boolean existsByDocumento(String documento);
	
	@Query("SELECT p FROM Proposta p WHERE p.situacao = 'ELEGIVEL' "
			+ "AND p.cartao.id = NULL")
	List<Proposta> propostasAprovadasSemCartao();

}
