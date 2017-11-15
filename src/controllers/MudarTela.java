package controllers;

import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class MudarTela implements ActionListener {	
	//Classe para alterações de tela, é chamada no Menu Inicial e 
	//no botão "voltar" nas simulações. Essa classe recebe a JFrame
	//que sera usada ao instanciar novas telas.
	
	private JFrame janela;
	
	public MudarTela(JFrame janela) {
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent evento){
		String comando = evento.getActionCommand();
		
		//O comando estabelecido no ActionCommand define a ação a
		//ser realizada.
		
		switch (comando) {
			case Tela.SIMULACAO1:
				//Instancia e abre a simulação 1
				try {
					Simulacao1 simulacao = new Simulacao1(janela);
					simulacao.mostrarTela(simulacao);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
			case Tela.SIMULACAO2:
				//Instancia e abre a simulação 2
				try {
					Simulacao2 simulacao = new Simulacao2(janela);
					simulacao.mostrarTela(simulacao);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case Tela.MENU:
				//Instancia e abre o menu inicial
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
