package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickListener implements ActionListener {
	public void actionPerformed(ActionEvent evento){
		String comando = evento.getActionCommand();
		
		switch (comando) {
			case Tela.SIMULACAO1:
				Simulacao1 teste = new Simulacao1();
				teste.mostrarTela();
				teste.setVisible(true);
				break;
			case Tela.SIMULACAO2:
				System.out.println("Simulação 2: não implementado");
				break;
			case Tela.SIMULACAO3:
				System.out.println("Simulação 3 não implementado");
				break;
			case Tela.MENU:
//				System.out.println("menu");
				MenuInicial menu = new MenuInicial();
				menu.mostrarTela();
				menu.setVisible(true);
				break;
		}
			
	}

}
