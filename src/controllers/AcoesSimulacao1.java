package controllers;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.FluxoDePotenciaFundamental;
import view.Bloco;
import view.Simulacao1;
import view.Simulacao;
import view.Tela;

public class AcoesSimulacao1 implements ActionListener{
	//Classe para ser usada como o ButtonClickListener da Simulação1
	private Bloco painel;
	private Simulacao1 simulacao;
	private double  angulo, amplitude;
	private FluxoDePotenciaFundamental calculo;
	
	public AcoesSimulacao1(Bloco bloco, Simulacao1 simulacao) {
		this.painel = bloco;
		this.simulacao = simulacao;
	}
	
	public AcoesSimulacao1(Simulacao1 simulacao) {
		this.simulacao = simulacao;
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		//O comando definido no ActionCommand escolhe a ação a ser realizada.
		
		//Os metodos try catch utilizados garantem que os valores estão dentro
		//dos padrões estabelacidos
		
		switch (comando){
			case Simulacao1.TENSAO:
				//Calcula a onda do bloco Tensão
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
					JOptionPane.showMessageDialog(null,"ERRO: Numero invalido!\n\nA amplitude da tensão deve ser entre 0 ≤ VRMS ≤ 220 \ne o angulo deve estar em graus.");
				}
				
				break;
				
			case Simulacao1.CORRENTE:
				//Calcula a onda do bloco Corrente
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
					JOptionPane.showMessageDialog(null,"ERRO: Numero invalido!\n\nA amplitude da corrente deve ser entre 0 ≤ IRMS ≤ 100 \ne o angulo deve estar em graus.");
				}
				
				break;
				
			case Tela.SIMULACAO1:
				//Calcula as ondas dos blocos Tensão e Corrente, além de calcular os resultados númericos
				//e os gráficos resultantes
				
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
					
					//Calcula os resultados númericos
					calculo = new FluxoDePotenciaFundamental(amplitudeTensao, amplitudeCorrente, anguloTensao, anguloCorrente);
					simulacao.getResultadoPotencia().setPotAtivaValor(calculo.calcularPotAtiva());
					simulacao.getResultadoPotencia().setPotReativaValor(calculo.calcularPotReativa());
					simulacao.getResultadoPotencia().setPotAparenteValor(calculo.calcularPotAparente());
					simulacao.getResultadoPotencia().setFatPotenciaValor(calculo.calcularFatPotencia());
					
					//Calcula as ondas de Tensão, Corrente e Resultante
					simulacao.getBlocoTensao().getGrafico().setScores(calculo.calcularOndaTensao());
					simulacao.getBlocoCorrente().getGrafico().setScores(calculo.calcularOndaCorrente());
					simulacao.getResultadoPotencia().getGrafico().setScores(calculo.calcularOnda());
					
					//Calcula o triangulo de potência
					double x = calculo.calcularPotAtiva();
					double y = calculo.calcularPotReativa();
					
					//Limites estabelecidos e valores divididos para que as linhas fiquem dentro
					//do painel do grafico
					if(x>10000 || y>10000 || x < -10000|| y< -10000) {
						simulacao.getResultadoPotencia().getTriangulo().setScores(x/1000, y/1000);
					}else if(x>5000 || y>5000 || x< -5000|| y< -5000) {
						simulacao.getResultadoPotencia().getTriangulo().setScores(x/250, y/250);
					}
					else if(x>1000 || y>1000 || x< -1000|| y< -1000) {
						simulacao.getResultadoPotencia().getTriangulo().setScores(x/100, y/100);
					}else if(x>500 || y>500 || x< -500|| y< -500){
						simulacao.getResultadoPotencia().getTriangulo().setScores(x/10 , y/10);
					}else {
						simulacao.getResultadoPotencia().getTriangulo().setScores(x/5 , y/5);
					}
					
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"ERRO: Numero invalido!\n\nA amplitude da tensão deve ser entre 0 ≤ VRMS ≤ 220, \nda corrente entre 0 ≤ IRMS ≤ 100 e o angulo deve estar\nem graus.");
				}
				
				break;
		}

	}
	
}
