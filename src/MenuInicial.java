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

public class MenuInicial extends JFrame{
	private JLabel titulo, descricao;
	private JPanel	contentPane, simulacoes;
	
	public static final String TITULO = "Aprenda QEE";
	public static final String DESCRICAO = "Escolha uma das opções abaixo e faça a sua simulação.";
	
	public static final String SIMULACAO1 = "Simular Fluxo de Potência Fundamental";
	public static final String SIMULACAO2 = "Simular Distorção Harmonica";
	public static final String SIMULACAO3 = "Simular Fluxo de Potência Harmonico";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuInicial frame = new MenuInicial();
					frame.mostrarMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MenuInicial() {
		preparaGUI();
	}
	
	private void preparaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(TITULO);
		setBounds(400, 400, 450, 450);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder (10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3,1));
		
		titulo = new JLabel("", JLabel.LEFT);
		descricao = new JLabel("", JLabel.LEFT);
		
		simulacoes = new JPanel();
		simulacoes.setLayout(new FlowLayout());
	}
	
	public void mostrarMenu() {
		titulo.setText(TITULO);
		titulo.setFont(new Font("Dialog", Font.PLAIN, 40));
		descricao.setText(DESCRICAO);
		descricao.setFont(new Font("Dialog", Font.PLAIN, 14));
		
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
		
		contentPane.add(titulo);
		contentPane.add(descricao);
		contentPane.add(simulacoes);
	}
	
	private class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent evento){
			String comando = evento.getActionCommand();
			
			switch (comando) {
				case SIMULACAO1:
					System.out.println("Simulação 1: não implementado");
					break;
				case SIMULACAO2:
					System.out.println("Simulação 2: não implementado");
					break;
				case SIMULACAO3:
					System.out.println("Simulação 3: não implementado");
					break;
			}
			
				
		}
	}
		
}
