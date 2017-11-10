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
import javax.swing.border.EmptyBorder;

import controllers.MudarTela;

public class Simulacao extends Tela{

	protected JLabel titulo;
	protected JPanel topoPagina, simulacaoPane, botoes;
	protected JButton voltar, simularResultado;
	protected String simulacao;
	
	public Simulacao(JFrame janela) throws IOException{
		super(janela);
	}

	
	public void preparaSimulacao(String simulacao) {
		contentPane.setLayout(new BorderLayout(3,1));
		
		botoes = new JPanel(new FlowLayout());
		simulacaoPane = new JPanel();
		
		titulo = new JLabel(simulacao, JLabel.CENTER);
		titulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_SUBTITULO));
		
		voltar = new JButton("Voltar ao Menu Principal");
		voltar.setFont(new Font(FONTE, Font.BOLD, TAMANHO_TEXTO));
		voltar.setPreferredSize(new Dimension(250, 25));
		
		voltar.setActionCommand(MENU);
		voltar.addActionListener(new MudarTela(janela));
		
		simularResultado = new JButton("Simular Resultado");
		simularResultado.setFont(new Font(FONTE, Font.BOLD, TAMANHO_TEXTO));
		simularResultado.setPreferredSize(new Dimension(250, 25));
		
		botoes.add(voltar);
		botoes.add(simularResultado);
		

		
		contentPane.add(titulo, BorderLayout.NORTH);
		contentPane.add(simulacaoPane, BorderLayout.CENTER);
		contentPane.add(botoes, BorderLayout.SOUTH);
	}
}
