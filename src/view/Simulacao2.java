package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import controllers.AcoesSimulacao2;
import view.Simulacao1.ResultadoPotencia;

public class Simulacao2 extends Simulacao{
	
	public final String COMPFUND = "Componente Fundamental";
	private Bloco compFund;
	private JPanel harmonicos, configuraHarmonicos;
	private JScrollPane  blocosHarmonicos;

	public Simulacao2(JFrame janela) throws IOException {
		super(janela);
		preparaSimulacao(SIMULACAO2);
		preparaGUI();
	}
	
	public void mostrarTela() {		
		janela.setVisible(true);
		contentPane.setVisible(true);
	}
	
	private void preparaGUI() {
		simulacaoPane.setLayout(new GridLayout(3,1));		
		
		compFund = new Bloco(false, COMPFUND, this);
		harmonicos = new JPanel(new BorderLayout());
		configuraHarmonicos = new JPanel(new GridLayout(2,1));
		blocosHarmonicos = new JScrollPane();
		
		harmonicos.add(configuraHarmonicos, BorderLayout.NORTH);
		harmonicos.add(blocosHarmonicos);

		simularResultado.setActionCommand(SIMULACAO2);
		simularResultado.addActionListener(new AcoesSimulacao2(this));
		
		preparaHarmonicos();
		
		simulacaoPane.add(compFund);
		simulacaoPane.add(harmonicos);
//		simulacaoPane.add();
	}
	
	private void preparaHarmonicos() {
		JLabel titulo, numOH, parImpar;
		JPanel numOHpanel, paridadePanel, imputsUsuario, imputsNumOH, imputsParImpar;
		JSpinner numOHTxt;
		JRadioButton par, impar;
		JButton enter;
		
		titulo = new JLabel("Harmonicos");
		titulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_SUBTITULO));
		configuraHarmonicos.add(titulo);
		
		imputsUsuario = new JPanel(new GridLayout(1,2));
		imputsNumOH = new JPanel(new FlowLayout());
		imputsParImpar = new JPanel(new FlowLayout());
		
		numOH = new JLabel("Número de Ordens Harmônicas:");
		numOH.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
		imputsNumOH.add(numOH);
		
		numOHTxt = new JSpinner();
		numOHTxt.setPreferredSize(new Dimension(50, 25));
		imputsNumOH.add(numOHTxt);
		
		parImpar = new JLabel("Harmonicos:");
		parImpar.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
		imputsParImpar.add(parImpar);
		
		par = new JRadioButton("Pares");
		par.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
		imputsParImpar.add(par);
		
		impar = new JRadioButton("Impares");
		impar.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
		imputsParImpar.add(impar);
		
		imputsUsuario.add(imputsNumOH);
		imputsUsuario.add(imputsParImpar);
		configuraHarmonicos.add(imputsUsuario);
		
	}

}
