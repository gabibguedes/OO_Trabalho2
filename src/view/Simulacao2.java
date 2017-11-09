package view;

import java.io.IOException;

import javax.swing.JFrame;

import controllers.AcoesSimulacao1;
import controllers.AcoesSimulacao2;

public class Simulacao2 extends Simulacao{

	public Simulacao2(JFrame janela) throws IOException {
		super(janela);
		preparaSimulacao(SIMULACAO2);
		simularResultado.setActionCommand(SIMULACAO2);
		simularResultado.addActionListener(new AcoesSimulacao2(this));
	}
	
	public void mostrarTela() {		
		janela.setVisible(true);
		contentPane.setVisible(true);
	}

}
