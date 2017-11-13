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
	private JLabel tituloBloco, amplitude, angulo, ordemHLb, tituloGrafico;
	private JTextField amplitudeTxt, anguloTxt;
	private JSpinner ordemHTxt;
	private JPanel imputUsuario, imputsAmp, imputsAng, imputsOrdemH, resposta, graficoPane;
	private String titulo, simboloAmplitude;
	private JButton simular;
	private GraphPanel grafico;
	private Simulacao1 simulacao1;
	private Simulacao2 simulacao2;
	private List<Double> pontos;
	private String tipo;
	private boolean ehHarmonico = false, ehPar;
	
	public Bloco(String titulo, Simulacao1 simulacao){
		
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
	
	public Bloco( String titulo, Simulacao2 simulacao2){
		this.titulo = titulo;
		this.simulacao2 = simulacao2;
		tipo = Tela.SIMULACAO2;
		ehHarmonico = false;
		simboloAmplitude = " (VRMS)";
		preparaGUI();
	}
	
	public Bloco(boolean ehPar, String titulo, Simulacao2 simulacao2){
		this.titulo = titulo;
		this.simulacao2 = simulacao2;
		tipo = Tela.SIMULACAO2;
		ehHarmonico = true;
		this.ehPar = ehPar;
		simboloAmplitude = " (VRMS)";
		preparaGUI();
	}
	
	public void preparaGUI() {
		setPreferredSize(new Dimension(400, 200));
		setLayout(new BorderLayout(2,2));
		tituloBloco = new JLabel(titulo + ": ");
		tituloBloco.setFont(new Font(Tela.FONTE, Font.PLAIN, Tela.TAMANHO_SUBTITULO));
			
		imputUsuario = new JPanel(new GridLayout(3,1));
				
		imputsAmp = new JPanel (new BorderLayout());
		imputsAng = new JPanel (new BorderLayout());
		imputsOrdemH = new JPanel (new BorderLayout());
		resposta = new JPanel(new BorderLayout());
		JPanel botao = new JPanel(new FlowLayout());
		JPanel ordemHTxtP = new JPanel(new FlowLayout());
		JPanel amplitudeTxtP = new JPanel(new FlowLayout());
		JPanel anguloTxtP = new JPanel(new FlowLayout());
		
		add(tituloBloco, BorderLayout.NORTH);
		add(imputUsuario, BorderLayout.WEST);
		add(resposta);

		amplitude = new JLabel("Amplitude"+ simboloAmplitude +": ");	
		amplitude.setFont(new Font(Tela.FONTE, Font.PLAIN, Tela.TAMANHO_TEXTO));
		amplitudeTxt = new JTextField();
		amplitudeTxt.setPreferredSize(new Dimension(100, 25));
		amplitudeTxt.setAlignmentY(amplitude.getAlignmentY());
		imputsAmp.add(amplitude, BorderLayout.NORTH);
		amplitudeTxtP.add(amplitudeTxt);
		imputsAmp.add(amplitudeTxtP);
		imputsAmp.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		angulo = new JLabel("Angulo de fase (0°): ");
		angulo.setFont(new Font(Tela.FONTE, Font.PLAIN,Tela. TAMANHO_TEXTO));
		anguloTxt = new JTextField();
		anguloTxt.setPreferredSize(new Dimension(100, 25));
		imputsAng.add(angulo, BorderLayout.NORTH);
		anguloTxtP.add(anguloTxt);
		imputsAng.add(anguloTxtP);
		imputsAng.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		imputUsuario.add(imputsAmp);
		imputUsuario.add(imputsAng);

		if(ehHarmonico) {
			SpinnerModel sm;
			if(ehPar) {
				sm = new SpinnerNumberModel(2, 2, 8, 2);
			}else {
				sm = new SpinnerNumberModel(1, 1, 9, 2);
			}
			
			ordemHLb = new JLabel("Ordem Harmonica");
			ordemHLb.setFont(new Font(Tela.FONTE, Font.PLAIN,Tela. TAMANHO_TEXTO));
			ordemHTxt = new JSpinner(sm);
			ordemHTxt.setPreferredSize(new Dimension(100, 25));
			imputsOrdemH.add(ordemHLb, BorderLayout.NORTH);
			ordemHTxtP.add(ordemHTxt);
			imputsOrdemH.add(ordemHTxtP);
			imputsOrdemH.setBorder(new EmptyBorder(5, 5, 5, 5));
			imputUsuario.add(imputsOrdemH);
		}else {
			simular = new JButton("Simular Forma de Onda");
			simular.setFont(new Font(Tela.FONTE, Font.PLAIN, Tela.TAMANHO_TEXTO));
			simular.setPreferredSize(new Dimension(200, 25));	
			botao.add(simular);
			
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
			
			imputUsuario.add(botao);
		}
		
			
		if(ehHarmonico) {
			tituloGrafico = new JLabel("Forma de Onda: ");
			grafico = new GraphPanel(220.0, -220.0, pontos);
		}else {
			tituloGrafico = new JLabel("Forma de Onda da " + titulo + ":");
		}
		tituloGrafico.setFont(new Font(Tela.FONTE, Font.PLAIN, Tela.TAMANHO_TEXTO));
		
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
			
		graficoPane = new JPanel(new FlowLayout());
		
		grafico.setPreferredSize(new Dimension(450,150));
		graficoPane.add(grafico);
		
		resposta.add(tituloGrafico, BorderLayout.NORTH);
		resposta.setBorder(new EmptyBorder (10, 10, 10, 10));
		resposta.add(graficoPane);
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
