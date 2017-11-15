package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GraphPanel extends JPanel {
	//Esta classe foi feita com o código passado pelo professor
	//https://gist.github.com/roooodcastro/6325153
	
    private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 2;
    private int numberYDivisions = 2; 
    private int numberXDivisions = 2; 
    private double minScore, maxScore;
    private boolean tamanhoDefinido = false;
    
    private List<Double> scores;
    private Double Amplitude = 220.0;

    //Caso o tamanho já tenha sido definido no contrutor, 
	//ele será utilizado para definir o tamanho do gráfico
    
    //Valor minimo
    private double getMinScore() {
    	if(tamanhoDefinido) {
    		double minScore = Double.MAX_VALUE;
            for (Double score : scores) {
                minScore = Math.min(this.minScore, score);
            }
            return minScore;
    	}else {
    		double minScore = Double.MAX_VALUE;
            for (Double score : scores) {
                minScore = Math.min(minScore, score);
            }
            return minScore;
    	}
    }
    //Valor maximo:
    private double getMaxScore() {
    	if(tamanhoDefinido) {
    		double maxScore = Double.MIN_VALUE;
            for (Double score : scores) {
                maxScore = Math.max(this.maxScore, score);
            }
            return maxScore;
    	}else {
    		double maxScore = Double.MIN_VALUE;
            for (Double score : scores) {
                maxScore = Math.max(maxScore, score);
            }
            return maxScore;
    	}
    }
    
    //Construtor com valores maximo e minimo
    public GraphPanel(double maxScore, double minScore, List<Double> scores) {
    	this.maxScore = maxScore;
    	this.minScore = minScore;
        this.scores = scores;
        
        tamanhoDefinido = true;
    } 
   
    //Construtor simplificado
    public GraphPanel(List<Double> scores) {
        this.scores = scores;
    }                                  
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Metodo para montar o grafico
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //Tamanho dos eixos X e Y:
        double xScale = ((double) getWidth() - 2 * padding - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> graphPoints = new ArrayList<>();
        
        //Adição dos pontos dos scores a lista graphPoints no formato 
        //adequado para o gráfico
        for (int i = 0; i < scores.size(); i++) { 
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }
        
        //Preenchimento do painel do grafico
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
  
        //Linhas de guia dos eixos:
        for (int i = 0; i < numberYDivisions + 1; i++) {
        	int x0 = 50;
            int x1 = pointWidth + 50;
            int y0 = getHeight() - ((i * (getHeight() - 75)) / numberYDivisions + 50);
            int y1 = y0;
            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(x1 + 1 , y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1); 
        }
                
        for (int i = 0; i < scores.size(); i++) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - 75) / (scores.size() - 1) + 50;
                int x1 = x0;
                int y0 = getHeight() - 50;
                int y1 = y0 - 5;			  
                if ((i % ((int) ((scores.size() /4.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - 56, x1, 25);
                    g2.setColor(Color.BLACK);
                    String xLabel = i/1571 + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
//                  g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
//	                g2.drawLine(x0, getHeight() - 56, x1, 550);
			    }
//              g2.drawLine(x0, y0, x1, y1);
            }                 
        }
        
        //Linhas das bordas:
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);
        
        //Desenha a onda do gráfico:
        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i+=1) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
            
        }
    }
    
    //Método para atualizar os pontos e desenhar uma nova onda
    public void setScores(List<Double> scores) {
        this.scores = scores;
        invalidate();
        this.repaint();
    }
}
