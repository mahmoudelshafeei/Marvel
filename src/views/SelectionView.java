package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Controller;
import model.world.Champion;

public class SelectionView extends JPanel {
	private JButton button = new JButton(); 
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JTextField player1 = new JTextField();
	private JTextField player2 = new JTextField();
	private JLabel label1 = new JLabel();
	private JLabel label2 = new JLabel();
	private JLabel label3= new JLabel();
	private JLabel label4 = new JLabel();
	private JButton start;
	private JButton addToTeam;
	private JButton SetLeader;
	
	private JTextArea details = new JTextArea();
	
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private JLabel back=new JLabel();
	
	public JLabel getBack() {
		return back;
	}
	public SelectionView(ArrayList<Champion> champions) throws IOException
	{
		this.setVisible(true);
		this.setSize(1382, 705);
		
		
		//the label containing the background ( we add all our containers on it)
		back.setIcon(new ImageIcon("Images/Start (1).jpg"));
		
		// the panel that contains the text fields and labels and button
		panel1.setBounds(0,0,1382,50);
		panel1.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		panel1.setLayout(new FlowLayout());
		
		back.add(panel1,BorderLayout.NORTH);
		
		// the panel that contains the back ground
		panel2.setBounds(0,50,this.getWidth(),this.getHeight()-80);
		
		panel2.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		
		back.add(panel2,BorderLayout.CENTER);
		
		panel3.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		panel3.setBounds(0, this.getHeight()-30, this.getWidth(),30);
		panel3.setLayout( new BorderLayout());

		addToTeam= new JButton("Add To Team");
		addToTeam.setPreferredSize(new Dimension(500,30));
		panel3.add(addToTeam , BorderLayout.WEST);
		SetLeader=new JButton("Set Leader");
		SetLeader.setPreferredSize(new Dimension(500,30));
		panel3.add(SetLeader , BorderLayout.EAST);
		back.add(panel3, BorderLayout.SOUTH);
		
		// labels and text fields that will get added to panel 1
		player1.setPreferredSize(new Dimension(100,30));
		player2.setPreferredSize(new Dimension(100,30));
		Font ff= new Font("Ghielz" , Font.BOLD, 20);
		
	
		label1.setText("Player One: ");
		label1.setForeground(Color.white);
		label1.setFont(ff);
		label1.setBounds(700, 15, 100, 30);
		label2.setText("Player Two: ");
		label2.setForeground(Color.white);
		label2.setFont(ff);
	
		panel1.add(label1);
		panel1.add(player1);
		button.setBackground(Color.white);
		panel1.add(label2 );
		panel1.add(player2);
		start= new JButton("Start");
		panel1.add(start);
		
		
		this.add(back);
		
		for(int i = 0 ; i < champions.size(); i++)
		{
			JButton B = new JButton();
			B.setActionCommand(champions.get(i).getName());
			B.setPreferredSize(new Dimension(140,196));
			B.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
			B.setOpaque(false);
			B.setContentAreaFilled(false);
			B.setContentAreaFilled(false);
			B.setIcon(new ImageIcon("Images/"+champions.get(i).getName()+ ".png"));
			B.setRolloverEnabled(false);
			buttons.add(B);
			panel2.add(B);
		}
	
		details.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));;
		details.setForeground(Color.WHITE);
		details.setEditable(false);
		
		Font x= new Font("Times New Roman" , Font.BOLD , 20);
		details.setFont(x);
		panel2.add(details);
		
		
		Font y= new Font("Times New Roman" , Font.BOLD , 33);
		label3.setForeground(Color.WHITE);
		label3.setPreferredSize(new Dimension(800,60));
		label3.setText("You Will Each Take Turns Choosing Your Champions");
		label3.setFont(y);
		panel2.add(label3);
		
		label4.setPreferredSize(new Dimension(600,100));
		label4.setFont(y);
		label4.setText("Player 1, Please Choose A Champion.");
		label4.setForeground(Color.WHITE);
		panel2.add(label4);
		
		System.out.println(this.getWidth());
		System.out.println(this.getHeight());
	    this.revalidate();
	    this.repaint();
	    
	    
	}
	public JLabel getLabel3() {
		return label3;
	}
	public JLabel getLabel4() {
		return label4;
	}
	public JLabel getLabel1() {
		return label1;
	}
	public JLabel getLabel2() {
		return label2;
	}
	public void setBack(JLabel back) {
		this.back = back;
	}
	public JTextArea getDetails() {
		return details;
	}
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	public JButton getAddToTeam() {
		return addToTeam;
	}
	public JButton getSetLeader() {
		return SetLeader;
	}
	public JButton  getStart() {
		return start;
	}
	public JPanel getPanel3() {
		return panel3;
	}
	
	
	public JTextField getPlayer1() {
		return player1;
	}
	public JTextField getPlayer2() {
		return player2;
	}


	

	public JButton getButton() {
		return button;
	}
	public JPanel getPanel1() {
		return panel1;
	}
	public JPanel getPanel2() {
		return panel2;
	}
	
	

	

}
