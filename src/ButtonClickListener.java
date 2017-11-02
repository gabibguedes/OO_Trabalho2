package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class ButtonClickListener implements ActionListener {
	private JFrame janela;
	
	public ButtonClickListener(JFrame janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent evento){
		String comando = evento.getActionCommand();
		
		switch (comando) {
			case Tela.SIMULACAO1:
				try {
					Simulacao1 teste = new Simulacao1(janela);
					teste.mostrarTela();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
			case Tela.SIMULACAO2:
				System.out.println("Simulação 2: não implementado");
				break;
			case Tela.SIMULACAO3:
				System.out.println("Simulação 3 não implementado");
				break;
			case Tela.MENU:
//				System.out.println("menu");
				try {
					MenuInicial menu = new MenuInicial(janela);
					menu.mostrarTela();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
		}
			
	}

}
