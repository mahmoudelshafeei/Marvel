package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndView extends JPanel{
	
	private JLabel back = new JLabel();
	private JLabel winner = new JLabel();
	private JLabel endgame= new JLabel();
	private JPanel wins= new JPanel();
	public EndView(){
		
		this.setVisible(true);
		this.setSize(1382, 705);
		
		back.setBackground(Color.BLACK);
		back.setIcon(new ImageIcon("Images/Start (1).jpg"));
		back.setPreferredSize(new Dimension(1382,705));
		
		endgame.setIcon(new ImageIcon("Images/endgame.gif"));
		endgame.setPreferredSize(new Dimension(1382,705));
		
		wins.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
		wins.setBounds(250,300,1000,300);
		
		
		Font x= new Font("Calibri" , Font.BOLD ,100);
		winner.setForeground(Color.YELLOW);
		winner.setText("Player Wins");
		winner.setFont(x);
		winner.setFocusable(true);
		
		Font y= new Font("Calibri" , Font.BOLD ,35);
		JLabel credit= new JLabel("Wait For It...");
		credit.setForeground(Color.white);
		credit.setBounds(350, 700, 300, 100);
		credit.setFont(y);
		
		wins.add(winner);
		wins.add(credit);
		back.add(wins);
		this.add(back);
		this.repaint();
		this.revalidate();
	}

	public JLabel getEndgame() {
		return endgame;
	}

	public void setEndgame(JLabel endgame) {
		this.endgame = endgame;
	}

	public JLabel getBack() {
		return back;
	}

	public JLabel getWinner() {
		return winner;
	}
}
