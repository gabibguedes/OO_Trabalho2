package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import controllers.AcoesSimulacao1;
import controllers.AcoesSimulacao2;
import view.GraphPanel;
import view.Simulacao;
import view.Simulacao1;
import view.Simulacao2;
import view.Tela;

public class Bloco extends JPanel{
	//Essa classe foi criada com o intuito de não repetir linhas de codigo.
	//Foi observado similaridades entre algumas partes de paineis que eram
	//exigidos nos requisitos, todos esses paineis necessitavam de inputs
	//similares do usuario para gerar gráficos similares. 
	//
	//-Na simulação 1:
	//	-Painel para Tensão;
	//	-Painel para Corrente;
	//
	//-Na simulação 2:
	//	-Painel para Componente Fundamental;
	//	-Painel para Harmonicos;

	private String titulo, simboloAmplitude;
	private JTextField amplitudeTxt, anguloTxt;
	private JSpinner ordemHTxt;
	private boolean ehHarmonico = false, ehPar;
	private GraphPanel grafico;
	private Simulacao2 simulacao2;
	private Simulacao1 simulacao1;
	private String tipo;
	
	public Bloco(String titulo, Simulacao1 simulacao){
		//Construtor para um bloco da simulação 1
		
		this.titulo = titulo;
		this.simulacao1 = simulacao1;
		tipo = Tela.SIMULACAO1;
		
		switch(titulo) {
		case Simulacao1.TENSAO:
			simboloAmplitude = " (VRMS)";
			break;
		case Simulacao1.CORRENTE:
			simboloAmplitude = " (IRMS)";
			break;
		}
		preparaGUI();
	}
	
	public Bloco(boolean ehHarmonico, boolean ehPar, String titulo, Simulacao2 simulacao2){
		//Construtor para um bloco da simulação 2
		
		this.titulo = titulo;
		this.simulacao2 = simulacao2;
		tipo = Tela.SIMULACAO2;
		this.ehHarmonico = ehHarmonico;
		this.ehPar = ehPar;
		simboloAmplitude = " (VRMS)";
		preparaGUI();
	}
	
