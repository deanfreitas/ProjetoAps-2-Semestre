package br.com.criptografia.apscriptografia.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.com.criptografia.apscriptografia.calculo.NumerosPrimos;
import br.com.criptografia.apscriptografia.criptografia.CriptografiaRSA;
import br.com.criptografia.apscriptografia.documento.Documento;

/**
 * 
 * @author Gilberto Freitas
 *
 */

public class TelaSistema extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6173059957602641432L;
	private JButton botaoEscreverMensagem;
	private JButton botaoCriptografarMensagem;
	private JButton botaoDescriptografarMensagem;
	private JButton botaoPegarMensagemDoc;
	private JButton botaoEscreverDoc;
	private JButton botaoEnviarMensagem;
	private JButton botaoApagarMensagem;
	private JTextArea mostrarMensagem;
	private JTextArea escreverMensagem;
	private JLabel titulo;
	private JScrollPane telaMensagem;
	private JScrollPane telaEscreverMensagem;
	private NumerosPrimos numerosPrimos = new NumerosPrimos();
	CriptografiaRSA criptografiaRSA = new CriptografiaRSA(numerosPrimos);

	/**
	 * construtor contem a parte grafica do programa
	 * @throws IOException 
	 */
	public TelaSistema() throws IOException {
		
		setTitle("Criptografia");
		setSize(1200, 750);
		setLocation(100, 0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(new JLabel(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/criptografia.png")))));

		titulo = new JLabel("");
		titulo.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/titulo.png"))));
		titulo.setBounds(100, -40, 0, 0);
		titulo.setSize(1200, 150);
		titulo.setFont(new Font("Courier", Font.BOLD, 40));
		titulo.setForeground(Color.RED);

		mostrarMensagem = new JTextArea();
		mostrarMensagem.setEnabled(false);
		mostrarMensagem.setBackground(Color.BLACK);

		escreverMensagem = new JTextArea();

		telaMensagem = new JScrollPane(mostrarMensagem);
		telaMensagem.setLocation(30, 85);
		telaMensagem.setSize(900, 450);
		telaMensagem.setFont(new Font("arial", Font.BOLD, 20));
		telaMensagem.setForeground(Color.black);

		telaEscreverMensagem = new JScrollPane(escreverMensagem);
		telaEscreverMensagem.setLocation(30, 550);
		telaEscreverMensagem.setSize(900, 100);
		telaEscreverMensagem.setFont(new Font("arial", Font.BOLD, 20));
		telaEscreverMensagem.setForeground(Color.black);
		telaEscreverMensagem.setVisible(false);

		botaoPegarMensagemDoc = new JButton("");
		botaoPegarMensagemDoc.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/botaoblack.png"))));
		botaoPegarMensagemDoc.setRolloverIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/botaoblack1.png"))));
		botaoPegarMensagemDoc.setSize(220, 50);
		botaoPegarMensagemDoc.setLocation(950, 80);
		botaoPegarMensagemDoc.addActionListener(this);
		botaoPegarMensagemDoc.setFocusable(false);
		botaoPegarMensagemDoc.setContentAreaFilled(false);
		botaoPegarMensagemDoc.setBorderPainted(false);

		botaoEscreverMensagem = new JButton("");
		botaoEscreverMensagem.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/botaoblue.png"))));
		botaoEscreverMensagem.setRolloverIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/botaoblue.png"))));
		botaoEscreverMensagem.setSize(220, 50);
		botaoEscreverMensagem.setLocation(950, 140);
		botaoEscreverMensagem.addActionListener(this);
		botaoEscreverMensagem.setFocusable(false);
		botaoEscreverMensagem.setContentAreaFilled(false);
		botaoEscreverMensagem.setBorderPainted(false);

		botaoEscreverDoc = new JButton("");
		botaoEscreverDoc.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/botaoblue2.png"))));
		botaoEscreverDoc.setRolloverIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/botaoblue21.png"))));
		botaoEscreverDoc.setSize(220, 50);
		botaoEscreverDoc.setLocation(950, 200);
		botaoEscreverDoc.addActionListener(this);
		botaoEscreverDoc.setFocusable(false);
		botaoEscreverDoc.setContentAreaFilled(false);
		botaoEscreverDoc.setBorderPainted(false);

		botaoCriptografarMensagem = new JButton("");
		botaoCriptografarMensagem.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/botaored.png"))));
		botaoCriptografarMensagem.setRolloverIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/botaored1.png"))));
		botaoCriptografarMensagem.setSize(220, 50);
		botaoCriptografarMensagem.setLocation(950, 420);
		botaoCriptografarMensagem.addActionListener(this);
		botaoCriptografarMensagem.setContentAreaFilled(false);
		botaoCriptografarMensagem.setBorderPainted(false);

		botaoDescriptografarMensagem = new JButton("");
		botaoDescriptografarMensagem.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/botao.png"))));
		botaoDescriptografarMensagem.setRolloverIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/botao1.png"))));
		botaoDescriptografarMensagem.setSize(220, 50);
		botaoDescriptografarMensagem.setLocation(950, 478);
		botaoDescriptografarMensagem.addActionListener(this);
		botaoDescriptografarMensagem.setBorderPainted(false);
		botaoDescriptografarMensagem.setContentAreaFilled(false);

		botaoApagarMensagem = new JButton("");
		botaoApagarMensagem.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/apagar.png"))));
		botaoApagarMensagem.setRolloverIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/apagar1.png"))));
		botaoApagarMensagem.setSize(220, 50);
		botaoApagarMensagem.setLocation(950, 260);
		botaoApagarMensagem.addActionListener(this);
		botaoApagarMensagem.setFocusable(false);
		botaoApagarMensagem.setContentAreaFilled(false);
		botaoApagarMensagem.setBorderPainted(false);

		botaoEnviarMensagem = new JButton("");
		botaoEnviarMensagem.setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/enviarred.png"))));
		botaoEnviarMensagem.setRolloverIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("resources/enviarred.png"))));
		botaoEnviarMensagem.setSize(150, 150);
		botaoEnviarMensagem.setLocation(1020, 550);
		botaoEnviarMensagem.addActionListener(this);
		botaoEnviarMensagem.setFont(new Font("arial", Font.BOLD, 22));
		botaoEnviarMensagem.setBackground(Color.red);
		botaoEnviarMensagem.setFocusable(true);
		botaoEnviarMensagem.setContentAreaFilled(false);
		botaoEnviarMensagem.setForeground(Color.white);
		botaoEnviarMensagem.setBorderPainted(false);
		botaoEnviarMensagem.setVisible(false);

		getContentPane().setLayout(null);
		getContentPane().add(botaoCriptografarMensagem);
		getContentPane().add(botaoDescriptografarMensagem);
		getContentPane().add(botaoPegarMensagemDoc);
		getContentPane().add(botaoEscreverMensagem);
		getContentPane().add(botaoEscreverDoc);
		getContentPane().add(botaoEnviarMensagem);
		getContentPane().add(titulo);
		getContentPane().add(telaMensagem);
		getContentPane().add(telaEscreverMensagem);
		getContentPane().add(botaoApagarMensagem);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(botaoPegarMensagemDoc)) {
			Documento documento = new Documento();
			documento.abrirArquivo(TelaSistema.this, mostrarMensagem);
		}

		if (e.getSource().equals(botaoEscreverMensagem)) {
			escreverInformacao();
		}

		if (e.getSource().equals(botaoEscreverDoc)) {
			Documento documento = new Documento();
			documento.criarDocumentoDocx(TelaSistema.this, mostrarMensagem);
		}

		if (e.getSource().equals(botaoEnviarMensagem)) {
			enviarMensagem();
			escreverMensagem.setText("");
		}

		if (e.getSource().equals(botaoApagarMensagem)) {
			mostrarMensagem.setText("");
		}

		if (e.getSource().equals(botaoCriptografarMensagem)) {
			criptografiaRSA.criptografarMensagem(mostrarMensagem);
		}

		if (e.getSource().equals(botaoDescriptografarMensagem)) {
			criptografiaRSA.descriptografarMensagem(mostrarMensagem);
		}
	}

	private void escreverInformacao() {
		botaoCriptografarMensagem.setVisible(false);
		botaoDescriptografarMensagem.setVisible(false);
		botaoEscreverDoc.setVisible(false);
		botaoEscreverMensagem.setVisible(false);
		botaoPegarMensagemDoc.setVisible(false);
		botaoApagarMensagem.setVisible(false);
		telaEscreverMensagem.setVisible(true);
		botaoEnviarMensagem.setVisible(true);
	}

	private void enviarMensagem() {
		mostrarMensagem.setText(escreverMensagem.getText());
		botaoCriptografarMensagem.setVisible(true);
		botaoDescriptografarMensagem.setVisible(true);
		botaoEscreverDoc.setVisible(true);
		botaoEscreverMensagem.setVisible(true);
		botaoPegarMensagemDoc.setVisible(true);
		botaoApagarMensagem.setVisible(true);
		telaEscreverMensagem.setVisible(false);
		botaoEnviarMensagem.setVisible(false);
	}
}
