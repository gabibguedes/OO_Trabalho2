import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class MenuInicial extends JFrame{
	private JLabel titulo, descricao;
	private JPanel	contentPane, simulacoes;
	
	public static final String TITULO = "Aprenda QEE";
	public static final String DESCRICAO = "Escolha uma das opções abaixo e faça a sua simulação!";
	
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
		contentPane.setBorder(new EmptyBorder (5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3,1));
		
		titulo = new JLabel("", JLabel.LEFT);
		descricao = new JLabel("", JLabel.LEFT);
		
		simulacoes = new JPanel();
		simulacoes.setLayout(new GridLayout(3,2));
	}
	
	public void mostrarMenu() {
		titulo.setText(TITULO);
		titulo.setFont(new Font("Dialog", Font.PLAIN, 40));
		descricao.setText(DESCRICAO);
		
		JButton simulacao1 = new JButton(SIMULACAO1);
		JButton simulacao2 = new JButton(SIMULACAO2);
		JButton simulacao3 = new JButton(SIMULACAO3);
		
		simulacoes.add(simulacao1);
		simulacoes.add(simulacao2);
		simulacoes.add(simulacao3);
		
		contentPane.add(titulo);
		contentPane.add(descricao);
		contentPane.add(simulacoes);
	}
		
}

