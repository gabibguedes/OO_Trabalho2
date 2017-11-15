package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.MudarTela;

public interface Simulacao{
	//Essa interface foi criada para ser utilizada nas simulações
	//ela define a organização inicial dessas classes pelo metodo
	//preparaSimulação(), dessa forma todas as simulações ficam 
	//com um aspecto parecido (titulo, simulações, botoes voltar 
	//e simular).

	JLabel titulo = new JLabel("",JLabel.CENTER);
	JPanel topoPagina = new JPanel(); 
	JPanel botoes = new JPanel();
	JButton voltar = new JButton();
	JButton simularResultado = new JButton();
	

	
	public default JPanel preparaSimulacao(String simulacao, Tela tela) {
		//Nessa organização inicial, o content pane é dividido em 3 partes.
		tela.getContentPane().setLayout(new BorderLayout(3,1));
		
		//1a parte = titulo
		titulo.setText(simulacao);
		titulo.setFont(new Font(Tela.FONTE, Font.PLAIN, Tela.TAMANHO_SUBTITULO));
		tela.getContentPane().add(titulo, BorderLayout.NORTH);
		
		//2a parte = painel das simulações, que será preenchido somente na
		//classe da simulação especifica, nele que ocorre toda a simulação
		JPanel simulacaoPane = new JPanel();
		tela.getContentPane().add(simulacaoPane, BorderLayout.CENTER);
		
		//3a parte = botões para voltar ao menu e para realizar a simulação
		botoes.setLayout(new FlowLayout());
		
		voltar.setText("Voltar ao Menu Principal");
		voltar.setFont(new Font(Tela.FONTE, Font.BOLD, Tela.TAMANHO_TEXTO));
		voltar.setPreferredSize(new Dimension(250, 25));
		
		//OBS.:O ActionListenner de voltar eh feito pela classe MudarTela, enquanto
		//o do botão simular resultado só é definido na classe da simulação a qual
		//ele pertence.
		voltar.setActionCommand(Tela.MENU);
		voltar.addActionListener(new MudarTela(tela.getJanela()));
		
		simularResultado.setText("Simular Resultado");
		simularResultado.setFont(new Font(Tela.FONTE, Font.BOLD, Tela.TAMANHO_TEXTO));
		simularResultado.setPreferredSize(new Dimension(250, 25));
		
		botoes.add(voltar);
		botoes.add(simularResultado);
		
		tela.getContentPane().add(botoes, BorderLayout.SOUTH);
		
		//O método retorna a SimulacaoPane que será preenchida na simulação que esse
		//método é chamado
		return simulacaoPane;
	}
	
	public default void mostrarTela(Tela tela) {
		//Método para a visibilidade da tela
		tela.getJanela().setVisible(true);
		tela.getContentPane().setVisible(true);

	}
}
