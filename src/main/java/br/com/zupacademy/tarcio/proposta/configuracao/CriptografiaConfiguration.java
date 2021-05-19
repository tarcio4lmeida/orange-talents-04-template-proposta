package br.com.zupacademy.tarcio.proposta.configuracao;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.security.crypto.encrypt.Encryptors;

@Converter
public class CriptografiaConfiguration implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String stringParaEncriptografar) {
		return Encryptors.text("${criptogafia.secret}", "24327924313224345A6E726C516846536E6B77673274716D5A6749487565").encrypt(stringParaEncriptografar);
	}

	@Override
	public String convertToEntityAttribute(String stringParaDesencriptografar) {
		return Encryptors.text("${criptogafia.secret}", "24327924313224345A6E726C516846536E6B77673274716D5A6749487565").decrypt(stringParaDesencriptografar);
	}
}