package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.FluxoDePotenciaFundamental;
import view.Bloco;
import view.Simulacao2;

public class AcoesSimulacao2 implements ActionListener{
	private Bloco painel;
	private Simulacao2 simulacao;
	private double  angulo, amplitude;
	private FluxoDePotenciaFundamental calculo;
	
	public AcoesSimulacao2(Bloco bloco, Simulacao2 simulacao) {
		this.painel = bloco;
		this.simulacao = simulacao;
	}
	
	public AcoesSimulacao2(Simulacao2 simulacao) {
		this.simulacao = simulacao;
	}

	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		System.out.println("Nao implementado");
	}
}
