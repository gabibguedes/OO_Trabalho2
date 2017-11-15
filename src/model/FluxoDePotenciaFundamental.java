package model;

import java.util.ArrayList;
import java.util.List;

public class FluxoDePotenciaFundamental {
	//Classe para os calculos do fluxo de potência fundamental da Simulação1
	
	private double amplitudeTensao, amplitudeCorrente, anguloTensao, anguloCorrente;
	private double potAtiva, potReativa, potAparente, fatPotencia;
	private List<Double> pontosGrafico, pontosTensao, pontosCorrente;
	
	public final double FREQANGULAR = 120 * Math.PI;
	
	public FluxoDePotenciaFundamental() {}
	
	//Construtor com todos os valores iniciais
	public FluxoDePotenciaFundamental(double amplitudeTensao, double amplitudeCorrente,
		double anguloTensao, double anguloCorrente) {
		this.amplitudeTensao = amplitudeTensao;
		this.amplitudeCorrente = amplitudeCorrente;
		this.anguloTensao = anguloTensao;
		this.anguloCorrente = anguloCorrente;
	}

	//Instancia apenas os valores da tensão
	public void setTensao(double amplitudeTensao, double anguloTensao) {
		this.amplitudeTensao = amplitudeTensao;
		this.anguloTensao = anguloTensao;
	}
	
	//Instancia apenas os valores da corrente
	public void setCorrente(double amplitudeCorrente, double anguloCorrente) {
		this.amplitudeCorrente = amplitudeCorrente;
		this.anguloCorrente = anguloCorrente;
	}
	
	//Metodos para os calculos dos valores resultantes:
	
	public double calcularPotAtiva() {
		potAtiva = amplitudeTensao * amplitudeCorrente * Math.cos(Math.toRadians(anguloTensao - anguloCorrente));
		return potAtiva;
	}

	public double calcularPotReativa() {
		potReativa = amplitudeTensao * amplitudeCorrente * Math.sin(Math.toRadians(anguloTensao - anguloCorrente));
		return potReativa;
	}

	public double calcularPotAparente() {
		potAparente = amplitudeTensao * amplitudeCorrente;
		return potAparente;
	}

	public double calcularFatPotencia() {
		if(anguloTensao == anguloCorrente) {
			fatPotencia = 1;
		}else {
			fatPotencia = Math.cos(Math.toRadians(anguloTensao - anguloCorrente));
		}
		return fatPotencia;
	}

	//Métodos para os calculos das ondas:
	
	public List<Double> calcularOndaTensao(){
		double ponto;
		pontosTensao = new ArrayList<>();
		
		for(double t = 0; t< 200; t++) {
			ponto = v(t);
			pontosTensao.add(ponto);
		}
		return pontosTensao;
	}
	
	public List<Double> calcularOndaCorrente(){
		double ponto;
		pontosCorrente = new ArrayList<>();
		
		for(double t = 0; t< 200; t++) {
			ponto = i(t);
			pontosCorrente.add(ponto);
		}
		return pontosCorrente;
	}
	
	public List<Double> calcularOnda(){
		double ponto;
		pontosGrafico = new ArrayList<>();
		
		for(double t = 0; t<100; t++) {
			ponto = v(t) * i(t);
			pontosGrafico.add(ponto);
		}
		return pontosGrafico;
	}
	
	private double v(double x) {
		double v;
		v = amplitudeTensao * Math.cos(Math.toRadians((FREQANGULAR * x) + anguloTensao));
		return v;
	}
	
	private double i(double x) {
		double i;
		i = amplitudeCorrente * Math.cos(Math.toRadians((FREQANGULAR * x) + anguloCorrente));
		return i;
	}
}
