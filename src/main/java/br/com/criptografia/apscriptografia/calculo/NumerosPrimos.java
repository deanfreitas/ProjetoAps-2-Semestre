package br.com.criptografia.apscriptografia.calculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Gilberto Freitas
 *
 */

public class NumerosPrimos {

	private List<Integer> listaNumerosPrimos = new ArrayList<Integer>();
	private int totalNumerosPrimos = 20000;

	/**
	 *  O construtor calcula os primeiros numeros primos, estabelecido pela variavel "totalNumerosPrimos"
	 */
	public NumerosPrimos() {
		int numero = 2;
		int quantidadeNumerosPrimos = 0;
		boolean numeroPrimo;

		while (quantidadeNumerosPrimos < totalNumerosPrimos) {
			numeroPrimo = true;
			for (int i = 0; i < listaNumerosPrimos.size(); i++) {
				if ((numero % listaNumerosPrimos.get(i)) == 0)
					numeroPrimo = false;
			}
			if (numeroPrimo) {
				listaNumerosPrimos.add(numero);
				quantidadeNumerosPrimos++;
			}
			numero++;
		}
	}

	/**
	 * 
	 * @return numeros primos aleatorios, dentro do limite estabelecido pela variavel "totalNumerosPrimos"
	 */
	public long pegarNumeroPrimo() {
		return (long) listaNumerosPrimos.get(new Random().nextInt(totalNumerosPrimos));
	}
}
