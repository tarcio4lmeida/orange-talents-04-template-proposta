package br.com.zupacademy.tarcio.proposta.cadastro_associacao_paypal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarteirasPermitidasValidator implements ConstraintValidator<CarteirasPermitidas, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String carteirasPermitidas[] = {"PAYPAL", "SAMSUNG PAY"}; 
		// hardcode, esse dado pode vir do banco no futuro.
		for (String carteira : carteirasPermitidas) {
			if (carteira.equals(value.toUpperCase())) {
				return true;
			}
			
		}
		return false;
	}
}