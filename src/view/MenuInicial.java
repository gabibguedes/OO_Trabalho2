package src.view;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import src.controllers.MudarTela;

import javax.swing.JButton;

public class MenuInicial extends Tela{

	private JLabel titulo, descricao;
	private JPanel simulacoes;

	private static final String DESCRICAO = "Escolha uma das opções abaixo e faça a sua simulação.";
	
	public MenuInicial(JFrame janela) throws IOException{
		super(janela);
		preparaGUI();
	}
	
	private void preparaGUI() {
		contentPane.setBorder(new EmptyBorder (40, 40, 40, 40));
		contentPane.setLayout(new GridLayout(3,1));
		
		titulo = new JLabel(TITULO, JLabel.CENTER);
		descricao = new JLabel(DESCRICAO, JLabel.LEFT);
		titulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TITULO));
		descricao.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
		
		simulacoes = new JPanel();
		simulacoes.setLayout(new FlowLayout());
		
		contentPane.add(titulo);
		contentPane.add(descricao);
		contentPane.add(simulacoes);
	}
	
	public void mostrarTela() {

		
		JButton simulacao1 = new JButton(SIMULACAO1);
		JButton simulacao2 = new JButton(SIMULACAO2);
		JButton simulacao3 = new JButton(SIMULACAO3);
		
		simulacao1.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
		simulacao2.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
		simulacao3.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
		
		simulacao1.setPreferredSize(new Dimension(350, 25));
		simulacao2.setPreferredSize(new Dimension(350, 25));
		simulacao3.setPreferredSize(new Dimension(350, 25));
		
		simulacao1.setActionCommand(SIMULACAO1);
		simulacao2.setActionCommand(SIMULACAO2);
		simulacao3.setActionCommand(SIMULACAO3);
		
		simulacao1.addActionListener(new MudarTela(janela));
		simulacao2.addActionListener(new MudarTela(janela));
		simulacao3.addActionListener(new MudarTela(janela));
		
		simulacoes.add(simulacao1);
		simulacoes.add(simulacao2);
		simulacoes.add(simulacao3);
	
		contentPane.setVisible(true);
		janela.setVisible(true);

	}
		
}
