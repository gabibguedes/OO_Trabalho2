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

public class Simulacao1 extends Tela implements Simulacao{
	//Essa classe constrói a parte gráfica da Simulação 1, ou
	//a simulação do fluxo de potência fundamental.
	
	public static final String TENSAO = "Tensão";
	public static final String CORRENTE = "Corrente";
	public JPanel simulacaoPane;
	
	//Após analisado a similaridade entre as partes do JPanel para
	//tensão e corrente foi feito uma classe Bloco para esses paineis
	private Bloco tensao, corrente;
	private ResultadoPotencia resultado;
	
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	public Simulacao1(JFrame janela) throws IOException{
		super(janela);
		//Content pane organizado pelo método da interface Simulação
		simulacaoPane = preparaSimulacao(SIMULACAO1, this);
		preparaGUI();
	}

	private void preparaGUI() {
		//O painel de Simulações também é dividido em 3
		simulacaoPane.setLayout(new GridLayout(3,1));		
		
		//1a parte = tensão
		tensao = new Bloco(TENSAO, this);
		simulacaoPane.add(tensao);
		
		//2a parte = corrente
		corrente = new Bloco(CORRENTE, this);
		simulacaoPane.add(corrente);
		
		//3a parte = resultado
		resultado = new ResultadoPotencia();
		simulacaoPane.add(resultado);
		
		//Para o botão simular resultado dessa simulação, é usado a 
		//classe AçõesSimulacao1 como ActionListener
		simularResultado.setActionCommand(SIMULACAO1);
		simularResultado.addActionListener(new AcoesSimulacao1(this));		
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

	
	public class ResultadoPotencia extends JPanel{
		//Nessa classe foi organizado o painel do resultado desta simulação
		
		private JLabel potAtivaValor, potReativaValor, potAparenteValor, fatPotenciaValor;
		private GraphPanel grafico;
		private Triangulo triangulo;

		private ResultadoPotencia() {
			preparaGUI();
		}
		
		private void preparaGUI() {
			JLabel tituloResultado, potAtiva, potReativa, potAparente, fatPotencia, potGrafico, potTriangulo;
			JPanel painel, valores, graficos;
			List<Double> pontos;
			
			//Primeitamente foi feito um BorderLayout, onde será utilizado
			//2 partes, norte e centro
			setLayout(new BorderLayout());
			
			//Ao norte é colocado o titulo
			tituloResultado = new JLabel("Resultado: ");
			tituloResultado.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_SUBTITULO));
			add(tituloResultado, BorderLayout.NORTH);
			
			//E no centro outro painel é montado. Este sendo dividido em 2.
			painel = new JPanel (new BorderLayout(2,1));
			add(painel);
			
			//Onde a esquerda estarão os valores numericos resultantes
			valores = new JPanel(new GridLayout(4,2));
			painel.add(valores, BorderLayout.WEST);
			
			//E a direita os graficos
			graficos = new JPanel(new BorderLayout());
			JPanel graficoOnda = new JPanel (new BorderLayout());
			JPanel graficoTriangulo = new JPanel (new BorderLayout());
			painel.add(graficos);

			//Configurações do painel dos resultados númericos:
			
			//Potência Ativa:
			potAtiva = new JLabel("Potência Ativa: ");
			potAtiva.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			valores.add(potAtiva);
			
			potAtivaValor = new JLabel("0");
			potAtivaValor.setFont(new Font(FONTE, Font.BOLD, TAMANHO_TEXTO));
			valores.add(potAtivaValor);
			
			//Potência Reativa:
			potReativa = new JLabel("Potência Reativa: ");
			potReativa.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			valores.add(potReativa);
			
			potReativaValor = new JLabel("0");
			potReativaValor.setFont(new Font(FONTE, Font.BOLD, TAMANHO_TEXTO));
			valores.add(potReativaValor);

			//Potência Aparente:
			potAparente = new JLabel("Potência Aparente: ");
			potAparente.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			valores.add(potAparente);
			
			potAparenteValor = new JLabel("0");
			potAparenteValor.setFont(new Font(FONTE, Font.BOLD, TAMANHO_TEXTO));
			valores.add(potAparenteValor);
			
			//Fator de Potência:
			fatPotencia = new JLabel("Fator Potência : ");
			fatPotencia.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			valores.add(fatPotencia);
			
			fatPotenciaValor = new JLabel("0");
			fatPotenciaValor.setFont(new Font(FONTE, Font.BOLD, TAMANHO_TEXTO));
			valores.add(fatPotenciaValor);
			
			//Configurações do painel de Gráficos:
			
			//Gráfico de Onda da Potência Instantânea:
			potGrafico = new JLabel ("Potência Instantânea:");
			potGrafico.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			graficoOnda.add(potGrafico, BorderLayout.NORTH);
			
			pontos = new ArrayList<Double>();
			pontos.add(0.0);
			
			grafico = new GraphPanel(pontos);
			grafico.setPreferredSize(new Dimension(120,120));
			graficoOnda.add(grafico);
			
			graficoOnda.setBorder(new EmptyBorder (10, 10, 10, 10));
			graficos.add(graficoOnda);
			
			//Gráfico do Triangulo de Potência:
			potTriangulo = new JLabel ("Triangulo de Potência:");
			potTriangulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			
			triangulo = new Triangulo(0,0);
			triangulo.setPreferredSize(new Dimension(100,100));	
			graficoTriangulo.add(potTriangulo, BorderLayout.NORTH);
			
			JPanel trianguloPane = new JPanel(new FlowLayout());
			trianguloPane.add(triangulo);
			
			graficoTriangulo.add(trianguloPane);
			graficoTriangulo.setBorder(new EmptyBorder (10, 10, 10, 10));	
			graficos.add(graficoTriangulo, BorderLayout.EAST);	
		}
		
		//Geters e Seters gerados para atualização dos resultados
		
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
}
