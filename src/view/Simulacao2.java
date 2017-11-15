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

public class Simulacao2 extends Tela implements Simulacao{
	//Essa classe constrói a parte gráfica da Simulação 2, ou
	//a simulação da distorção harmonica.
	
	public final static String COMPFUND = "Componente Fundamental";
	public final static String HARMONICOS = "Harmônicos";
	public JPanel simulacaoPane;
	private Bloco compFund;
	private Harmonicos harmonicos;
	private Resultado resultado;
	private boolean ehPar = true;
	
	public Simulacao2(JFrame janela) throws IOException {
		super(janela);
		simulacaoPane = preparaSimulacao(SIMULACAO2, this);
		preparaGUI();
	}
	
	private void preparaGUI() {
		//O painel de simulações foi dividido em 3
		simulacaoPane.setLayout(new GridLayout(3,1));
		
		//1a parte = Componente Fundamental, assim como a tensão e a
		//corrente da simulação 1 o componente fundamental foi feito
		//a partir da classe Bloco
		compFund = new Bloco(false, false, COMPFUND, this);
		simulacaoPane.add(compFund, BorderLayout.NORTH);
		
		//2a parte = Harmonicos
		harmonicos = new Harmonicos(this);
		simulacaoPane.add(harmonicos, BorderLayout.CENTER);
		
		//3a parte = Resultado
		resultado = new Resultado();
		simulacaoPane.add(resultado, BorderLayout.SOUTH);
		
		//ActionListener do botão simular resultado instanciado pela
		//classe AcoesSimulacao2
		simularResultado.setActionCommand(SIMULACAO2);
		simularResultado.addActionListener(new AcoesSimulacao2(this));		
	}
	
	public class Harmonicos extends JPanel{
		//Essa classe organiza e instancia o JPanel onde se encontram os
		//Harmonicos
		
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
			//O JPanel é dividido em dois, onde ao norte estarão as configurações dos 
			//harmonicos e ao centro a lista dos mesmos com a utilização do JScrollPane
			
			//1a parte = configurações dos Harmonicos, este em 2, sendo a 1a parte o
			//titulo e a segunda os imputs do usuario
			configuraHarmonicos = new JPanel(new BorderLayout());
			add(configuraHarmonicos, BorderLayout.NORTH);
			
			//titulo
			titulo = new JLabel(HARMONICOS+":   ");
			titulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_SUBTITULO));
			configuraHarmonicos.add(titulo, BorderLayout.WEST);
			
			//Imputs do Usuario: Nessa parte o usuario seleciona a quantidade de harmonicos
			//e a paridade
			imputsUsuario = new JPanel(new FlowLayout());
			configuraHarmonicos.add(imputsUsuario, BorderLayout.EAST);
			
			//Quantidade de harmonicos:
			imputsNumOH = new JPanel(new FlowLayout());
			numOH = new JLabel("Número de Ordens Harmônicas:");
			numOH.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			imputsNumOH.add(numOH);
			
			SpinnerModel sm = new SpinnerNumberModel(0, 0, 6, 1);
			
			numOHTxt = new JSpinner(sm);
			numOHTxt.setPreferredSize(new Dimension(50, 25));
			imputsNumOH.add(numOHTxt);
			imputsUsuario.add(imputsNumOH);
			
			//Paridade:
			imputsParImpar = new JPanel(new FlowLayout());

			parImpar = new JLabel(HARMONICOS+":");
			parImpar.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			imputsParImpar.add(parImpar);
			
			par = new JRadioButton("Pares");
			par.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			par.setActionCommand("par");
			par.addActionListener(new AcoesSimulacao2(simulacao, this));
			imputsParImpar.add(par);
			
			impar = new JRadioButton("Ímpares");
			impar.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			impar.setActionCommand("impar");
			impar.addActionListener(new AcoesSimulacao2(simulacao, this));
			imputsParImpar.add(impar);
			imputsUsuario.add(imputsParImpar);
			
			//Botão ok chama o ActionListener dessa simulação, onde será gerado
			//a quantidade de harmonicos nos parametros estabelecidos
			enter = new JButton("OK");
			enter.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			enter.setActionCommand(HARMONICOS);
			enter.addActionListener(new AcoesSimulacao2(simulacao, this));
			imputsUsuario.add(enter);
			
			//Harmonicos:
			listaHarmonicos = new ArrayList<>();
			blocosHarmonicos = new JScrollPane();
			add(blocosHarmonicos);
		}
		
		//Metodos acessores para os JRadioButtons são necessários para impedir
		//que os dois estão selecionados ao mesmo tempo
		
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
			//Este médodo é chamado em AcoesSimulacao2 após o usuário escolher 
			//a quantidade de harmonicos que quer executar
			
			if(listaHarmonicos != null) {
				
				//Um painel é instanciado e nele é posto todos os blocos da lista
				//formada	
				JPanel harmonicos;
				harmonicos = new JPanel(new GridLayout(listaHarmonicos.size(), 1));
				
				for(int i=0; i<listaHarmonicos.size(); i++) {
					harmonicos.add(listaHarmonicos.get(i));
				}
				
				//O painel criado é então colocado no JScrollPane
				blocosHarmonicos.setViewportView(harmonicos);
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
		//Esta classe instancia o JPanel do resultado desta simulação.
		
		private JLabel titulo, onda, formula, tituloFormula;;
		private GraphPanel grafico;
		private JPanel formulaPanel, graficoPanel, resultados;
		public Resultado() {
			//Neste painel teremos inicialmente 2 partes, o titulo e o panel 
			//onde os resultados são aprensentados
			setLayout(new BorderLayout());
			
			//Dentro do painel de aprensentação de resultados teremos outros 2
			//paineis, 1 para a apresentação das formulas e outro para os 
			//gráficos
			resultados = new JPanel(new BorderLayout());
			formulaPanel = new JPanel(new GridLayout(2,1));
			graficoPanel = new JPanel(new BorderLayout());
			
			resultados.add(formulaPanel, BorderLayout.NORTH);
			resultados.add(graficoPanel);
			
			add(resultados);
			mostrarResultado();
		}
		
		public void mostrarResultado() {
			//titulo do painel da classe
			titulo = new JLabel("Resultado:");
			titulo.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_SUBTITULO));
			add(titulo, BorderLayout.NORTH);
			
			List<Double> pontos = new ArrayList<>();
			pontos.add(0.0);
			
			//No painel dos gráficos, temos o titulo e o grafico de onda
			onda = new JLabel("Gráfico Resultante:");
			onda.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			graficoPanel.add(onda, BorderLayout.NORTH);
			graficoPanel.setBorder(new EmptyBorder(5,5,5,5));
			
			grafico = new GraphPanel(pontos);
			grafico.setPreferredSize(new Dimension(400,150));
			graficoPanel.add(grafico);
			
			//No painel das formulas, temos dois JLabels, 1 para o titulo, 
			//outro para a formula resultante
			tituloFormula = new JLabel("Série de Fourier Amplitude-Fase:");
			tituloFormula.setFont(new Font(FONTE, Font.PLAIN, TAMANHO_TEXTO));
			formulaPanel.add(tituloFormula);
			
			formula = new JLabel("f(t) = ");
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
