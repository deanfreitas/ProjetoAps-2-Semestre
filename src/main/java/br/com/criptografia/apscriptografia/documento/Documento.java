package br.com.criptografia.apscriptografia.documento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import br.com.criptografia.apscriptografia.criptografia.CriptografiaRSA;
import br.com.criptografia.apscriptografia.swing.TelaSistema;

/**
 * 
 * @author Gilberto Freitas
 *
 */

public class Documento {
	
	private static BigInteger p;
	private static BigInteger q;
	
	/**
	 * abrir o documento (docx)
	 * 
	 * @param main
	 * @param mostrarMensagem
	 */
	public void abrirArquivo(TelaSistema main, JTextArea mostrarMensagem) {
		JFileChooser abrirArquivo = new JFileChooser();
		int result = abrirArquivo.showOpenDialog(main);

		if (result == JFileChooser.APPROVE_OPTION) {
			File file = abrirArquivo.getSelectedFile();
			JOptionPane.showMessageDialog(null, file.getPath());

			if (file.getName().contains("docx")) {
				lerDocumentoDocx(file, mostrarMensagem);
			}

		} else if (result == JFileChooser.CANCEL_OPTION) {
			System.out.println("Nada foi selecionado");

		} else if (result == JFileChooser.ERROR_OPTION) {
			System.out.println("Ocorreu um erro");
		}
	}
	
	/**
	 * ler o documento(docx)
	 * 
	 * @param file
	 * @param mostrarMensagem
	 */
	@SuppressWarnings("unused")
	private void lerDocumentoDocx(File file, JTextArea mostrarMensagem) {
		XWPFDocument document;
		XWPFWordExtractor wordExtractor;

		try {
			document = new XWPFDocument(new FileInputStream(file));
			wordExtractor = new XWPFWordExtractor(document);

			for (XWPFParagraph paragraph : document.getParagraphs()) {
				if(!paragraph.getText().contains("O primeiro número primo: ")) {
					if(!paragraph.getText().contains("O segundo número primo: ")) {
						if (!mostrarMensagem.getText().equals("")) {
							mostrarMensagem.setText(mostrarMensagem.getText() + "\n" + paragraph.getText());
						} else {
							mostrarMensagem.setText(paragraph.getText());
						}
					} else {
						String numerosPrimos = paragraph.getText().substring(24);
						q = BigInteger.valueOf(Long.parseLong(numerosPrimos));
					}
				} else {
					String numerosPrimos = paragraph.getText().substring(25);
					p = BigInteger.valueOf(Long.parseLong(numerosPrimos));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * escrever informacoes no documento(docx)
	 * 
	 * @param out
	 * @param mostrarMensagem
	 * @param p
	 * @param q
	 * @throws IOException
	 */
	
	@SuppressWarnings("resource")
	private void escreverDocumentoDocx(FileOutputStream out, JTextArea mostrarMensagem) throws IOException {
		XWPFDocument document = new XWPFDocument();
		for (String mensagemFragmentada : mostrarMensagem.getText().split("\n")) {
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText(mensagemFragmentada);
		}
		
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.setText("O primeiro número primo: " + CriptografiaRSA.getPrimeiroNumeroPrimo());
		
		XWPFParagraph paragraph1 = document.createParagraph();
		XWPFRun run1 = paragraph1.createRun();
		run1.setText("O segundo número primo: " + CriptografiaRSA.getSegundoNumeroPrimo());
		
		document.write(out);
		out.close();
	}
	
	/**
	 * Criar e Salvar documento(docx)
	 * 
	 * @param main
	 * @param mostrarMensagem
	 * @param p
	 * @param q
	 */
	public void criarDocumentoDocx(TelaSistema main, JTextArea mostrarMensagem) {
		JFileChooser salvarArquivo = new JFileChooser();
		int result = salvarArquivo.showSaveDialog(main);

		if (result == JFileChooser.APPROVE_OPTION) {
			File file = salvarArquivo.getSelectedFile();
			String criarDocumento = file.getPath().concat(".docx");

			JOptionPane.showMessageDialog(null, "Salvando: " + criarDocumento + "." + "\n");
			try {
				FileOutputStream out = new FileOutputStream(new File(criarDocumento));
				escreverDocumentoDocx(out, mostrarMensagem);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (result == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "Nada foi selecionado");

		} else if (result == JFileChooser.ERROR_OPTION) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro");
		}
	}
	
	public static BigInteger getPrimeiroNumeroPrimo() {
		return p;
	}
	
	public static BigInteger getSegundoNumeroPrimo() {
		return q;
	}
}
