package br.com.zupacademy.tarcio.proposta.compartilhado;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.zupacademy.tarcio.proposta.cadastro_proposta.CadastradoDocumentoException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroValidacaoCampos> validation(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroValidacaoCampos erro = new ErroValidacaoCampos();
		erro.setTimestamp(Instant.now());
		erro.setStatus(status.value());
		erro.setErro("Erro de validação de campos");
		erro.setMensagem(e.getMessage());
		erro.setPath(request.getRequestURI());

		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			erro.addErro(f.getField(), f.getDefaultMessage());
		}

		return ResponseEntity.status(status).body(erro);
	}

	@ExceptionHandler(CadastradoDocumentoException.class)
	public ResponseEntity<ErroPadrao> documentoJaCadastradoException(CadastradoDocumentoException e,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ErroPadrao erro = new ErroPadrao();

		erro.setTimestamp(Instant.now());
		erro.setStatus(status.value());
		erro.setErro("Cadastro Documento exception");
		erro.setMensagem(e.getMessage());
		erro.setPath(request.getRequestURI());

		return ResponseEntity.status(status).body(erro);
	}

}
