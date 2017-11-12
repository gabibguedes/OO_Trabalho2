package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import controllers.AcoesSimulacao2;
import view.Simulacao1.ResultadoPotencia;

public class Simulacao2 extends Simulacao{
	
	public final static String COMPFUND = "Componente Fundamental";
	public final static String HARMONICOS = "Harmonicos";
	private Bloco compFund;
	private Harmonicos harmonicos;
	private Resultado resultado;
	private JScrollPane scroll;
	private boolean ehPar = true;
	private GraphPanel grafico;
	
	public Simulacao2(JFrame janela) throws IOException {
		super(janela);
		preparaSimulacao(SIMULACAO2);
		preparaGUI();
	}
	
	public void mostrarTela() {		
		janela.setVisible(true);
		contentPane.setVisible(true);
	}
	
	private void preparaGUI() {
			
		simulacaoPane.setLayout(new GridLayout(3,1));
		JPanel simulacao2Pane = new JPanel(new BorderLayout());
		simulacao2Pane.setPreferredSize(new Dimension(500,800));
		
		compFund = new Bloco(COMPFUND, this);
		harmonicos = new Harmonicos(this);
		resultado = new Resultado();

		simularResultado.setActionCommand(SIMULACAO2);
		simularResultado.addActionListener(new AcoesSimulacao2(this));
		
		simulacaoPane.add(compFund, BorderLayout.NORTH);
		simulacaoPane.add(harmonicos, BorderLayout.CENTER);
		simulacaoPane.add(resultado, BorderLayout.SOUTH);
		
		
	}
	

	
	public class Harmonicos extends JPanel{
		Simulacao2 simulacao;

		private JPanel configuraHarmonicos;
		private JScrollPane  blocosHarmonicos;
		private JLabel titulo, numOH, parImpar;
		private JPanel numOHpanel, paridadePanel, imputsUsuario, imputsNumOH, imputsParImpar;
		private JSpinner numOHTxt;
		private JRadioButton par, impar;
		private JButton enter;
		private List<Bloco> listaHarmonicos;
		
		private Harmonicos(Simulacao2 simulacao){
			this.simulacao = simulacao;
			setLayout(new BorderLayout());
			preparaHarmonicos();
		}
		
		private void preparaHarmonicos() {
			configuraHarmonicos = new JPanel(new BorderLayout());
			add(configuraHarmonicos, BorderLayout.NORTH);
			
			titulo = new JLabel(HARMONICOS+":   ");
			titulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_SUBTITULO));
			configuraHarmonicos.add(titulo, BorderLayout.WEST);
			
			
			imputsUsuario = new JPanel(new FlowLayout());
			imputsNumOH = new JPanel(new FlowLayout());
			imputsParImpar = new JPanel(new FlowLayout());
			
			numOH = new JLabel("Número de Ordens Harmônicas:");
			numOH.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			imputsNumOH.add(numOH);
			
			SpinnerModel sm = new SpinnerNumberModel(1, 1, 9, 1);
			
			numOHTxt = new JSpinner(sm);
			numOHTxt.setPreferredSize(new Dimension(50, 25));
			imputsNumOH.add(numOHTxt);
			
			parImpar = new JLabel(HARMONICOS+":");
			parImpar.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			imputsParImpar.add(parImpar);
			
			par = new JRadioButton("Pares");
			par.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			par.setActionCommand("par");
			par.addActionListener(new AcoesSimulacao2(simulacao, this));
			par.setSelected(ehPar);
			imputsParImpar.add(par);
			
			impar = new JRadioButton("Impares");
			impar.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			impar.setActionCommand("impar");
			impar.addActionListener(new AcoesSimulacao2(simulacao, this));
			imputsParImpar.add(impar);
			
			enter = new JButton("OK");
			enter.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			enter.setActionCommand(HARMONICOS);
			enter.addActionListener(new AcoesSimulacao2(simulacao, this));
			
			imputsUsuario.add(imputsNumOH);
			imputsUsuario.add(imputsParImpar);
			imputsUsuario.add(enter);
			configuraHarmonicos.add(imputsUsuario, BorderLayout.EAST);
			
			listaHarmonicos = new ArrayList<>();
			blocosHarmonicos = new JScrollPane();
			Bloco blocoInicial = new Bloco(ehPar, "1", simulacao);
			listaHarmonicos.add(blocoInicial);
			blocosHarmonicos.setViewportView(blocoInicial);
			add(blocosHarmonicos);
		}
		
		public JRadioButton getRadioButtonImpar() {
			return impar;
		}
		
		public JRadioButton getRadioButtonPar() {
			return par;
		}
		public JSpinner getNumOH() {
			return numOHTxt;
		}

		public void setNovaListaHarmonicos(List<Bloco> listaHarmonicos) {
			this.listaHarmonicos = listaHarmonicos;
			if(listaHarmonicos != null) {
				JPanel lista;
				lista = new JPanel(new GridLayout(listaHarmonicos.size(), 1));
				for(int i=0; i<listaHarmonicos.size(); i++) {
					lista.add(listaHarmonicos.get(i));
				}
				blocosHarmonicos.setViewportView(lista);
				
			}
		}
		public List<Bloco> getListaHarmonicos(){
			return listaHarmonicos;
		}
		
		public void setListaHarmonicos(List<Bloco> listaHarmonicos){
			this.listaHarmonicos = listaHarmonicos;
		}
	}
	
	public Harmonicos getHarmonicos() {
		return harmonicos;
	}
	public Bloco getComponenteFundamental() {
		return compFund;
	}
	public GraphPanel getGraficoResultante() {
		return resultado.getGrafico();
	}
	public void setEhPar(boolean ehPar) {
		this.ehPar = ehPar;
	}
	public boolean  getEhPar() {
		return ehPar;
	}
	public JLabel getFormula() {
		return resultado.getFormula();
	}
	
	public class Resultado extends JPanel{
		private JLabel titulo, onda, formula, tituloFormula;;
		private GraphPanel grafico;
		private JPanel formulaPanel, graficoPanel, resultados;
		public Resultado() {
			setLayout(new BorderLayout());
			resultados = new JPanel(new BorderLayout());
			formulaPanel = new JPanel(new GridLayout(2,1));
			graficoPanel = new JPanel(new BorderLayout());
			
			resultados.add(formulaPanel, BorderLayout.NORTH);
			resultados.add(graficoPanel);
			
			add(resultados);
			mostrarResultado();
		}
		
		public void mostrarResultado() {
			titulo = new JLabel("Resultado:");
			titulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_SUBTITULO));
			add(titulo, BorderLayout.NORTH);
			
			List<Double> pontos = new ArrayList<>();
			pontos.add(0.0);
			
			onda = new JLabel("Grafico Resultante:");
			onda.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			graficoPanel.add(onda, BorderLayout.NORTH);
			graficoPanel.setBorder(new EmptyBorder(5,5,5,5));
			
			grafico = new GraphPanel(pontos);
			grafico.setPreferredSize(new Dimension(400,150));
			graficoPanel.add(grafico);
			
			tituloFormula = new JLabel("Serie de Fourier Amplitude-Fase:");
			tituloFormula.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			formulaPanel.add(tituloFormula);
			
			formula = new JLabel("");
			formula.setFont(new Font(FONTE, Font.BOLD, TAMANHO_TEXTO));
			formulaPanel.add(formula);
		}
		
		public GraphPanel getGrafico() {
			return grafico;
		}
		
		public JLabel getFormula() {
			return formula;
		}
	}

}
