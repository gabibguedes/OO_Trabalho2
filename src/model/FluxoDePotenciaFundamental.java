package src.model;

import java.util.List;

public class FluxoDePotenciaFundamental {
	double amplitudeTensao, amplitudeCorrente, anguloTensao, anguloCorrente;
	double potAtiva, potReativa, potAparente, fatPotencia;
	List<Double> pontosGrafico;
	
	public FluxoDePotenciaFundamental() {}
	
	public FluxoDePotenciaFundamental(double amplitudeTensao, double amplitudeCorrente,
		double anguloTensao, double anguloCorrente) {
		this.amplitudeTensao = amplitudeTensao;
		this.amplitudeCorrente = amplitudeCorrente;
		this.anguloTensao = anguloTensao;
		this.anguloCorrente = anguloCorrente;
		
	}
	
	public List<Double> getPontosGrafico() {
		return pontosGrafico;
	}

	public void setAmplitudeTensao(double amplitudeTensao) {
		this.amplitudeTensao = amplitudeTensao;
	}

	public void setAmplitudeCorrente(double amplitudeCorrente) {
		this.amplitudeCorrente = amplitudeCorrente;
	}

	public void setAnguloTensao(double anguloTensao) {
		this.anguloTensao = anguloTensao;
	}

	public void setAnguloCorrente(double anguloCorrente) {
		this.anguloCorrente = anguloCorrente;
	}

	public void setPontosGrafico(List<Double> pontosGrafico) {
		this.pontosGrafico = pontosGrafico;
	}

	public void setTensao(double amplitudeTensao, double anguloTensao) {
		this.amplitudeTensao = amplitudeTensao;
		this.anguloTensao = anguloTensao;
	}
	
	public void setCorrente(double amplitudeCorrente, double anguloCorrente) {
		this.amplitudeCorrente = amplitudeCorrente;
		this.anguloCorrente = anguloCorrente;
	}
	
	public double calcularPotAtiva() {
		potAtiva = amplitudeTensao * amplitudeCorrente * Math.cos(anguloTensao - anguloCorrente);
		return potAtiva;
	}
	
	public double calcularPotReativa() {
		potReativa = amplitudeTensao * amplitudeCorrente * Math.sin(anguloTensao - anguloCorrente);
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
			fatPotencia = Math.cos(anguloTensao - anguloCorrente);
		}
		return fatPotencia;
	}
	
	public List<Double> pontosOnda(){
		
		return pontosGrafico;
	}
}