	public void preparaGUI() {
		JLabel tituloBloco, amplitude, angulo, ordemHLb, tituloGrafico;
		JButton simular;
		List<Double> pontos;
		JPanel imputUsuario, imputsAmp, imputsAng, imputsOrdemH, resposta, graficoPane;
		
		//O painel é dividido em tres, onde ao topo está o titulo
		//a esquerda os imputs do usuario e no centro está o gráfico
		
		setPreferredSize(new Dimension(400, 200));
		setLayout(new BorderLayout());
		
		tituloBloco = new JLabel(titulo + ": ");
		tituloBloco.setFont(new Font(Tela.FONTE, Font.PLAIN, Tela.TAMANHO_SUBTITULO));
			
		imputUsuario = new JPanel(new GridLayout(3,1));	
		resposta = new JPanel(new BorderLayout());
		
		add(tituloBloco, BorderLayout.NORTH);
		add(imputUsuario, BorderLayout.WEST);
		add(resposta);
		
		//Nos imputs do usuario temos:
		
		//Amplitude:
		imputsAmp = new JPanel (new BorderLayout());
		
		amplitude = new JLabel("Amplitude"+ simboloAmplitude +": ");
		amplitude.setFont(new Font(Tela.FONTE, Font.PLAIN, Tela.TAMANHO_TEXTO));
		imputsAmp.add(amplitude, BorderLayout.NORTH);
		
		amplitudeTxt = new JTextField();
		amplitudeTxt.setPreferredSize(new Dimension(100, 25));
		amplitudeTxt.setAlignmentY(amplitude.getAlignmentY());
		
		JPanel amplitudeTxtP = new JPanel(new FlowLayout());
		amplitudeTxtP.add(amplitudeTxt);
		imputsAmp.add(amplitudeTxtP);
		
		imputsAmp.setBorder(new EmptyBorder(5, 5, 5, 5));
		imputUsuario.add(imputsAmp);
		
		//Angulo:
		imputsAng = new JPanel (new BorderLayout());
		
		angulo = new JLabel("Angulo de fase (0°): ");
		angulo.setFont(new Font(Tela.FONTE, Font.PLAIN,Tela. TAMANHO_TEXTO));
		imputsAng.add(angulo, BorderLayout.NORTH);
		
		anguloTxt = new JTextField();
		anguloTxt.setPreferredSize(new Dimension(100, 25));
		
		JPanel anguloTxtP = new JPanel(new FlowLayout());
		anguloTxtP.add(anguloTxt);
		imputsAng.add(anguloTxtP);
		
		imputsAng.setBorder(new EmptyBorder(5, 5, 5, 5));
		imputUsuario.add(imputsAng);
		
		//Ordem Harmonica ou botão de simulação
		if(ehHarmonico) {
			SpinnerModel sm;
			if(ehPar) {
				sm = new SpinnerNumberModel(2, 2, 14, 2);
			}else {
				sm = new SpinnerNumberModel(1, 1, 15, 2);
			}
			imputsOrdemH = new JPanel (new BorderLayout());
			
			ordemHLb = new JLabel("Ordem Harmonica");
			ordemHLb.setFont(new Font(Tela.FONTE, Font.PLAIN,Tela. TAMANHO_TEXTO));
			imputsOrdemH.add(ordemHLb, BorderLayout.NORTH);
			
			ordemHTxt = new JSpinner(sm);
			ordemHTxt.setPreferredSize(new Dimension(100, 25));
			
			JPanel ordemHTxtP = new JPanel(new FlowLayout());
			ordemHTxtP.add(ordemHTxt);
			imputsOrdemH.add(ordemHTxtP);
			
			imputsOrdemH.setBorder(new EmptyBorder(5, 5, 5, 5));
			imputUsuario.add(imputsOrdemH);
		}else {
			JPanel botao = new JPanel(new FlowLayout());
			simular = new JButton("Simular Forma de Onda");
			simular.setFont(new Font(Tela.FONTE, Font.PLAIN, Tela.TAMANHO_TEXTO));
			simular.setPreferredSize(new Dimension(200, 25));	
			botao.add(simular);
			imputUsuario.add(botao);
			
			//ActionListener estabelecido de acordo com o tipo de bloco
			if(tipo == Tela.SIMULACAO1) {
				simular.setActionCommand(titulo);
				simular.addActionListener(new AcoesSimulacao1(this, simulacao1));
			}else if(ehHarmonico) {
				simular.setActionCommand("lista");
				simular.addActionListener(new AcoesSimulacao2(this, simulacao2));
			}else if(tipo == Tela.SIMULACAO2) {
				simular.setActionCommand(titulo);
				simular.addActionListener(new AcoesSimulacao2(this, simulacao2));
			}
		}
		
		//Após os imputs do usuario temos o painel dos graficos
		
		//Titulo do gráfico:
		if(ehHarmonico) {
			tituloGrafico = new JLabel("Forma de Onda: ");
		}else {
			tituloGrafico = new JLabel("Forma de Onda da " + titulo + ":");
		}
		tituloGrafico.setFont(new Font(Tela.FONTE, Font.PLAIN, Tela.TAMANHO_TEXTO));
		resposta.add(tituloGrafico, BorderLayout.NORTH);
		
		//Gráfico:
		pontos = new ArrayList<Double>();
		pontos.add(0.0);
		
		if(tipo == Tela.SIMULACAO1) {
			switch (titulo) {
				case Simulacao1.TENSAO:
					grafico = new GraphPanel(220.0, -220.0, pontos);
					break;
				case Simulacao1.CORRENTE:
					grafico = new GraphPanel(100.0, -100.0, pontos);
					break;
			}			
		}else {
			grafico = new GraphPanel(220.0, -220.0, pontos);
		}
		grafico.setPreferredSize(new Dimension(450,150));
			
		graficoPane = new JPanel(new FlowLayout());
		graficoPane.add(grafico);
		resposta.add(graficoPane);
		
		resposta.setBorder(new EmptyBorder (10, 10, 10, 10));
		
	}

	public String getAmplitudeTxt() {
		return amplitudeTxt.getText();
	}

	public String getAnguloTxt() {
		return anguloTxt.getText();
	}
	
	public String getOrdemHarmonicaTxt() {
		return ordemHTxt.getValue().toString();
	}
		
	public GraphPanel getGrafico() {
		return grafico;
	}
	
	public void setEhHarmonico(boolean ehHarmonico) {
		this.ehHarmonico = ehHarmonico;
	}
	public boolean getEhHarmonico() {
		return ehHarmonico;
	}
	public void setEhPar(boolean ehPar) {
		this.ehPar = ehPar;
	}
	public boolean getEhPar() {
		return ehPar;
	}
	public String getTitulo() {
		return titulo;
	}
	public Simulacao2 getSimulacao2() {
		return simulacao2;
	}
}
