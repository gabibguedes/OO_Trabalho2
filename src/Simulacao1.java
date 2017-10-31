package src;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Simulacao1 extends Simulacao{
	
	public Simulacao1() {
		preparaSimulacao(SIMULACAO1);
		preparaGUI();
	}
	
	private void preparaGUI() {
		simulacaoPane.setLayout(new GridLayout(3,1));		
		
		Bloco tensao = new Bloco("Tensão");
		Bloco corrente = new Bloco("Corrente");
		ResultadoPotencia resultado = new ResultadoPotencia();
		
		simulacaoPane.add(tensao);
		simulacaoPane.add(corrente);
		simulacaoPane.add(resultado);
	}
	
	public void mostrarTela() {
		
		setVisible(true);
	}
	
	private class Bloco extends JPanel{
		private JLabel tituloBloco, amplitude, angulo;
		private JTextField amplitudeTxt, anguloTxt;
		private JPanel painel, imputUsuario, imputsAmp, imputsAng, resposta;
		private String titulo;
		private JButton simular;
		
		public Bloco(String titulo){
				this.titulo = titulo;
				preparaGUI();
		}
		
		public void preparaGUI() {
			setPreferredSize(new Dimension(500, 300));
			setLayout(new GridLayout(2,1));
			tituloBloco = new JLabel(titulo + ": ");
			tituloBloco.setFont(new Font("Dialog", Font.PLAIN, 20));
			
			painel = new JPanel(new GridLayout(1,2));
			imputUsuario = new JPanel(new GridLayout(3,1));
			imputsAmp = new JPanel (new GridLayout(1,2));
			imputsAng = new JPanel (new GridLayout(1,2));
			resposta = new JPanel(new GridLayout(2,1));
			JPanel botao = new JPanel(new FlowLayout());
			
			add(tituloBloco);
			painel.add(imputUsuario);
			painel.add(resposta);
			add(painel);
			
			amplitude = new JLabel("Amplitude: ");
			amplitude.setFont(new Font("Dialog", Font.PLAIN, 12));
			amplitudeTxt = new JTextField();
			amplitudeTxt.setPreferredSize(new Dimension(100, 25));
			imputsAmp.add(amplitude);
			imputsAmp.add(amplitudeTxt);
			
			angulo = new JLabel("Angulo de fase: ");
			angulo.setFont(new Font("Dialog", Font.PLAIN, 12));
			anguloTxt = new JTextField();
			anguloTxt.setPreferredSize(new Dimension(100, 25));
			imputsAng.add(angulo);
			imputsAng.add(anguloTxt);
						
			simular = new JButton("simular");
			simular.setFont(new Font("Dialog", Font.PLAIN, 12));
			simular.setPreferredSize(new Dimension(100, 25));
			botao.add(simular);
			
			imputUsuario.add(imputsAmp);
			imputUsuario.add(imputsAng);
			imputUsuario.add(botao);
			
		}	
	}
	
	private class ResultadoPotencia extends JPanel{
		private JLabel potAtiva, potReativa, potAparente, fatPotencia;
		private JLabel potAtivaValor, potReativaValor, potAparenteValor, fatPotenciaValor;
		private JPanel valores, graficos;
		
		private ResultadoPotencia() {
			preparaGUI();
		}
		
		private void preparaGUI() {
			setLayout(new GridLayout(2,1));
			valores = new JPanel(new GridLayout(4,2));
			graficos = new JPanel(new GridLayout(4,1));
			
			add(valores);
			add(graficos);	
			
			potAtiva = new JLabel("Potencia Ativa: ");
			potAtiva.setFont(new Font("Dialog", Font.PLAIN, 12));
			
			potReativa = new JLabel("Potencia Reativa: ");
			potReativa.setFont(new Font("Dialog", Font.PLAIN, 12));
			
			potAparente = new JLabel("Potencia Aparente: ");
			potAparente.setFont(new Font("Dialog", Font.PLAIN, 12));
			
			fatPotencia = new JLabel("Fator Potencia : ");
			fatPotencia.setFont(new Font("Dialog", Font.PLAIN, 12));
			
			potAtivaValor = new JLabel("0");
			potAtivaValor.setFont(new Font("Dialog", Font.BOLD, 12));
			
			potReativaValor = new JLabel("0");
			potReativaValor.setFont(new Font("Dialog", Font.BOLD, 12));
			
			potAparenteValor = new JLabel("0");
			potAparenteValor.setFont(new Font("Dialog", Font.BOLD, 12));
			
			fatPotenciaValor = new JLabel("0");
			fatPotenciaValor.setFont(new Font("Dialog", Font.BOLD, 12));
			
			valores.add(potAtiva);
			valores.add(potAtivaValor);
			valores.add(potReativa);
			valores.add(potReativaValor);
			valores.add(potAparente);
			valores.add(potAparenteValor);
			valores.add(fatPotencia);
			valores.add(fatPotenciaValor);
			
		}
	}

}
