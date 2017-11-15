package controllers;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.DistorcaoHarmonica;
import model.FluxoDePotenciaFundamental;
import view.Bloco;
import view.Simulacao2;
import view.Simulacao2.Harmonicos;
import view.Tela;

public class AcoesSimulacao2 implements ActionListener{
	//Classe utilizada para ActionListener da Simulação2
	private Bloco painel;
	private Simulacao2 simulacao;
	private double  angulo, amplitude, ordemHarmonica, anguloCF, amplitudeCF;
	private DistorcaoHarmonica calculo;
	private Harmonicos harmonicos;
	private String formula;
	
	public AcoesSimulacao2(Bloco bloco, Simulacao2 simulacao) {
		this.painel = bloco;
		this.simulacao = simulacao;
	}
	
	public AcoesSimulacao2(Simulacao2 simulacao) {
		this.simulacao = simulacao;
	}
	
	public AcoesSimulacao2(Simulacao2 simulacao, Harmonicos harmonicos) {
		this.simulacao = simulacao;
		this.harmonicos = harmonicos;
	}

	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		switch(comando) {
			case Simulacao2.HARMONICOS:
				//Esta ação será executada nos casos em que o usuário clica
				//no botão OK após ter selecionado a quantidade e a paridade
				//dos harmonicos a serem executados.
				int numOH;
				List<Bloco> listaHarmonicos;
				listaHarmonicos = new ArrayList<>();
				
				try {
					numOH = Integer.parseInt(harmonicos.getNumOH().getValue().toString());
					
					//A partir do número de harmonicos (numOH) um for é feito para
					//criar a quantidade solicitada de blocos e coloca-los em uma 
					//lista.
					for(int i = 0; i < numOH; i++) {
						Bloco harmonico = new Bloco(true, simulacao.getEhPar(),Integer.toString(i+1), simulacao);
						listaHarmonicos.add(harmonico);
					}
					
					//Após a lista ser formada ela vai para um método que colocará
					//esses elementos na tela.
					harmonicos.setNovaListaHarmonicos(listaHarmonicos);
				}catch(NullPointerException e) {
					JOptionPane.showMessageDialog(null,"ERRO: Número de Ordens Harmônicas inválido.");
				}
				break;
				
			case "impar":
				//Esta ação é chamada ao clicar no RadioButton ímpar, ela
				//desceleciona o Radiobutton par caso esteja selecionado
				//e atualiza a variavel para controlar a paridade
				
				harmonicos.getRadioButtonImpar().setSelected(true);
				harmonicos.getRadioButtonPar().setSelected(false);
				simulacao.setEhPar(false);
				break;
				
			case "par":
				//Assim como na ação anterior, esta descelaciona o outro
				//RadioButton e atualiza a variavel de controle de paridade
				
				harmonicos.getRadioButtonPar().setSelected(true);
				harmonicos.getRadioButtonImpar().setSelected(false);
				simulacao.setEhPar(true);
				break;
				
			case Simulacao2.COMPFUND:
				try {
					//Esta ação atualiza o gráfico do Bloco Componente Fundamental
					amplitude = Double.parseDouble(painel.getAmplitudeTxt());
					angulo = Double.parseDouble(painel.getAnguloTxt());

					if(amplitude < 0 || amplitude > 220) {
						NumberFormatException e = new NumberFormatException();
						throw e;
					}
					
					//A classe de calculos é instanciada
					calculo = new DistorcaoHarmonica();
					calculo.setComponenteFundamental(amplitude, angulo);
					
					//Os calculos são feitos e colocados no gráfico
					painel.getGrafico().setScores(calculo.calcularOndaCFundamental());
					
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"ERRO: Número inválido!\n\nA amplitude da Componente "
							+ "Fundamental deve ser entre 0 ≤ VRMS ≤ 220 \ne o angulo deve estar em graus.");
				}
				break;
				
			case Tela.SIMULACAO2:
				//Esta ação atualiza os gráficos de Componente fundamental, 
				//dos Harmonicos e o gráfico resultante, além de estruturar
				//a String da formula
				
