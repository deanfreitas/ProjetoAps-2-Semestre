package br.com.criptografia.apscriptografia;

import java.io.IOException;

import javax.swing.JOptionPane;

import br.com.criptografia.apscriptografia.swing.TelaSistema;

public class Main {

	public static void main(String[] args) {
		try {
			new TelaSistema().setVisible(true);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar as imagens");
		}
	}
}
