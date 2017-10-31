package src;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Tela extends JFrame{
	public static final String TITULO = "Aprenda QEE";
	public static final String MENU = "Menu Inicial";
	public static final String SIMULACAO1 = "Simular Fluxo de Potência Fundamental";
	public static final String SIMULACAO2 = "Simular Distorção Harmonica";
	public static final String SIMULACAO3 = "Simular Fluxo de Potência Harmonico";
	
	protected JPanel contentPane;
	
	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(TITULO);
		setBounds(100, 100, 800, 800);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder (20, 20, 20, 20));
		setContentPane(contentPane);
		
	}
}
