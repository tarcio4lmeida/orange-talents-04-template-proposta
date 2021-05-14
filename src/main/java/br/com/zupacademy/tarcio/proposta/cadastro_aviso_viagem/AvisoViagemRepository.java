package br.com.zupacademy.tarcio.proposta.cadastro_aviso_viagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoViagemRepository extends JpaRepository<AvisoViagem, Long> {

}
