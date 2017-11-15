package view;
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

import controllers.MudarTela;

import javax.swing.JButton;

public class MenuInicial extends Tela{
	//Classe que monta a tela do menu inicial
	
	private JLabel titulo, descricao;
	private JPanel simulacoes;

	private static final String DESCRICAO = "Escolha uma das opções abaixo e faça a sua simulação.";
	
	public MenuInicial(JFrame janela) throws IOException{
		super(janela);
		preparaGUI();
	}
	
	private void preparaGUI() {
		//O content pane é dividido em 3, sendo 	
		contentPane.setBorder(new EmptyBorder (40, 40, 40, 40));
		contentPane.setLayout(new GridLayout(3,1));
		
		//1a parte = titulo da tela;
		titulo = new JLabel(TITULO, JLabel.CENTER);
		titulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TITULO));
		contentPane.add(titulo);
		
		//2a parte = descrição da aplicação;
		descricao = new JLabel(DESCRICAO, JLabel.LEFT);
		descricao.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
		contentPane.add(descricao);
		
		//3a parte = botões para as simulações.
		simulacoes = new JPanel();
		simulacoes.setLayout(new FlowLayout());	
		contentPane.add(simulacoes);
	}
	
	public void mostrarTela() {
		//Para os botões das simulações foi criado o JPanel simulacoes
		
		JButton simulacao1 = new JButton(SIMULACAO1);
		JButton simulacao2 = new JButton(SIMULACAO2);
		
		simulacao1.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
		simulacao2.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
		
		simulacao1.setPreferredSize(new Dimension(350, 25));
		simulacao2.setPreferredSize(new Dimension(350, 25));
		
		simulacao1.setActionCommand(SIMULACAO1);
		simulacao2.setActionCommand(SIMULACAO2);
		
		//Foi criado uma classe MudarTela para ser utilizada como
		//ButtonClickListener e chamar as próximas telas escolhidas
		//pelo usuario
		simulacao1.addActionListener(new MudarTela(janela));
		simulacao2.addActionListener(new MudarTela(janela));
		
		simulacoes.add(simulacao1);
		simulacoes.add(simulacao2);
	
		contentPane.setVisible(true);
		janela.setVisible(true);

	}
		
}
