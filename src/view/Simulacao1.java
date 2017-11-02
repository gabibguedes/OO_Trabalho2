package src.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
		private JLabel tituloBloco, amplitude, angulo, graficoTitulo;
		private JTextField amplitudeTxt, anguloTxt;
		private JPanel painel, imputUsuario, imputsAmp, imputsAng, resposta;
		private String titulo, simboloAmplitude;
		private JButton simular;
		private GraphPanel grafico;
		private List <Double> pontos;
		private Simulacao1 simulacao;
		
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
			setLayout(new GridLayout(2,1));
			tituloBloco = new JLabel(titulo + ": ");
			tituloBloco.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_SUBTITULO));
			
			
			
			painel = new JPanel(new BorderLayout(1,2));
			imputUsuario = new JPanel(new GridLayout(3,1));
			imputsAmp = new JPanel (new GridLayout(1,2));
			imputsAng = new JPanel (new GridLayout(1,2));
			resposta = new JPanel(new BorderLayout());
			JPanel botao = new JPanel(new FlowLayout());
			
			add(tituloBloco);
			painel.add(imputUsuario, BorderLayout.WEST);
			painel.add(resposta);
			add(painel);
			
			amplitude = new JLabel("Amplitude ("+ simboloAmplitude +"): ");
			amplitude.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			amplitudeTxt = new JTextField();
			amplitudeTxt.setPreferredSize(new Dimension(100, 25));
			imputsAmp.add(amplitude);
			imputsAmp.add(amplitudeTxt);
			
			angulo = new JLabel("Angulo de fase (0º): ");
			angulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			anguloTxt = new JTextField();
			anguloTxt.setPreferredSize(new Dimension(100, 25));
			imputsAng.add(angulo);
			imputsAng.add(anguloTxt);
						
			simular = new JButton("Simular Forma de Onda");
			simular.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			simular.setPreferredSize(new Dimension(200, 25));	
			botao.add(simular);

			simular.setActionCommand(titulo);
			simular.addActionListener(new AcoesSimulacao1(this, simulacao));
			
			imputUsuario.add(imputsAmp);
			imputUsuario.add(imputsAng);
			imputUsuario.add(botao);
			
			graficoTitulo = new JLabel("Forma de onda:");
			graficoTitulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			
//			grafico = new GraphPanel(pontos);
			resposta.setBorder(new EmptyBorder (10, 10, 10, 10));
			resposta.add(graficoTitulo, BorderLayout.NORTH);
//			resposta.add(grafico);
		}

		public String getAmplitudeTxt() {
			return amplitudeTxt.getText();
		}

		public String getAnguloTxt() {
			return anguloTxt.getText();
		}
	}
	
	public class ResultadoPotencia extends JPanel{
		private JLabel tituloResultado, potAtiva, potReativa, potAparente, fatPotencia, potGrafico, potTriangulo;
		private JLabel potAtivaValor, potReativaValor, potAparenteValor, fatPotenciaValor;
		private JPanel painel, valores, graficos;


		private ResultadoPotencia() {
			preparaGUI();
		}
		
		private void preparaGUI() {
			setLayout(new GridLayout(2,1));
			painel = new JPanel (new BorderLayout(2,1));
			valores = new JPanel(new GridLayout(4,2));
			graficos = new JPanel(new GridLayout(4,1));
			
			tituloResultado = new JLabel("Resultado: ");
			tituloResultado.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_SUBTITULO));
			
			painel.add(valores, BorderLayout.WEST);
			painel.add(graficos);

			add(tituloResultado);
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
				
		}
		

		public void setPotAtivaValor(double potAtivaValCalculado) {
			potAtivaValor.setText("" + potAtivaValCalculado);
		}

		public void setPotReativaValor(double potReativaValCalculado) {
			potReativaValor.setText("" + potReativaValCalculado);
		}

		public void setPotAparenteValor(double potAparenteValCalculado) {
			potAparenteValor.setText("" + potAparenteValCalculado);
		}

		public void setFatPotenciaValor(double fatPotenciaValCalculado) {
			fatPotenciaValor.setText("" + fatPotenciaValCalculado);
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
