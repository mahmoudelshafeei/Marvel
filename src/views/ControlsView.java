package views;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlsView extends JPanel {
	private JLabel controls= new JLabel();
	private JButton back= new JButton();
	
	public ControlsView(){
		
		controls.setIcon(new ImageIcon("Images/Controls.png"));
		
		
		back.setBounds(100, 500, 150, 50);
		back.setText("Back");
		back.setActionCommand("Back");
		controls.add(back);
		
		this.add(controls);
		this.repaint();
		this.revalidate();
	}

	public JLabel getControls() {
		return controls;
	}

	public JButton getBack() {
		return back;
	}

}
