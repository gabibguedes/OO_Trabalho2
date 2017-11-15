package view;

import java.awt.BasicStroke;
import java.awt.Color;
import static java.awt.Color.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Triangulo extends JPanel {
	//A partir do codigo passado pelo professor, foi possivel
	//uma nova organização para construir uma classe que faça
	//gráficos de triangulos
	
	//https://gist.github.com/roooodcastro/6325153
	
	private int padding = 1;
	private int labelPadding = 1;
	private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
	private double coordenadaX=0;
	private double coordenadaY=0;
	private static final int TAMANHO =100; 

	//O contrutor é chamado com as coordenadas x e y
	public Triangulo(double coordenadaX, double coordenadaY) {
    	this.coordenadaX = coordenadaX;
    	this.coordenadaY=coordenadaY;
	}

	@Override
	protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	//Método para a construção do grafico
    	
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    	//Tamanho dos eixos
    	double xScale = ((double) 600);
    	double yScale = ((double) 600);

    	//Preenchimento do painel do grafico
    	g2.setColor(Color.WHITE);
    	g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
    	g2.setColor(Color.BLACK);
    	
    	//Cria as bordas
    	g2.drawLine(padding + labelPadding, TAMANHO - padding - labelPadding, padding + labelPadding, padding);
    	g2.drawLine(padding + labelPadding, TAMANHO- padding - labelPadding, TAMANHO- padding, TAMANHO- padding - labelPadding);
    	g2.drawLine(TAMANHO - padding, TAMANHO- padding - labelPadding, TAMANHO - padding, padding);
    	g2.drawLine(padding + labelPadding , padding , TAMANHO - padding, padding );
     
    	//Cria os eixos X e Y 
    	g2.drawLine((TAMANHO/2)-2,TAMANHO,TAMANHO/2,2);
    	g2.drawLine(2,TAMANHO/2,TAMANHO,TAMANHO/2);
    	
    	//Desenha as retas
    	g2.setColor(blue);
    	g2.setStroke(GRAPH_STROKE);
    	g2.drawLine(TAMANHO/2, TAMANHO/2, (int) (coordenadaX+TAMANHO/2), (int) ((-coordenadaY+TAMANHO/2)));
    	g2.setColor(green);
    	g2.drawLine((int) (coordenadaX+TAMANHO/2), (int) ((-coordenadaY+TAMANHO/2)), (int) (coordenadaX+TAMANHO/2),TAMANHO/2);
    	g2.setColor(RED);
    	g2.drawLine((int) (coordenadaX+TAMANHO/2), TAMANHO/2,TAMANHO/2, TAMANHO/2);
  	}
	
	public void setScores(double coordenadaX, double coordenadaY) {
		//Método para atualizar o grafico
    	this.coordenadaX = coordenadaX;
    	this.coordenadaY=coordenadaY;
    	invalidate();
    	this.repaint();
	}
}
