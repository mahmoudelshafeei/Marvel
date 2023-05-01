package views;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class StartView extends JFrame {
	private JLabel back;
	private JButton play;
	private JButton controls;
	
	public StartView(){
		
		this.setVisible(true);
		this.setBounds(0,0,1370, 735);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Avengers Assemble");
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("images/logo.jpg"));
		} catch (IOException e) {
		}
		this.setIconImage(img);
		//setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		back=new JLabel();
		back.setIcon(new ImageIcon("Images/intro.gif"));
		play= new JButton("Play");
		play.setBounds(500, 600, 100, 30);
		controls= new JButton("Controls");
		controls.setBounds(800,600,100,30);
		back.add(play);
		back.add(controls);
		this.add(back);
		//System.out.println(this.getWidth());
		//System.out.println(this.getHeight());
		repaint();
		revalidate();
		
	}
	
	public void setBack(JLabel back) {
		this.back = back;
	}

	public JButton getPlay() {
		return play;
	}
	public JButton getControls() {
		return controls;
	}
	public JLabel getBack() {
		return back;
	}
	public static void main(String[] e) {
		StartView v= new StartView();
		System.out.println(v.getBounds());
	}

	

}
