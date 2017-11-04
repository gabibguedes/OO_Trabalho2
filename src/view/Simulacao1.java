package src.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import src.controllers.AcoesSimulacao1;
import src.controllers.MudarTela;
import src.model.GraphPanel;

public class Simulacao1 extends Simulacao{
	
	public static final String TENSAO = "Tensão";
	public static final String CORRENTE = "Corrente";
	
	private Bloco tensao, corrente;
	private ResultadoPotencia resultado;
	
	NumberFormat formatter = new DecimalFormat("#0.0000");
	
	public Simulacao1(JFrame janela) throws IOException{
		super(janela);
		preparaSimulacao(SIMULACAO1);
		preparaGUI();
	}

	private void preparaGUI() {
		simulacaoPane.setLayout(new GridLayout(3,1));		
		
		Bloco nulo = null;
		tensao = new Bloco(TENSAO, this);
		corrente = new Bloco(CORRENTE, this);
		resultado = new ResultadoPotencia();
		
		simularResultado.setActionCommand(SIMULACAO1);
		simularResultado.addActionListener(new AcoesSimulacao1(nulo, this));
		
		simulacaoPane.add(tensao);
		simulacaoPane.add(corrente);
		simulacaoPane.add(resultado);
	}
	
	public void mostrarTela() {		
		janela.setVisible(true);
		contentPane.setVisible(true);
	}
	
	public class Bloco extends JPanel{
		private JLabel tituloBloco, amplitude, angulo, tituloGrafico;
		private JTextField amplitudeTxt, anguloTxt;
		private JPanel imputUsuario, imputsAmp, imputsAng, resposta;
		private String titulo, simboloAmplitude;
		private JButton simular;
		private GraphPanel grafico;
		private Simulacao1 simulacao;
		private List<Double> pontos;
		
		public Bloco(String titulo, Simulacao1 simulacao){
				this.titulo = titulo;
				this.simulacao = simulacao;
				
				switch(titulo) {
				case TENSAO:
					simboloAmplitude = "VRMS";
					break;
				case CORRENTE:
					simboloAmplitude = "IRMS";
					break;
				}
				preparaGUI();
		}
		
		public void preparaGUI() {
			setPreferredSize(new Dimension(500, 300));
			setLayout(new BorderLayout(2,2));
			tituloBloco = new JLabel(titulo + ": ");
			tituloBloco.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_SUBTITULO));
			
			imputUsuario = new JPanel(new GridLayout(3,1));
			imputsAmp = new JPanel (new BorderLayout());
			imputsAng = new JPanel (new BorderLayout());
			resposta = new JPanel(new BorderLayout());
			JPanel botao = new JPanel(new FlowLayout());
			JPanel amplitudeTxtP = new JPanel(new FlowLayout());
			JPanel anguloTxtP = new JPanel(new FlowLayout());
			
			add(tituloBloco, BorderLayout.NORTH);
			add(imputUsuario, BorderLayout.WEST);
			add(resposta);
			
			amplitude = new JLabel("Amplitude ("+ simboloAmplitude +"): ");
			amplitude.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			amplitudeTxt = new JTextField();
			amplitudeTxt.setPreferredSize(new Dimension(100, 25));
			amplitudeTxt.setAlignmentY(amplitude.getAlignmentY());
			imputsAmp.add(amplitude, BorderLayout.NORTH);
			amplitudeTxtP.add(amplitudeTxt);
			imputsAmp.add(amplitudeTxtP);
			imputsAmp.setBorder(new EmptyBorder(5, 5, 5, 5));
			
			angulo = new JLabel("Angulo de fase (0º): ");
			angulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			anguloTxt = new JTextField();
			anguloTxt.setPreferredSize(new Dimension(100, 25));
			imputsAng.add(angulo, BorderLayout.NORTH);
			anguloTxtP.add(anguloTxt);
			imputsAng.add(anguloTxtP);
			imputsAng.setBorder(new EmptyBorder(5, 5, 5, 5));
						
			simular = new JButton("Simular Forma de Onda");
			simular.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			simular.setPreferredSize(new Dimension(200, 25));	
			botao.add(simular);

			simular.setActionCommand(titulo);
			simular.addActionListener(new AcoesSimulacao1(this, simulacao));
			
			imputUsuario.add(imputsAmp);
			imputUsuario.add(imputsAng);
			imputUsuario.add(botao);
			
			tituloGrafico = new JLabel("Forma de Onda da " + titulo + ":");
			tituloGrafico.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			
			pontos = new ArrayList<Double>();
			pontos.add(0.0);
			
			grafico = new GraphPanel(pontos);
			grafico.setPreferredSize(new Dimension(200,200));
			
