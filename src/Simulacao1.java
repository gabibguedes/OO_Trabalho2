package src;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Simulacao1 extends Simulacao{
	
	public Simulacao1() {
		preparaGUI();
	}
	
	private void preparaGUI() {
		contentPane.setLayout(new GridLayout(3,1));
		titulo = new JLabel (SIMULACAO1);
		titulo.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		Bloco tensao = new Bloco("TENSÃO");
		Bloco corrente = new Bloco("CORRENTE");
		
		contentPane.add(tensao);
		contentPane.add(corrente);
	}
	
	public void mostrarTela() {
		topoPagina.add(titulo);
		setVisible(true);
	}
	
	private class Bloco extends JPanel{
		private JLabel tituloBloco, amplitude, angulo;
		private JTextField amplitudeTxt, anguloTxt;
		private JPanel painel, imputUsuario, imputsAmp, imputsAng, resposta;
		private String titulo;
		private JButton simular;
		
		public Bloco(String titulo){
				this.titulo = titulo;
				preparaGUI();
		}
		
		public void preparaGUI() {
			setLayout(new GridLayout(2,1));
			tituloBloco = new JLabel(titulo + ": ");
			tituloBloco.setFont(new Font("Dialog", Font.BOLD, 20));
			
			painel = new JPanel(new GridLayout(1,2));
			imputUsuario = new JPanel(new FlowLayout());
			imputsAmp = new JPanel (new GridLayout(1,2));
			imputsAng = new JPanel (new GridLayout(1,2));
			resposta = new JPanel(new GridLayout(2,1));
			
			add(tituloBloco);
			painel.add(imputUsuario);
			painel.add(resposta);
			add(painel);
			
			amplitude = new JLabel("Amplitude: ");
			amplitude.setFont(new Font("Dialog", Font.PLAIN, 12));
			amplitudeTxt = new JTextField();
			amplitudeTxt.setPreferredSize(new Dimension(100, 25));
			imputsAmp.add(amplitude);
			imputsAmp.add(amplitudeTxt);
			
			angulo = new JLabel("Angulo de fase: ");
			angulo.setFont(new Font("Dialog", Font.PLAIN, 12));
			anguloTxt = new JTextField();
			anguloTxt.setPreferredSize(new Dimension(100, 25));
			imputsAng.add(angulo);
			imputsAng.add(anguloTxt);
						
			simular = new JButton("simular");
			simular.setFont(new Font("Dialog", Font.PLAIN, 12));
			simular.setPreferredSize(new Dimension(100, 25));
			
			imputUsuario.add(imputsAmp);
			imputUsuario.add(imputsAng);
			imputUsuario.add(simular);
			
		}	
	}

}
