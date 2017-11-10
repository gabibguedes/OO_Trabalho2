package controllers;

import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class MudarTela implements ActionListener {
	private JFrame janela;
	
	public MudarTela(JFrame janela) {
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent evento){
		String comando = evento.getActionCommand();
		
		switch (comando) {
			case Tela.SIMULACAO1:
				try {
					Simulacao1 simulacao = new Simulacao1(janela);
					simulacao.mostrarTela();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
			case Tela.SIMULACAO2:
				try {
					Simulacao2 simulacao = new Simulacao2(janela);
					simulacao.mostrarTela();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case Tela.MENU:
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
