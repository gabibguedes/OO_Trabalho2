package view;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Tela {
	public static final String TITULO = "Aprenda QEE";
	public static final String MENU = "Menu Inicial";
	public static final String SIMULACAO1 = "Simular Fluxo de Potência Fundamental";
	public static final String SIMULACAO2 = "Simular Distorção Harmônica";
	
	public static final String FONTE = "Dialog";
	public static final int TAMANHO_TITULO = 40;
	public static final int TAMANHO_SUBTITULO = 20;
	public static final int TAMANHO_TEXTO = 12;
	
	protected JPanel contentPane;
	protected JFrame janela;
	
	public Tela(JFrame janela)  throws IOException{
		this.janela = janela;
		janela.setLocationRelativeTo(null);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setTitle(TITULO);
		janela.setSize(800, 720);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder (20, 20, 20, 20));
		contentPane.setLayout(null);
		contentPane.setLocation((janela.getWidth()-contentPane.getWidth())/2,(janela.getHeight()-contentPane.getHeight())/2);
		
		janela.setContentPane(contentPane);
		
	}
}
