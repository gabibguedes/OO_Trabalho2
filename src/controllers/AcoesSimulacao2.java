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
				int numOH;
				List<Bloco> listaHarmonicos;
				listaHarmonicos = new ArrayList<>();
				
				try {
					numOH = Integer.parseInt(harmonicos.getNumOH().getValue().toString());
				}catch(NullPointerException e) {
					numOH = 0;
				}
				
				for(int i = 0; i < numOH; i++) {
					Bloco harmonico = new Bloco(simulacao.getEhPar(),Integer.toString(i+1), simulacao);
					listaHarmonicos.add(harmonico);
				}
				harmonicos.setNovaListaHarmonicos(listaHarmonicos);

				break;
			case "impar":
				harmonicos.getRadioButtonImpar().setSelected(true);
				harmonicos.getRadioButtonPar().setSelected(false);
				simulacao.setEhPar(false);
				break;
			case "par":
				harmonicos.getRadioButtonPar().setSelected(true);
				harmonicos.getRadioButtonImpar().setSelected(false);
				simulacao.setEhPar(true);
				break;
			case Simulacao2.COMPFUND:
				try {
					amplitude = Double.parseDouble(painel.getAmplitudeTxt());
					angulo = Double.parseDouble(painel.getAnguloTxt());

					if(amplitude < 0 || amplitude > 220) {
						NumberFormatException e = new NumberFormatException();
						throw e;
					}
					
					calculo = new DistorcaoHarmonica();
					calculo.setComponenteFundamental(amplitude, angulo);
					
					painel.getGrafico().setScores(calculo.calcularOndaCFundamental());
					
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"ERRO: Número inválido!\n\nA amplitude da Componente "
							+ "Fundamental deve ser entre 0 ≤ VRMS ≤ 220 \ne o angulo deve estar em graus.");
				}
				break;
			case Tela.SIMULACAO2:
				calculo = new DistorcaoHarmonica();
				List<Bloco> blocosHarmonicos = new ArrayList<>();
				blocosHarmonicos = simulacao.getHarmonicos().getListaHarmonicos();
				
					
				try {
					for(int i=0; i<blocosHarmonicos.size(); i++) {
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
						
						calculo.setHarmonico(i, angulo, amplitude, ordemHarmonica);
						blocosHarmonicos.get(i).getGrafico().setScores(calculo.calcularOndaHarmonica(i));
						simulacao.getHarmonicos().setListaHarmonicos(blocosHarmonicos);	
					}
					
					amplitudeCF = Double.parseDouble(simulacao.getComponenteFundamental().getAmplitudeTxt());
					anguloCF = Double.parseDouble(simulacao.getComponenteFundamental().getAnguloTxt());

					if(amplitude < 0 || amplitude > 220) {
						NumberFormatException e = new NumberFormatException();
						throw e;
					}
					
					calculo = new DistorcaoHarmonica();
					calculo.setComponenteFundamental(amplitude, angulo);
					
					simulacao.getComponenteFundamental().getGrafico().setScores(calculo.calcularOndaCFundamental());
					
					simulacao.getGraficoResultante().setScores(calculo.calcularOndaResultado());
					
					String amplitudeStr, anguloStr;
					if(anguloCF>=0) {
						anguloStr = "+ "+ anguloCF;
					}else {
						anguloStr = "- "+ (anguloCF * (-1));
					}
					formula = "f(t) = " + amplitudeCF + " cos(ωt "+anguloStr+"°) ";
					
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
