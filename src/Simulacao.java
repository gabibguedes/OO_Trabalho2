package src;

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

public class Simulacao extends Tela{

	protected JLabel titulo;
	protected JPanel topoPagina, simulacaoPane, botao;
	protected JButton voltar;
	protected String simulacao;
	
	public Simulacao(JFrame janela) throws IOException{
		super(janela);
	}

	
	public void preparaSimulacao(String simulacao) {
		contentPane.setLayout(new BorderLayout(3,1));
		
		botao = new JPanel(new FlowLayout());
		simulacaoPane = new JPanel();
		
		titulo = new JLabel(simulacao, JLabel.CENTER);
		titulo.setFont(new Font("Dialog", Font.BOLD, 25));
		
		voltar = new JButton("Voltar ao Menu Principal");
		voltar.setFont(new Font("Dialog", Font.BOLD, 10));
		voltar.setPreferredSize(new Dimension(100, 30));
		
		voltar.setActionCommand(MENU);
		voltar.addActionListener(new ButtonClickListener(janela));
		botao.add(voltar);
		

		
		contentPane.add(titulo, BorderLayout.NORTH);
		contentPane.add(simulacaoPane, BorderLayout.CENTER);
		contentPane.add(voltar, BorderLayout.SOUTH);
	}
}
