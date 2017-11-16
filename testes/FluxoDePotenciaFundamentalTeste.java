import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.FluxoDePotenciaFundamental;

class FluxoDePotenciaFundamentalTeste {

	//Essa classe faz os testes dos calculos do fator de potencia fundamental
	//usados na simulação 1
	
	double amplitudeTensao, amplitudeCorrente, anguloTensao, anguloCorrente;
	double potAtiva, potReativa,potAparente, fatPotencia; 
	FluxoDePotenciaFundamental calculo;
	List <Double> pontosTensao, pontosCorrente, pontosOnda;
	
	//Antes de tudo, é feita a preparação para os calculos
	@BeforeEach
	public void beforeTests(){
		double ponto;
		
		//Os valores para todas as variaveis são estabelecidos
		amplitudeTensao = 220f;
		anguloTensao = 0f;
		amplitudeCorrente = 39f;
		anguloCorrente = 35f;
		
		potAtiva = 7028f;
		potReativa = -4921f;
		potAparente = 8580f;
		fatPotencia = 0.82f;
		
		//A classe a ser testada é instanciada
		calculo = new FluxoDePotenciaFundamental(amplitudeTensao,
				amplitudeCorrente, anguloTensao, anguloCorrente);
		
		//As listas são criadas e preenchidas corretamnete
		pontosTensao = new ArrayList<>();
		pontosCorrente = new ArrayList<>();
		pontosOnda = new ArrayList<>();

		for(double t = 0; t< 200; t++) {
			ponto = amplitudeTensao * Math.cos(Math.toRadians((
					calculo.FREQANGULAR * t) + anguloTensao));
			pontosTensao.add(ponto);
		}
		
		for(double t = 0; t< 200; t++) {
			ponto = amplitudeCorrente * Math.cos(Math.toRadians(
					(calculo.FREQANGULAR * t) + anguloCorrente));
			pontosCorrente.add(ponto);
		}
		
		for(double t = 0; t<100; t++) {
			ponto = (amplitudeTensao * Math.cos(Math.toRadians((
					calculo.FREQANGULAR * t) + anguloTensao))) * 
					(amplitudeCorrente * Math.cos(Math.toRadians((
							calculo.FREQANGULAR * t) + anguloCorrente)));
			pontosOnda.add(ponto);
		}
	}
	
	//Os metodos da classe são testados
	
	@Test
	void testCalcularPotenciaAtiva() {		
		assertEquals(potAtiva,calculo.calcularPotAtiva(), 10e1);
	}
	
	@Test
	void testCalcularPotReativa() {
		assertEquals(potReativa, calculo.calcularPotReativa(), 10e1);
	}
	
	@Test
	void testCalcularPotAparente() {
		assertEquals(potAparente, calculo.calcularPotAparente(), 10e1);
	}
	
	@Test
	void testCalcularFatPotencia() {
		assertEquals(fatPotencia, calculo.calcularFatPotencia(), 10e-2);
	}
	
	@Test
	void testCalcularOndaTensao() {
		
		for(int i=0; i<pontosTensao.size(); i++) {
			assertEquals(pontosTensao.get(i), calculo.calcularOndaTensao().get(i), 10e-2);
		}
		
	}
	
	@Test
	void testSetTensao() {
		FluxoDePotenciaFundamental tensao = new FluxoDePotenciaFundamental();
		tensao.setTensao(amplitudeTensao, anguloTensao);
		
		for(int i=0; i<pontosTensao.size(); i++) {
			assertEquals(pontosTensao.get(i), tensao.calcularOndaTensao().get(i), 10e-2);
		}
	}
	
	@Test
	void testCalcularOndaCorrente() {
		
		for(int i=0; i<pontosCorrente.size(); i++) {
			assertEquals(pontosCorrente.get(i), calculo.calcularOndaCorrente().get(i), 10e-2);
		}
		
	}
	
	@Test
	void testSetCorrente() {
		FluxoDePotenciaFundamental corrente = new FluxoDePotenciaFundamental();
		corrente.setCorrente(amplitudeCorrente, anguloCorrente);
		
		for(int i=0; i<pontosTensao.size(); i++) {
			assertEquals(pontosCorrente.get(i), corrente.calcularOndaCorrente().get(i), 10e-2);
		}
	}
	
	@Test
	void testCalculaOnda() {
		for(int i=0; i<pontosOnda.size(); i++) {
			assertEquals(pontosOnda.get(i), calculo.calcularOnda().get(i), 10e-2);
		}
	}
	
	@Test
	void testFatPotenciaAngulosIguais() {
		FluxoDePotenciaFundamental teste = new FluxoDePotenciaFundamental(amplitudeTensao,
				amplitudeCorrente, 4, 4);
		assertEquals(1, teste.calcularFatPotencia());
	}

}
