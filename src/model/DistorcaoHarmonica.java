package model;

import java.util.ArrayList;
import java.util.List;

public class DistorcaoHarmonica {
	//Classe que realiza os calculos da simulação 2
	
	public double amplitudeCFundamental, anguloCFundamental;
	public List<Double> ondaCFundamental, ondaResultado;
	public List<HarmonicoDados> harmonicos; 
	public final double FREQANGULAR = 120 * Math.PI;
	
	public DistorcaoHarmonica() {
		harmonicos = new ArrayList<>();
	}
	
	public void setComponenteFundamental(double amplitudeCFundamental, double anguloCFundamental) {
		//Método que preenche os atributos da Componente Fundamental

		this.amplitudeCFundamental = amplitudeCFundamental;
		this.anguloCFundamental = anguloCFundamental;
	}
	
	public void setHarmonico(int i, double angulo, double amplitude, double ordemHarmonica) {
		//Metodo para acrescentar um novo harmonico na lista a partir
		//de suas variaveis
		HarmonicoDados harmonicosI = new HarmonicoDados(angulo, amplitude, ordemHarmonica);
		harmonicos.add(i, harmonicosI);
	}
	
	//Métodos para os calculos dos pontos das ondas:
	
	//Dos Harmonicos (calcula somente a do harmonico selecionado)
	public List<Double> calcularOndaHarmonica(int i){
		double ponto;
		List<Double> listaPontos;
		listaPontos = new ArrayList<>();
		for(double t =0; t< 200; t++) {
			ponto = vH(i, t);
			listaPontos.add(ponto);
		}
		harmonicos.get(i).setPontosOnda(listaPontos);
		
		return harmonicos.get(i).getPontosOnda();
	}
	
	//Da Componente Fundamental
	public List<Double> calcularOndaCFundamental(){
		ondaCFundamental = new ArrayList<>();
		double ponto;
		
		for(double i=0; i<200; i++) {
			ponto = vT(i);
			ondaCFundamental.add(ponto);
		}
		return ondaCFundamental;
	}
	
	//Da Onda Resultante
	public List<Double> calcularOndaResultado(){
		double somatorio=0.0f, ponto;
		ondaResultado = new ArrayList<>();
		
		for(double t=0; t<200; t++) {
			for(int h=0; h<harmonicos.size(); h++) {
				somatorio += vH(h, t);
			}
			ponto = vT(t) + somatorio;
			ondaResultado.add(ponto);
		}
		return ondaResultado;
	}
	
	public double vT(double t) {
		//Formula dos pontos da Componente Fundamental
		double vT;
		vT = amplitudeCFundamental * Math.cos(Math.toRadians((FREQANGULAR * t) + anguloCFundamental));
		return vT;
	}
	public double vH(int h, double t) {
		//Formula dos pontos dos Harmonicos
		double vH;
		vH = harmonicos.get(h).getAmplitude() * Math.cos(Math.toRadians((harmonicos.get(h).getOrdemHarmonica() *
				FREQANGULAR * t) + harmonicos.get(h).getAngulo()));
		return vH;
	}
	
	public class HarmonicoDados{
		//Classe criada para salvar os dados dos harmonicos, de forma
		//que nã necessite da classe bloco e utilizar o JPanel
		
		double angulo, amplitude, ordemHarmonica;
		List<Double> pontosOnda;
		
		private HarmonicoDados(double angulo, double amplitude, double ordemHarmonica) {
			//Construtor que coloca os valores aos atributos
			this.angulo= angulo;
			this.amplitude = amplitude;
			this.ordemHarmonica = ordemHarmonica;
			pontosOnda = new ArrayList<>();
		}

		public List<Double> getPontosOnda() {
			return pontosOnda;
		}

		public void setPontosOnda(List<Double> pontosOnda) {
			this.pontosOnda = pontosOnda;
		}

		public double getAngulo() {
			return angulo;
		}

		public double getAmplitude() {
			return amplitude;
		}

		public double getOrdemHarmonica() {
			return ordemHarmonica;
		}

		public void setAngulo(double angulo) {
			this.angulo = angulo;
		}

		public void setAmplitude(double amplitude) {
			this.amplitude = amplitude;
		}

		public void setOrdemHarmonica(double ordemHarmonica) {
			this.ordemHarmonica = ordemHarmonica;
		}
	}
}
