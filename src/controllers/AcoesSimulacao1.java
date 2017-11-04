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
	private double  angulo, amplitude;
	private FluxoDePotenciaFundamental calculo;
	
	public AcoesSimulacao1(Bloco painel, Simulacao1 simulacao) {
		this.painel = painel;
		this.simulacao = simulacao;
	}
	
	public AcoesSimulacao1(Simulacao1 simulacao) {
		this.simulacao = simulacao;
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		
		switch (comando){
			case Simulacao1.TENSAO:
				try {
					amplitude = Double.parseDouble(painel.getAmplitudeTxt());
					angulo = Double.parseDouble(painel.getAnguloTxt());

					if(amplitude < 0 || amplitude > 220) {
						NumberFormatException e = new NumberFormatException();
						throw e;
					}
					
					calculo = new FluxoDePotenciaFundamental();
					calculo.setTensao(amplitude, angulo);
					
					painel.getGrafico().setScores(calculo.calcularOndaTensao());
					
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"ERRO: Numero invalido!\nTensão: 0 ≤ VRMS ≤ 220");
				}
				
				break;
				
			case Simulacao1.CORRENTE:
				try {
					amplitude = Double.parseDouble(painel.getAmplitudeTxt());
					angulo = Double.parseDouble(painel.getAnguloTxt());
					
					if(amplitude < 0 || amplitude > 100) {
						NumberFormatException e = new NumberFormatException();
						throw e;
					}
					
					calculo = new FluxoDePotenciaFundamental();
					calculo.setCorrente(amplitude, angulo);
					
					painel.getGrafico().setScores(calculo.calcularOndaCorrente());
					
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"ERRO: Numero invalido!\nCorrente: 0 ≤ IRMS ≤ 100");
				}
				
				break;
				
			case Tela.SIMULACAO1:
				try {
					double  amplitudeTensao, amplitudeCorrente, anguloTensao, anguloCorrente;
					
					amplitudeTensao = Double.parseDouble(simulacao.getBlocoTensao().getAmplitudeTxt());
					anguloTensao = Double.parseDouble(simulacao.getBlocoTensao().getAnguloTxt());

					amplitudeCorrente = Double.parseDouble(simulacao.getBlocoCorrente().getAmplitudeTxt());
					anguloCorrente = Double.parseDouble(simulacao.getBlocoCorrente().getAnguloTxt());
					
					if(amplitudeCorrente < 0 || amplitudeCorrente > 100
							|| amplitudeTensao < 0 || amplitudeTensao > 220) {
						NumberFormatException e = new NumberFormatException();
						throw e;
					}

					calculo = new FluxoDePotenciaFundamental(amplitudeTensao, amplitudeCorrente, anguloTensao, anguloCorrente);
					simulacao.getResultadoPotencia().setPotAtivaValor(calculo.calcularPotAtiva());
					simulacao.getResultadoPotencia().setPotReativaValor(calculo.calcularPotReativa());
					simulacao.getResultadoPotencia().setPotAparenteValor(calculo.calcularPotAparente());
					simulacao.getResultadoPotencia().setFatPotenciaValor(calculo.calcularFatPotencia());
					
					simulacao.getBlocoTensao().getGrafico().setScores(calculo.calcularOndaTensao());
					simulacao.getBlocoCorrente().getGrafico().setScores(calculo.calcularOndaCorrente());
					simulacao.getResultadoPotencia().getGrafico().setScores(calculo.calcularOnda());
					
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"ERRO: Numero invalido!\nTensão: 0 ≤ VRMS ≤ 220\nCorrente: 0 ≤ IRMS ≤ 100");
				}
				
				break;
		}

	}
	
}
