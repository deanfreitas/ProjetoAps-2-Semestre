package br.com.criptografia.apscriptografia.calculo;

/**
 * 
 * @author Gilberto Freitas
 *
 */

public class MaximoDivisorComum {

	/**
	 * Calculo do máximo divisor comum
	 * 
	 * @param primeiro numero para calcular o MDC
	 * @param segundo numero para calcular o MDC
	 * @return o resultado do calculo MDC dos dois numeros fornecidos
	 */
	public long MDC(long primeiroNumero, long segundoNumero) {
		long resto;

		while (segundoNumero != 0) {
			resto = primeiroNumero % segundoNumero;
			primeiroNumero = segundoNumero;
			segundoNumero = resto;
		}
		return primeiroNumero;
	}
}
