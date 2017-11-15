package view;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Tela {
	
	//Classe que configura a JFrame utilizada e adiciona o contentPane.
	//Essa classe faz as configurações iniciais necessárias para gerear
	//um painel diferente no mesmo frame. Dessa forma as classes de Menu
	//inicial e todas as simulações herdam dessa classe, pois todas são 
	//"telas" e todas precisam dessas configurações.
	
	//As principais strings utilizadas são declaradas
	public static final String TITULO = "Aprenda QEE";
	public static final String MENU = "Menu Inicial";
	public static final String SIMULACAO1 = "Simular Fluxo de Potência Fundamental";
	public static final String SIMULACAO2 = "Simular Distorção Harmônica";
	
	//Para manter o texto de forma padrão foram salvas também as strings
	//de fonte e os valores de tamanho dos caracteres
	public static final String FONTE = "Dialog";
	public static final int TAMANHO_TITULO = 40;
	public static final int TAMANHO_SUBTITULO = 20;
	public static final int TAMANHO_TEXTO = 12;
	
	protected JPanel contentPane;
	protected JFrame janela;
	
	public Tela(JFrame janela)  throws IOException{
		//Configurações iniciais da JFrame
		this.janela = janela;
		janela.setLocationRelativeTo(null);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setTitle(TITULO);
		janela.setSize(800, 720);
		
		//Configurações iniciais do JPanel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder (20, 20, 20, 20));
		contentPane.setLayout(null);
		contentPane.setLocation((janela.getWidth()-contentPane.getWidth())/2,(janela.getHeight()-contentPane.getHeight())/2);
		
		janela.setContentPane(contentPane);
		
	}
	
	//Metodos para acesso a janela e ao contentPane foram necessários
	//com a utilização de interface
	public JPanel getContentPane() {
		return contentPane;
	}
	public JFrame getJanela() {
		return janela;
	}
}
