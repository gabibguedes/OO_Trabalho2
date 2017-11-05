package src.model;

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

    private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 2; // tamanho do ponto
    private int numberYDivisions = 2; //estava 10//divisoes eixo y 
    private int numberXDivisions = 2; 
    private double minScore, maxScore;
    private boolean tamanhoDefinido = false;
    
    private List<Double> scores;
    private Double Amplitude = 220.0;

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
    
    public GraphPanel(double maxScore, double minScore, List<Double> scores) {
    	this.maxScore = maxScore;
    	this.minScore = minScore;
        this.scores = scores;
        
        tamanhoDefinido = true;
    } 
   
    public GraphPanel(List<Double> scores) {
        this.scores = scores;
    }                                   //desenhar elementos gráficos é um processo orientado a eventos.
                                        //JComponent é uma superclasse de JPanel .
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> graphPoints = new ArrayList<>();
           
        for (int i = 0; i < scores.size(); i++) { //.size() retorna o numero de elementos da lista
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }
        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        //g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.  
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = 50;
            int x1 = pointWidth + 50;
            int y0 = getHeight() - ((i * (getHeight() - 75)) / numberYDivisions + 50);
            int y1 = y0;
            if (scores.size() > 0) {
                g2.setColor(gridColor);// cor das linhas no grafico
                g2.drawLine(x1 + 1 , y0, getWidth() - padding, y1);//desenha as linhas no grafico
                g2.setColor(Color.BLACK);// cor dos numeros no eixo y
                String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1); //desenha as linhas dos numeros na coluna do eixo y
        }
         // and for x axis        
        for (int i = 0; i < scores.size(); i++) { //i++ = contador linhas x = 20
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - 75) / (scores.size() - 1) + 50;
                int x1 = x0;
                int y0 = getHeight() - 50;
                int y1 = y0 - 5;			   // 2 * numero de harmonicos
                if ((i % ((int) ((scores.size() /4.0)) + 1)) == 0) { //4 = numero de divisoes = 4 * numero de harmonicos
                    g2.setColor(gridColor); // cor das colunas no grafico
                    g2.drawLine(x0, getHeight() - 56, x1, 25);//desenha as colunas no grafico
                    g2.setColor(Color.BLACK); //cor dos numeros e da linha no eixo x e da linha do eixo y
                    String xLabel = i/1571 + ""; //faz as divisoes
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
//                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3); //desenha os numeros
	            	
//	               g2.drawLine(x0, getHeight() - 56, x1, 550); //desenha as colunas dos numeros na linha do eixo x
			    }  // drawLine acima era o debaixo, foi mofificado e colocado dentro
                   //g2.drawLine(x0, y0, x1, y1);
            }                 
        }
        // desenha linhas dos numeros x e y 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i+=1) {//3141 meio arco
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2); //desenha as linhas azuis
            
        }
    }
    
    public void setScores(List<Double> scores) {
        this.scores = scores;
        invalidate();
        this.repaint();
    }
    
//    public static void main(String[] args) {//3
//      	int amplitude = 55;
//    	int numerodeHamonicos =8 ;
//    	
//    	List<Double> scores = new ArrayList<>();
// 	//6.2825 e o valor para simetria da linha azul;  										
//    	for (double i = 0; i < 6.2825 * numerodeHamonicos ;  i =(double) i+0.001) { //valor de doze encurta ou alonga a onda....  *tentar alinhar com 
//      	
//       		scores.add((double) amplitude * Math.sin(i) );
//    	}
//        
//    	GraphPanel mainPanel = new GraphPanel(scores);
//    	//mainPanel.setPreferredSize(new Dimension(400,300));	//tentar mudar as dimensoes do panel
//    	mainPanel.repaint();
// 		mainPanel.setBackground(Color.LIGHT_GRAY);
//    	JFrame frame = new JFrame("DrawGraph");
//    	frame.setSize(800,600);
//    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    	frame.add(mainPanel);
//   //frame.pack();
//    	frame.setLocationRelativeTo(null);
//    	frame.setVisible(true);         
//    }
}
