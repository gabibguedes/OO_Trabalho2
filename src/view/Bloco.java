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
import javax.swing.JTextField;
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
	private JTextField amplitudeTxt, anguloTxt, ordemHTxt;
	private JPanel imputUsuario, imputsAmp, imputsAng, imputsOrdemH, resposta;
	private String titulo, simboloAmplitude;
	private JButton simular;
	private GraphPanel grafico;
	private Simulacao1 simulacao1;
	private Simulacao2 simulacao2;
	private List<Double> pontos;
	private String tipo;
	private boolean ehHarmonico = false;
	
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
	
	public Bloco(boolean ehHarmonico, String titulo, Simulacao2 simulacao2){
		this.titulo = titulo;
		this.simulacao2 = simulacao2;
		tipo = Tela.SIMULACAO2;
		this.ehHarmonico = ehHarmonico;
		simboloAmplitude = "";
		preparaGUI();
	}
	
	public void preparaGUI() {
		setPreferredSize(new Dimension(500, 300));
		setLayout(new BorderLayout(2,2));
		tituloBloco = new JLabel(titulo + ": ");
		tituloBloco.setFont(new Font(Tela.FONTE, Font.PLAIN, Tela.TAMANHO_SUBTITULO));
			
		if(ehHarmonico) {
			imputUsuario = new JPanel(new GridLayout(4,1));
		}else {
			imputUsuario = new JPanel(new GridLayout(3,1));
		}		
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
		
		angulo = new JLabel("Angulo de fase (0º): ");
		angulo.setFont(new Font(Tela.FONTE, Font.PLAIN,Tela. TAMANHO_TEXTO));
		anguloTxt = new JTextField();
		anguloTxt.setPreferredSize(new Dimension(100, 25));
		imputsAng.add(angulo, BorderLayout.NORTH);
		anguloTxtP.add(anguloTxt);
		imputsAng.add(anguloTxtP);
		imputsAng.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ordemHLb = new JLabel("Ordem Harmonica");
		ordemHLb.setFont(new Font(Tela.FONTE, Font.PLAIN,Tela. TAMANHO_TEXTO));
		ordemHTxt = new JTextField();
		ordemHTxt.setPreferredSize(new Dimension(100, 25));
		imputsOrdemH.add(ordemHLb, BorderLayout.NORTH);
		ordemHTxtP.add(ordemHTxt);
		imputsOrdemH.add(ordemHTxtP);
		imputsOrdemH.setBorder(new EmptyBorder(5, 5, 5, 5));
					
		simular = new JButton("Simular Forma de Onda");
		simular.setFont(new Font(Tela.FONTE, Font.PLAIN, Tela.TAMANHO_TEXTO));
		simular.setPreferredSize(new Dimension(200, 25));	
		botao.add(simular);
		
		if(tipo == Tela.SIMULACAO1) {
			simular.setActionCommand(titulo);
			simular.addActionListener(new AcoesSimulacao1(this, simulacao1));
		}else if(ehHarmonico) {
			simular.setActionCommand("Harmonicos");
			simular.addActionListener(new AcoesSimulacao2(this, simulacao2));
		}else if(tipo == Tela.SIMULACAO2) {
			simular.setActionCommand(titulo);
			simular.addActionListener(new AcoesSimulacao2(this, simulacao2));
		}
					
		imputUsuario.add(imputsAmp);
		imputUsuario.add(imputsAng);
		if(ehHarmonico) {
			imputUsuario.add(imputsOrdemH);
		}
		imputUsuario.add(botao);
			
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
			grafico = new GraphPanel(pontos);
		}
			
		
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
	
	public void setEhHarmonico(boolean ehHarmonico) {
		this.ehHarmonico = ehHarmonico;
	}
}
