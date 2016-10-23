package br.com.criptografia.apscriptografia.criptografia;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Gilberto Freitas
 *
 */

public class TabelaAscii {

	/**
	 * modificar as letras para números atravez da tabela Ascii(caractere por
	 * caractere), para encriptar a mensagem do usuario.
	 * 
	 * @param mensagem
	 *            real para ser transformada em numeros
	 * @return lista de numeros que representam mensagem real
	 */
	public List<Integer> codificarLetras(String mensagem) {
		List<Integer> letrasCodificadas = new ArrayList<Integer>();
		for (Character letras : mensagem.toCharArray()) {
			for (int i = 10; i <= 245; i++) {
				if (letras.equals((char) i)) {
					letrasCodificadas.add(i);
					break;
				}
			}
		}
		return letrasCodificadas;
	}

	/**
	 * modificar os numeros desencriptado para as letras correspondentes
	 * 
	 * @param numeros para modificar para letras
	 * @return letras que eram os numeros decodificados
	 */
	public String decodificarLetras(BigInteger numerosMensagem) {
		String mensagem = null;
		for (int i = 10; i <= 245; i++) {
			if (numerosMensagem.intValue() == i) {
				Character letrasMensagem = (char) i;
				mensagem = letrasMensagem.toString();
				return mensagem;
			}
		}
		return mensagem;
	}
}