				calculo = new DistorcaoHarmonica();
				List<Bloco> blocosHarmonicos = new ArrayList<>();
				blocosHarmonicos = simulacao.getHarmonicos().getListaHarmonicos();
					
				try {
					for(int i=0; i<blocosHarmonicos.size(); i++) {
						//Variaveis são estabelecidas e verificadas se estão de 
						//acordo com o padrão exigido
						
						amplitude = Double.parseDouble(blocosHarmonicos.get(i).getAmplitudeTxt());
						angulo = Double.parseDouble(blocosHarmonicos.get(i).getAnguloTxt());
						ordemHarmonica = Double.parseDouble(blocosHarmonicos.get(i).getOrdemHarmonicaTxt());

						if(amplitude < 0 || amplitude > 220) {
							NumberFormatException e = new NumberFormatException();
							throw e;
						}
						
						int resto = (int) (ordemHarmonica) % 2;
						if((simulacao.getEhPar() && (resto != 0))||((!simulacao.getEhPar())&&(resto == 0))) {
							NumberFormatException e = new NumberFormatException();
							throw e;
						}
						
						//A lista de harmonicos é passada para o calculo
						calculo.setHarmonico(i, angulo, amplitude, ordemHarmonica);
						//Os pontos da onda são calculados e o gráfico é atualizado
						blocosHarmonicos.get(i).getGrafico().setScores(calculo.calcularOndaHarmonica(i));
						simulacao.getHarmonicos().setListaHarmonicos(blocosHarmonicos);	
					}
					
					amplitudeCF = Double.parseDouble(simulacao.getComponenteFundamental().getAmplitudeTxt());
					anguloCF = Double.parseDouble(simulacao.getComponenteFundamental().getAnguloTxt());

					if(amplitude < 0 || amplitude > 220) {
						NumberFormatException e = new NumberFormatException();
						throw e;
					}
					
					//Variaveis da Componente fundamental são passadas para o calculo
					calculo = new DistorcaoHarmonica();
					calculo.setComponenteFundamental(amplitudeCF, anguloCF);
					
					//Onda da Componente Fundamental calculada e gráfico atualizado
					simulacao.getComponenteFundamental().getGrafico().setScores(calculo.calcularOndaCFundamental());
					
					//Calculo e atualização do gráfico resultante
					simulacao.getGraficoResultante().setScores(calculo.calcularOndaResultado());
					
					//Formação da String da formula:
					
					//Componente Fundamental é passada para a String
					String amplitudeStr, anguloStr;
					if(anguloCF>=0) {
						anguloStr = "+ "+ anguloCF;
					}else {
						anguloStr = "- "+ (anguloCF * (-1));
					}
					formula = "f(t) = " + amplitudeCF + " cos(ωt "+anguloStr+"°) ";
					
					//Harmonicos são passados para a string:
					for(int i=0; i<simulacao.getHarmonicos().getListaHarmonicos().size(); i++) {
						amplitude = Double.parseDouble(blocosHarmonicos.get(i).getAmplitudeTxt());
						angulo = Double.parseDouble(blocosHarmonicos.get(i).getAnguloTxt());
						ordemHarmonica = Double.parseDouble(blocosHarmonicos.get(i).getOrdemHarmonicaTxt());
						
						if(amplitude>=0) {
							amplitudeStr = "+ "+ amplitude;
						}else {
							amplitudeStr = "- "+ amplitude;
						}
						if(angulo>=0) {
							anguloStr = "+ "+ angulo;
						}else {
							anguloStr = "- "+ (angulo * (-1));
						}
						
						formula += amplitudeStr + " cos("+ ordemHarmonica +" ωt "+ anguloStr+"°) ";
					}
					
					//String de formula inserida na tela
					simulacao.getFormula().setText(formula);
					
				}
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"ERRO: Número inválido!\n\nA amplitude deve ser entre 0 ≤ VRMS ≤ 220,"
							+ "\no angulo deve estar em graus e a ordem deve\nestar de acordo com a paridade pré-definida.");
				}
				break;
		}
	}
}
