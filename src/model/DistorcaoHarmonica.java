package model;

import java.util.ArrayList;
import java.util.List;

public class DistorcaoHarmonica {
	public double amplitudeCFundamental, anguloCFundamental;
	public List<Double> ondaCFundamental, ondaResultado;
	public List<HarmonicoDados> harmonico; 
	public final double FREQANGULAR = 120 * Math.PI;
	
	public DistorcaoHarmonica() {
		harmonico = new ArrayList<>();
	}
	
	public void setComponenteFundamental(double amplitudeCFundamental, double anguloCFundamental) {
		this.amplitudeCFundamental = amplitudeCFundamental;
		this.anguloCFundamental = anguloCFundamental;
	}
	
	public void setHarmonico(int i, double angulo, double amplitude, double ordemHarmonica) {
		HarmonicoDados harmonicoI = new HarmonicoDados(angulo, amplitude, ordemHarmonica);
		harmonico.add(i, harmonicoI);
	}
	
	public List<Double> calcularOndaHarmonica(int i){
		double ponto;
		List<Double> listaPontos;
		listaPontos = new ArrayList<>();
		for(double t =0; t< 200; t++) {
			ponto = vH(i, t);
			listaPontos.add(ponto);
		}
		harmonico.get(i).setPontosOnda(listaPontos);
		
		return harmonico.get(i).getPontosOnda();
	}
	public List<Double> calcularOndaCFundamental(){
		ondaCFundamental = new ArrayList<>();
		double ponto;
		
		for(double i=0; i<200; i++) {
			ponto = vT(i);
			ondaCFundamental.add(ponto);
		}
		return ondaCFundamental;
	}
	public double vT(double t) {
		double vT;
		vT = amplitudeCFundamental * Math.cos(Math.toRadians((FREQANGULAR * t) + anguloCFundamental));
		return vT;
	}
	public double vH(int h, double t) {
		double vH;
		vH = harmonico.get(h).getAmplitude() * Math.cos(Math.toRadians((harmonico.get(h).getOrdemHarmonica() *
				FREQANGULAR * t) + harmonico.get(h).getAngulo()));
		return vH;
	}
	
	public List<Double> calcularOndaResultado(){
		double somatorio=0.0f, ponto;
		ondaResultado = new ArrayList<>();
		
		for(double t=0; t<200; t++) {
			for(int h=0; h<harmonico.size(); h++) {
				somatorio += vH(h, t);
			}
			ponto = vT(t) + somatorio;
			ondaResultado.add(ponto);
		}
		return ondaResultado;
	}
	
	public class HarmonicoDados{
		double angulo, amplitude, ordemHarmonica;
		List<Double> pontosOnda;
		
		private HarmonicoDados(double angulo, double amplitude, double ordemHarmonica) {
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
