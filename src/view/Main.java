package src.view;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame janelaPrincipal = new JFrame();
		try {
			MenuInicial menu = new MenuInicial(janelaPrincipal);
			menu.mostrarTela();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