			resposta.add(tituloGrafico, BorderLayout.NORTH);
			resposta.setBorder(new EmptyBorder (10, 10, 10, 10));
			resposta.add(grafico);
		}

		public String getAmplitudeTxt() {
			return amplitudeTxt.getText();
		}

		public String getAnguloTxt() {
			return anguloTxt.getText();
		}
		
		public GraphPanel getGrafico() {
			return grafico;
		}
	}
	
	public class ResultadoPotencia extends JPanel{
		private JLabel tituloResultado, potAtiva, potReativa, potAparente, fatPotencia, potGrafico, potTriangulo;
		private JLabel potAtivaValor, potReativaValor, potAparenteValor, fatPotenciaValor;
		private JPanel painel, valores, graficos;
		private GraphPanel grafico, triangulo;
		private List<Double> pontos;

		private ResultadoPotencia() {
			preparaGUI();
		}
		
		private void preparaGUI() {
			setLayout(new BorderLayout());
			painel = new JPanel (new BorderLayout(2,1));
			valores = new JPanel(new GridLayout(4,2));
			graficos = new JPanel(new BorderLayout());
			JPanel graficoOnda = new JPanel (new BorderLayout());
			JPanel graficoTriangulo = new JPanel (new BorderLayout());
			
			tituloResultado = new JLabel("Resultado: ");
			tituloResultado.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_SUBTITULO));
			
			painel.add(valores, BorderLayout.WEST);
			painel.add(graficos);

			add(tituloResultado, BorderLayout.NORTH);
			add(painel);
			
			potAtiva = new JLabel("Potencia Ativa: ");
			potAtiva.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			
			potReativa = new JLabel("Potencia Reativa: ");
			potReativa.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			
			potAparente = new JLabel("Potencia Aparente: ");
			potAparente.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			
			fatPotencia = new JLabel("Fator Potencia : ");
			fatPotencia.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			
			potAtivaValor = new JLabel("0");
			potAtivaValor.setFont(new Font(FONTE, Font.BOLD, TAMANHO_TEXTO));
			
			potReativaValor = new JLabel("0");
			potReativaValor.setFont(new Font(FONTE, Font.BOLD, TAMANHO_TEXTO));
			
			potAparenteValor = new JLabel("0");
			potAparenteValor.setFont(new Font(FONTE, Font.BOLD, TAMANHO_TEXTO));
			
			fatPotenciaValor = new JLabel("0");
			fatPotenciaValor.setFont(new Font(FONTE, Font.BOLD, TAMANHO_TEXTO));
			
			valores.add(potAtiva);
			valores.add(potAtivaValor);
			valores.add(potReativa);
			valores.add(potReativaValor);
			valores.add(potAparente);
			valores.add(potAparenteValor);
			valores.add(fatPotencia);
			valores.add(fatPotenciaValor);
			
			potGrafico = new JLabel ("Potencia Instantanea");
			potGrafico.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			potTriangulo = new JLabel ("Triangulo de Potência");
			potTriangulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			
			pontos = new ArrayList<Double>();
			pontos.add(0.0);
			
			grafico = new GraphPanel(pontos);
			grafico.setPreferredSize(new Dimension(100,100));
			
			graficoOnda.add(potGrafico, BorderLayout.NORTH);
			graficoOnda.add(grafico);
			graficoOnda.setBorder(new EmptyBorder (10, 10, 10, 10));
			graficos.add(graficoOnda);
			
			triangulo = new GraphPanel(pontos);
			triangulo.setPreferredSize(new Dimension(100,100));
			
			graficoTriangulo.add(potTriangulo, BorderLayout.NORTH);
			graficoTriangulo.add(triangulo);
			graficoTriangulo.setBorder(new EmptyBorder (10, 10, 10, 10));
			graficos.add(graficoTriangulo, BorderLayout.EAST);
				
		}
		

		public void setPotAtivaValor(double potAtivaValCalculado) {;
			potAtivaValor.setText("" + formatter.format(potAtivaValCalculado) + " Watt");
		}

		public void setPotReativaValor(double potReativaValCalculado) {
			potReativaValor.setText("" + formatter.format(potReativaValCalculado) + " VAR");
		}

		public void setPotAparenteValor(double potAparenteValCalculado) {
			potAparenteValor.setText("" + formatter.format(potAparenteValCalculado) + " VA");
		}

		public void setFatPotenciaValor(double fatPotenciaValCalculado) {
			fatPotenciaValor.setText("" + formatter.format(fatPotenciaValCalculado));
		}
		
		public GraphPanel getGrafico() {
			return grafico;
		}
	}
	
	public Bloco getBlocoTensao() {
		return tensao;
	}
	public Bloco getBlocoCorrente() {
		return corrente;
	}
	public ResultadoPotencia getResultadoPotencia() {
		return resultado;
	}

}
