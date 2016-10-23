package br.com.criptografia.apscriptografia.criptografia;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import br.com.criptografia.apscriptografia.calculo.MaximoDivisorComum;
import br.com.criptografia.apscriptografia.calculo.NumerosPrimos;
import br.com.criptografia.apscriptografia.documento.Documento;

/**
 * 
 * @author Gilberto Freitas
 *
 */

public class CriptografiaRSA {

	private static BigInteger p;
	private static BigInteger q;
	
	public CriptografiaRSA(NumerosPrimos numerosPrimos) {
		p = BigInteger.valueOf(numerosPrimos.pegarNumeroPrimo());
		q = BigInteger.valueOf(numerosPrimos.pegarNumeroPrimo());
	}
	
	/**
	 * Esse metodo pega a mensagem real, modifica cada letra num respectivo
	 * numero, Calcula chave Publica, Depois traz a mensagem criptografada.
	 * 
	 * @param mostrarMensagem 
	 */
	public void criptografarMensagem(JTextArea mostrarMensagem) {
		BigInteger n;
		BigInteger e;
		BigInteger numerosTextoCriptografado;
		List<Integer> mensagem = new ArrayList<Integer>();
		TabelaAscii tabelaAscii = new TabelaAscii();
		StringBuilder stringBuilder = new StringBuilder();
		
		n = p.multiply(q);

		mensagem = tabelaAscii.codificarLetras(mostrarMensagem.getText());
		e = calcularChavePublica(p, q);

		mostrarMensagem.setText("");
		for (int numerosTextoOriginal : mensagem) {
			numerosTextoCriptografado = BigInteger.valueOf(numerosTextoOriginal).modPow(e, n);
			stringBuilder.append(numerosTextoCriptografado.toString()).append(".0");
		}
		mostrarMensagem.setText(stringBuilder.toString());
	}
	
	/**
	 * Calcula o máximo divisor comum, para descobrir a chave publica da
	 * criptografia
	 * 
	 * @param p
	 * @param q
	 * @return e
	 */
	public BigInteger calcularChavePublica(BigInteger p, BigInteger q) {
		BigInteger e = null;
		BigInteger z;
		MaximoDivisorComum maximoDivisorComum = new MaximoDivisorComum();
		
		z = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));
		
		for (int segundoNumero = 2; segundoNumero < 100000000; segundoNumero++) {
			if (maximoDivisorComum.MDC(z.longValue(), segundoNumero) == 1) {
				e = BigInteger.valueOf(segundoNumero);
				break;
			}
		}
		return e;
	}
	
	/**
	 * Esse metodo pega a mensagem criptografada, separa cada bloco de numero
	 * correspondente a uma letra, num vetor, Calcula a chave privada, Depois
	 * traz a mensagem descriptografada.
	 * 
	 * @param mostrarMensagem
	 */
	public void descriptografarMensagem(JTextArea mostrarMensagem) {
		BigInteger n;
		BigInteger e;
		BigInteger d;
		String mensagemReal;
		String[] numerosMensagemCriptografada;
		TabelaAscii tabelaAscii = new TabelaAscii();
		StringBuilder stringBuilder = new StringBuilder();
		p = Documento.getPrimeiroNumeroPrimo() != null ? Documento.getPrimeiroNumeroPrimo() : p;
		q = Documento.getSegundoNumeroPrimo() != null ? Documento.getSegundoNumeroPrimo() : q;
		n = p.multiply(q);
		e = calcularChavePublica(p, q);
		d = calcularChavePrivada(p, q, e);
		
		numerosMensagemCriptografada = mostrarMensagem.getText().replace(".0", " ").split(" ");
		mostrarMensagem.setText("");

		try {
			for (String numerosTextoCriptografado : numerosMensagemCriptografada) {
				mensagemReal = tabelaAscii.decodificarLetras(BigInteger.valueOf(Long.parseLong(numerosTextoCriptografado)).modPow(d, n));
				stringBuilder.append(mensagemReal);
			}
			mostrarMensagem.setText(stringBuilder.toString());
		}catch(NumberFormatException numberFormatException) {
			JOptionPane.showMessageDialog(null, "Essa mensagem já estava descriptografada");
		}catch(NullPointerException nullPointerException) {
			JOptionPane.showMessageDialog(null, "Erro ao descriptografar a mensagem");
		}
	}
	
	/**
	 * @param p
	 * @param q
	 * @param e
	 * @return d
	 */
	private BigInteger calcularChavePrivada(BigInteger p, BigInteger q, BigInteger e) {
		BigInteger z;
		
		z = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));
		
		BigInteger d = algoritmoEuclidesEstendido(e, z);
		return d;
	}
	
	/**
	 * @param e
	 * @param z
	 * @return o resultado do calculo do algoritmo Euclides Estendido
	 */
	private BigInteger algoritmoEuclidesEstendido(BigInteger e, BigInteger z) {
		return e.modInverse(z);
	}
	
	public static BigInteger getPrimeiroNumeroPrimo() {
		return p;
	}
	
	public static BigInteger getSegundoNumeroPrimo() {
		return q;
	}
}
