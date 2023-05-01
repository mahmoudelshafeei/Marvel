package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ChampionView extends JFrame{

	private JLabel back= new JLabel();
	private JPanel panel= new JPanel();
	private JTextArea display= new JTextArea();
	
	
	public ChampionView(String s){
		
		this.setBounds(150, 100, 500, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		
		back.setIcon(new ImageIcon("Images/"+ s+".jpg"));
		
		
		
		
		panel.setBackground(new Color(0.0f,0.0f,0.0f,0.5f));
		panel.setBounds(0,0,500,660);

		display=new JTextArea();
		display.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
		display.setForeground(Color.WHITE);
		Font x= new Font("Times new roman", Font.BOLD , 17);
		display.setFont(x);
		display.setEditable(false);
		
		
		panel.add(display, BorderLayout.CENTER);
		
		back.add(panel);
		
		this.add(back);
		this.repaint();
		this.revalidate();
	}


	public JLabel getBack() {
		return back;
	}


	public JPanel getPanel() {
		return panel;
	}


	public JTextArea getDisplay() {
		return display;
	}
}
