package src.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.model.FluxoDePotenciaFundamental;
import src.view.Simulacao1;
import src.view.Simulacao1.Bloco;
import src.view.Tela;

public class AcoesSimulacao1 implements ActionListener{
	private Bloco painel;
	private Simulacao1 simulacao;
	private double  amplitudeTensao, amplitudeCorrente, anguloTensao, anguloCorrente;
	private FluxoDePotenciaFundamental calculo;
	
	public AcoesSimulacao1(Bloco painel, Simulacao1 simulacao) {
		this.painel = painel;
		this.simulacao = simulacao;
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		
		switch (comando){
			case Simulacao1.TENSAO:
				try {
					amplitudeTensao = Double.parseDouble(painel.getAmplitudeTxt());
					anguloTensao = Double.parseDouble(painel.getAnguloTxt());

					if(amplitudeTensao <= 0 || amplitudeTensao >= 220) {
						NumberFormatException e = new NumberFormatException();
						throw e;
					}
					
					System.out.println("Forma de onda da Tensão: não implementado");
					
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"ERRO: Numero invalido!\nTensão: 0 ≤ VRMS ≤ 220");
				}
				
				break;
				
			case Simulacao1.CORRENTE:
				try {
					amplitudeCorrente = Double.parseDouble(painel.getAmplitudeTxt());
					anguloCorrente = Double.parseDouble(painel.getAnguloTxt());
					
					if(amplitudeCorrente <= 0 || amplitudeCorrente >= 100) {
						NumberFormatException e = new NumberFormatException();
						throw e;
					}
					
					System.out.println("Forma de onda da Corrente: não implementado");
					
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"ERRO: Numero invalido!\nCorrente: 0 ≤ IRMS ≤ 100");
				}
				
				break;
				
			case Tela.SIMULACAO1:
				try {
					amplitudeTensao = Double.parseDouble(simulacao.getBlocoTensao().getAmplitudeTxt());
					anguloTensao = Double.parseDouble(simulacao.getBlocoTensao().getAnguloTxt());

					amplitudeCorrente = Double.parseDouble(simulacao.getBlocoCorrente().getAmplitudeTxt());
					anguloCorrente = Double.parseDouble(simulacao.getBlocoCorrente().getAnguloTxt());
					
					if(amplitudeCorrente <= 0 || amplitudeCorrente >= 100
							|| amplitudeTensao <= 0 || amplitudeTensao >= 220) {
						NumberFormatException e = new NumberFormatException();
						throw e;
					}
					
//					System.out.println("Simulação 1: não implementado");
					calculo = new FluxoDePotenciaFundamental(amplitudeTensao, amplitudeCorrente, anguloTensao, anguloCorrente);
					simulacao.getResultadoPotencia().setPotAtivaValor(calculo.calcularPotAtiva());
					simulacao.getResultadoPotencia().setPotReativaValor(calculo.calcularPotReativa());
					simulacao.getResultadoPotencia().setPotAparenteValor(calculo.calcularPotAparente());
					simulacao.getResultadoPotencia().setFatPotenciaValor(calculo.calcularFatPotencia());
					
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"ERRO: Numero invalido!\nTensão: 0 ≤ VRMS ≤ 220\nCorrente: 0 ≤ IRMS ≤ 100");
				}
				
				break;
		}

	}
	
}
