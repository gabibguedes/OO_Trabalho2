package view;

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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.AcoesSimulacao1;
import controllers.MudarTela;

public class Simulacao1 extends Simulacao{
	
	public static final String TENSAO = "Tensão";
	public static final String CORRENTE = "Corrente";
	
	private Bloco tensao, corrente;
	private ResultadoPotencia resultado;
	
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	public Simulacao1(JFrame janela) throws IOException{
		super(janela);
		preparaSimulacao(SIMULACAO1);
		preparaGUI();
	}

	private void preparaGUI() {
		simulacaoPane.setLayout(new GridLayout(3,1));		
		
		tensao = new Bloco(TENSAO, this);
		corrente = new Bloco(CORRENTE, this);
		resultado = new ResultadoPotencia();
		
		simularResultado.setActionCommand(SIMULACAO1);
		simularResultado.addActionListener(new AcoesSimulacao1(this));
		
		simulacaoPane.add(tensao);
		simulacaoPane.add(corrente);
		simulacaoPane.add(resultado);
		
	}
	
	public void mostrarTela() {		
		janela.setVisible(true);
		contentPane.setVisible(true);

	}
	
	public class ResultadoPotencia extends JPanel{
		private JLabel tituloResultado, potAtiva, potReativa, potAparente, fatPotencia, potGrafico, potTriangulo;
		private JLabel potAtivaValor, potReativaValor, potAparenteValor, fatPotenciaValor;
		private JPanel painel, valores, graficos;
		private GraphPanel grafico;
		Triangulo triangulo;
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
			
			potAtiva = new JLabel("Potência Ativa: ");
			potAtiva.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			
			potReativa = new JLabel("Potência Reativa: ");
			potReativa.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			
			potAparente = new JLabel("Potência Aparente: ");
			potAparente.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			
			fatPotencia = new JLabel("Fator Potência : ");
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
			
			potGrafico = new JLabel ("Potência Instantânea:");
			potGrafico.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			potTriangulo = new JLabel ("Triangulo de Potência:");
			potTriangulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			
			pontos = new ArrayList<Double>();
			pontos.add(0.0);
			
			grafico = new GraphPanel(pontos);
			grafico.setPreferredSize(new Dimension(120,120));
			
			graficoOnda.add(potGrafico, BorderLayout.NORTH);
			graficoOnda.add(grafico);
			graficoOnda.setBorder(new EmptyBorder (10, 10, 10, 10));
			graficos.add(graficoOnda);
			
			triangulo = new Triangulo(0,0);
			triangulo.setPreferredSize(new Dimension(100,100));
			
			graficoTriangulo.add(potTriangulo, BorderLayout.NORTH);
			JPanel trianguloPane = new JPanel(new FlowLayout());
			trianguloPane.add(triangulo);
			graficoTriangulo.add(trianguloPane);
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
		
		public Triangulo getTriangulo() {
			return triangulo;
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
