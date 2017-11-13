package view;


//TRIANGULO DE POTENCIAS
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

  private int padding = 1;
  private int labelPadding = 1;
  private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
  private double coordenadaX=0;
  private double coordenadaY=0;
  private static final int TAMANHO =100; 

  public Triangulo(double coordenadaX, double coordenadaY) {
      this.coordenadaX = coordenadaX;
      this.coordenadaY=coordenadaY;
  }

  @Override
  protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
      setCoordenadaX(coordenadaX);
      setCoordenadaY(coordenadaY);
      
      double xScale = ((double) 600);
      double yScale = ((double) 600);


      // draw white background
      g2.setColor(Color.WHITE);
      g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
      g2.setColor(Color.BLACK);

      //Cria as bordas
      g2.drawLine(padding + labelPadding, TAMANHO - padding - labelPadding, padding + labelPadding, padding);
      g2.drawLine(padding + labelPadding, TAMANHO- padding - labelPadding, TAMANHO- padding, TAMANHO- padding - labelPadding);
      g2.drawLine(TAMANHO - padding, TAMANHO- padding - labelPadding, TAMANHO - padding, padding);
      g2.drawLine(padding + labelPadding , padding , TAMANHO - padding, padding );
      
      //Cria os eixos X e Y (como defini o tamanho do meu TrianguloPotencia TAMANHOxTAMANHO, as bordas estão limitidas a esse tamanho).
      g2.drawLine((TAMANHO/2)-2,TAMANHO,TAMANHO/2,2); // y1 axi
      g2.drawLine(2,TAMANHO/2,TAMANHO,TAMANHO/2); // x axi

      g2.setColor(blue);
      g2.setStroke(GRAPH_STROKE);
      g2.drawLine(TAMANHO/2, TAMANHO/2, (int) (coordenadaX+TAMANHO/2), (int) ((-coordenadaY+TAMANHO/2)));
      g2.setColor(black);
      g2.drawLine((int) (coordenadaX+TAMANHO/2), (int) ((-coordenadaY+TAMANHO/2)), (int) (coordenadaX+TAMANHO/2),TAMANHO/2);
      g2.setColor(RED);
      g2.drawLine((int) (coordenadaX+TAMANHO/2), TAMANHO/2,TAMANHO/2, TAMANHO/2);


  }
  public void setScores(double coordenadaX, double coordenadaY) {
	  this.coordenadaX = coordenadaX;
      this.coordenadaY=coordenadaY;
      invalidate();
      this.repaint();
  }

  public double getCoordenadaX() {
      return coordenadaX;
  }

  public void setCoordenadaX(double coordenadaX) {
      this.coordenadaX = coordenadaX;
  }

  public double getCoordenadaY() {
      return coordenadaY;
  }

  public void setCoordenadaY(double coordenadaY) {
      this.coordenadaY = coordenadaY;
  }
  
//  public static void main(String[] args) {
//      Triangulo tp = new Triangulo(-50 , 50);
//      tp.setScores(7028, -7921);
//      JFrame frame = new JFrame("Triangulo de Potencias");
//      frame.setSize(800,600);
//      
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      frame.add(tp);
//    
//      frame.setLocationRelativeTo(null);
//      frame.setVisible(true);
// }
}
