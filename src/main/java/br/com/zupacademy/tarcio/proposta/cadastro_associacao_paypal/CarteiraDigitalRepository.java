package br.com.zupacademy.tarcio.proposta.cadastro_associacao_paypal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraDigitalRepository extends JpaRepository<CarteiraDigital, Long> {
	
	@Query("SELECT case when (count(c) > 0)  then true else false end FROM CarteiraDigital c WHERE c.carteira = ?1 "
			+ "AND c.cartao.numero = ?2")
	boolean buscaCarteiraComCartaoAssociado(TipoCarteira carteira, String numeroCartao);
}
