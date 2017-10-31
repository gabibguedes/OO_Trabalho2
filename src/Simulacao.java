package src;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Simulacao extends Tela{
	protected JLabel titulo;
	protected JPanel pagina, topoPagina;

	public Simulacao(){
		pagina = new JPanel(new GridLayout(2,1));
		topoPagina = new JPanel(new FlowLayout());
		
		JButton voltar = new JButton("Voltar");
		voltar.setFont(new Font("Dialog", Font.BOLD, 10));
		voltar.setPreferredSize(new Dimension(55, 30));
		
		voltar.setActionCommand(MENU);
		voltar.addActionListener(new ButtonClickListener());
		
		topoPagina.add(voltar);
		pagina.add(topoPagina);
		contentPane.add(pagina);
		
	}
}
