package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class GameView extends JPanel {
	
	private JLabel back=new JLabel();
	private JPanel left=new JPanel();
	private JPanel right=new JPanel();
	private JPanel Board=new JPanel();
	private JPanel BoardPanel= new JPanel();
	private JPanel TurnPanel= new JPanel();
	private JPanel AbilitiesCast =new JPanel();
	private JLabel player1=new JLabel();
	private JLabel player2=new JLabel();
	private JButton Abilities1 = new JButton();
	private JButton Effects1 = new JButton();
	private JButton Abilities2 = new JButton();
	private JButton Effects2 = new JButton();
	private JButton endTurn = new JButton();
	private ArrayList<JLabel> TurnLabels= new ArrayList<JLabel>();
	private ArrayList<JLabel> CoverLabels= new ArrayList<JLabel>();
	private ArrayList<JButton> firstButtons=new ArrayList<JButton>();
	private ArrayList<JButton> secondButtons= new ArrayList<JButton>();
	private ArrayList<JButton> BoardButtons=new ArrayList<JButton>();
	private JTextArea display1=new JTextArea();
	private JTextArea display2= new JTextArea();
	private JLabel myChampion= new JLabel();
	private JButton useLeaderAbility= new JButton();
	private JTextArea currentInfo =new JTextArea();
	

	
	public GameView(ActionListener c , KeyListener l){
		this.setVisible(true);
		this.setSize(1382, 744);
		
		
		
		// organizing the main frame
		//the label containing the background ( we add all our containers on it)
		back.setIcon(new ImageIcon("Images/Start (1).jpg"));
		back.setSize(new Dimension(1382,705));
		
		
		
		
		Font x= new Font("Calibri" , Font.BOLD , 18);
		
		//the panel on the left side splitting to other to panels for (player1, player2)
		left= new JPanel();
		left.setBounds(0,0,300,back.getHeight());
		left.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		
		// the panel containing player1 details
		//panel1= new JPanel();
		//panel1.setPreferredSize(new Dimension(200,back.getHeight()/2));
		//panel1.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		
		
		player1= new JLabel("Player 1");
		player1.setForeground(Color.BLUE);
		player1.setFont(x);
		player1.setForeground(Color.WHITE);
		player1.setPreferredSize(new Dimension(150,30));
		
		
		
		Abilities1= new JButton("Abilities");
		Effects1= new JButton("Effects");
		Abilities1.setEnabled(false);
		Effects1.setEnabled(false);
		
		
		
		/*panel on the player1 details holding both the abilities and the effects of the selected 
		Champion from the first player team.*/
		JPanel bottom1= new JPanel();
		bottom1.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		bottom1.setLayout(new FlowLayout());
		bottom1.add(Abilities1);
		bottom1.add(Effects1);
		bottom1.setPreferredSize(new Dimension(300,30));
		bottom1.setBackground(new Color(0.0f ,0.0f,0.0f,0.0f));
		
		//adding the above panel to the firstPlayer panel
		
		
		
		// add the empty buttons for the first players champions
		
		
		
		//the jlabel containing the display for the first player
		
		
		
		// add the area of displaying the first player's selected champion's details 
		display1 = new JTextArea();
		display1.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		display1.removeMouseListener(display1.getMouseListeners()[0]);
		display1.setForeground(Color.WHITE);
		display1.setPreferredSize(new Dimension(200,220));
		Font f= new Font("Times New Roman" , Font.BOLD , 15);
		display1.setText("Leader Ability Used: ");
		display1.setFont(f);
		
		
		
		
		
		// adding the first player panel to the top left corner.
		//left.add(panel1);
		
		
		//panel representing the second player details.
		//panel2= new JPanel();
		//panel2.setPreferredSize(new Dimension(200,back.getHeight()/2-50));
		//panel2.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		
		
		player2= new JLabel("Player 2");
		player2.setForeground(Color.BLUE);
		player2.setFont(x);	
		player2.setForeground(Color.WHITE);
		player2.setPreferredSize(new Dimension(150,30));
		left.add(player2);
		
		
		Abilities2= new JButton("Abilities");
		Effects2= new JButton("Effects");
		Abilities2.setEnabled(false);
		Effects2.setEnabled(false);
		
		//the panel representing the abilities and effects of the selected champion from the second player team
		JPanel bottom2= new JPanel();
		bottom2.setLayout(new FlowLayout());
		bottom2.add(Abilities2);
		bottom2.add(Effects2);
		bottom2.setPreferredSize(new Dimension(300,30));
		bottom2.setBackground(new Color(0.0f,0.0f,0.0f, 0.0f));
		left.add(bottom2 );
		
		
		// add the empty buttons for the second players champions
		for (int i = 1; i < 4; i++) {
			JButton b = new JButton();
			b.setPreferredSize(new Dimension(130,25));
			b.setActionCommand("2" + i);
			left.add(b);
			secondButtons.add(b);
		}
				
				
		// add the area of displaying the second player's selected champion's details
		display2 = new JTextArea();
		display2.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		display2.setPreferredSize(new Dimension(200,200));
		display2.setForeground(Color.WHITE);
		display2.removeMouseListener(display2.getMouseListeners()[0]);
		display2.setFont(f);
		display2.setText("Leader Ability Used: ");
		left.add(display2);
				
		//adding the second player details to the bottom left corner of the view.
		
		//this part we changed to put the first player at the bottom of the screen
		left.add(player1);
		left.add(bottom1);
		for(int i=1; i<4 ;i++)
		{	
			
			JButton b= new JButton();
			b.setActionCommand("1"+i);
			b.setPreferredSize(new Dimension(130,25));
			left.add(b);
			firstButtons.add(b);
		}
		left.add(display1);
		
		//adding both player details to the left.
		back.add(left, BorderLayout.WEST);
		
		//the panel holding information for both the current Champion details, the end turn command and the turn order. 
		right= new JPanel();
		right.setBounds(this.getWidth()-300,0,300,back.getHeight());
		right.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		
		//the panel holding the information of the current Champion
		
		
		//the button representing the current champion at the top right corner
	
		myChampion.setPreferredSize(new Dimension(140,196));
		myChampion.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
		myChampion.setOpaque(false);
		right.add(myChampion);
	
		//adding the current champion detail to the top right corner of the view
		
		
		//the panel holding both the end turn command and the turn order
		
		
		//current champion info
		currentInfo.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		currentInfo.setPreferredSize(new Dimension(250,200));
		currentInfo.setForeground(Color.WHITE);
		currentInfo.setFont(f);
		currentInfo.removeMouseListener(currentInfo.getMouseListeners()[1]);
		currentInfo.setEditable(false);
		right.add(currentInfo);
		
		AbilitiesCast.setPreferredSize(new Dimension(300,250));
		AbilitiesCast.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
		right.add(AbilitiesCast);
		
		
		endTurn= new JButton("End Turn");
		right.add(endTurn);
		
		useLeaderAbility.setText("Use Leader Ability");
		right.add(useLeaderAbility);
		
		
		
		//adding the turn order and end turn command to the bottom right corner of the view
		
		
		// adding the current champion details, end turn command, and the turn order view.
		back.add(right);
		
		//the turn order panel
		TurnPanel.setBackground(new Color(0.0f,0.0f,0.0f,0.5f));
		TurnPanel.setBounds(300, 0, 782, 75);
		
		for(int i=0;i<6;i++)
		{
			JLabel j= new JLabel();
			j.setPreferredSize(new Dimension(56,70));
			TurnPanel.add(j);
			j.addKeyListener(l);;
			TurnLabels.add(j);
		}
		
		
		//adding the Turn Order
		back.add(TurnPanel);
		
		//fading the Board panel
		BoardPanel.setBackground(new Color(0.0f,0.0f,0.0f,0.5f));
		BoardPanel.setBounds(300,75,782,back.getHeight());
		
		//the board of the game
		Board= new JPanel();
		Board.setPreferredSize(new Dimension(680,back.getHeight()-100));
		Board.setLayout(new GridLayout(5,5));
		Board.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		
		//filling the board with buttons.
		for(int i=4 ; i>=0 ; i--)
		{
			for(int j=4 ; j>=0 ; j--)
			{
				JButton k= new JButton();
				k.setFocusable(true);
				k.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
				k.setOpaque(false);
				Board.add(k);
				BoardButtons.add(k);
				k.setRolloverEnabled(false);
				k.addActionListener(c);
				k.addKeyListener(l);
			}
		}
		
		//filling the board with JLabels
		for(int i=4 ; i>=0 ; i--)
		{
			for(int j= 4; j>=0 ; j--)
			{
				JLabel k= new JLabel();
				k.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
				k.addKeyListener(l);
				CoverLabels.add(k);
			}
		}
		
		
		
		//adding the board to the center of the Board Panel (for the fade)
		BoardPanel.add(Board);
		
		//adding the board to the view
		back.add(BoardPanel);
		
		
		
		
		System.out.println(this.getWidth());
		System.out.println(this.getHeight());
		
	    this.add(back);
		this.repaint();
		this.revalidate();
		
	}
	




	public JPanel getTurnPanel() {
		return TurnPanel;
	}





	public ArrayList<JLabel> getTurnLabels() {
		return TurnLabels;
	}





	public ArrayList<JLabel> getCoverLabels() {
		return CoverLabels;
	}





	


	public JTextArea getCurrentInfo() {
		return currentInfo;
	}


	public JPanel getLeft() {
		return left;
	}

	public JPanel getRight() {
		return right;
	}

	public JLabel getBack() {
		return back;
	}

	public JButton getUseLeaderAbility() {
		return useLeaderAbility;
	}

	public ArrayList<JButton> getBoardButtons() {
		return BoardButtons;
	}

	public JLabel getMyChampion() {
		return myChampion;
	}

	

	public JTextArea getDisplay1() {
		return display1;
	}

	public JTextArea getDisplay2() {
		return display2;
	}

	public ArrayList<JButton> getFirstButtons() {
		return firstButtons;
	}

	public ArrayList<JButton> getSecondButtons() {
		return secondButtons;
	}

	public JButton getEndTurn() {
		return endTurn;
	}
	public JButton getAbilities1() {
		return Abilities1;
	}
	public JButton getEffects1() {
		return Effects1;
	}
	public JButton getAbilities2() {
		return Abilities2;
	}
	public JButton getEffects2() {
		return Effects2;
	}
	public JLabel getPlayer1() {
		return player1;
	}
	public JLabel getPlayer2() {
		return player2;
	}
	
	
	public JPanel getBoard() {
		return Board;
	}
	

	
	public JPanel getAbilitiesCast() {
		return AbilitiesCast;
	}





	public JPanel getBoardPanel() {
		return BoardPanel;
	}





	public void setAbilitiesCast(JPanel abilitiesCast) {
		AbilitiesCast = abilitiesCast;
	}









	


	
	
}
