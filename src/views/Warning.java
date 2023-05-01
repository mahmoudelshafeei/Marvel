package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Warning extends JFrame implements KeyListener{
	
	private JLabel t;
	private JPanel panel;
	
	public Warning(String s){
		panel= new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setPreferredSize(new Dimension(300,200));
		this.setSize(500,200);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Warning: " );
		this.addKeyListener(this);
		t= new JLabel();
		t.setForeground(Color.red);
		Font x= new Font("Times new roman", Font.BOLD , 15);
		t.setFont(x);
		t.setText(s);
		panel.add(t, BorderLayout.SOUTH);
		this.add(panel);
		this.repaint();
		this.revalidate();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		this.dispose();
	}

}
