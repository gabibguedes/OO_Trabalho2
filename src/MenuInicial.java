package src;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;

public class MenuInicial extends Tela{
	private JLabel titulo, descricao;
	private JPanel simulacoes;

	private static final String DESCRICAO = "Escolha uma das opções abaixo e faça a sua simulação.";
	
	public MenuInicial() {
		preparaGUI();
	}
	
	private void preparaGUI() {
		contentPane.setBorder(new EmptyBorder (40, 40, 40, 40));
		contentPane.setLayout(new GridLayout(3,1));
		
		titulo = new JLabel(TITULO, JLabel.LEFT);
		descricao = new JLabel(DESCRICAO, JLabel.LEFT);
		titulo.setFont(new Font("Dialog", Font.PLAIN, 40));
		descricao.setFont(new Font("Dialog", Font.PLAIN, 14));
		
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
		
		simulacao1.setFont(new Font("Dialog", Font.PLAIN, 14));
		simulacao2.setFont(new Font("Dialog", Font.PLAIN, 14));
		simulacao3.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		simulacao1.setPreferredSize(new Dimension(350, 25));
		simulacao2.setPreferredSize(new Dimension(350, 25));
		simulacao3.setPreferredSize(new Dimension(350, 28));
		
		simulacao1.setActionCommand(SIMULACAO1);
		simulacao2.setActionCommand(SIMULACAO2);
		simulacao3.setActionCommand(SIMULACAO3);
		
		simulacao1.addActionListener(new ButtonClickListener());
		simulacao2.addActionListener(new ButtonClickListener());
		simulacao3.addActionListener(new ButtonClickListener());
		
		simulacoes.add(simulacao1);
		simulacoes.add(simulacao2);
		simulacoes.add(simulacao3);
		
		setVisible(true);
	}
		
}
