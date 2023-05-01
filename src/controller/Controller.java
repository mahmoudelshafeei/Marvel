package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;
import views.ChampionView;
import views.ControlsView;
import views.EndView;
import views.GameView;
import views.SelectionView;
import views.StartView;
import views.Warning;
import engine.Game;
import engine.Player;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;


public class Controller implements ActionListener , KeyListener{
	private Game game;
	private SelectionView selection;
	private ArrayList<Champion> firstPlayerTeam;
	private ArrayList<Champion> secondPlayerTeam;
	private int counter=0;
	private int addCounter=0;
	private int addCounter2=0;
	private boolean firstLeader=false;
	private boolean secondLeader=false;
	private Champion Leader1;
	private Champion Leader2;
	private JButton Selected;
	private ArrayList<JButton> buttons;
	private GameView play;
	private ChampionView display;
	private StartView start;
	private ControlsView controls=new ControlsView();
	private boolean p1=false;
	private boolean p2=false;
	private boolean p3=false;
	private boolean p4=false;
	private Ability a;
	private ArrayList<JButton> AbilitiesButtons=new ArrayList<JButton>();
	private boolean DA;
	private boolean SA;
	private EndView end= new EndView();
	private static AudioInputStream audio;
	private static Clip clip;
	private static Clip clip2;
	public Controller()  throws IOException {
		
		
		start= new StartView();
		start.addKeyListener(this);
		playSound("Sounds/Marvel Intro.wav");
		start.getPlay().addActionListener(this);
		start.getControls().addActionListener(this);
		p1=true;
	
		firstPlayerTeam = new ArrayList<Champion>();
		secondPlayerTeam = new ArrayList<Champion>();
		game.loadAbilities("Abilities.csv");
		game.loadChampions("Champions.csv");

		
	}
	public void resetGame(Game g, GameView p){
		
		ArrayList<Object> boardObject= new ArrayList<Object>();
		
		for(int i=4 ; i>=0 ; i--)
		{
			for(int j= 4; j>=0 ; j--)
			{
				boardObject.add(g.getBoard()[i][j]);
			}
		}
		
		
		for(int i=0; i<p.getBoardButtons().size() ; i++)
		{
			Object o = boardObject.get(i);
			JButton b= p.getBoardButtons().get(i);
			JLabel j= p.getCoverLabels().get(i);
			
			
			if(o !=null)
			{
				if(o instanceof Champion)
				{
					ImageIcon icon= new ImageIcon("Images/"+((Champion) o).getName()+".gif");
					b.setActionCommand(((Champion) o).getName());
					b.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
					b.setIcon(icon);
					j.setForeground(Color.WHITE);
					if(game.getFirstPlayer().getTeam().contains(o))
					{
						b.setBorder(BorderFactory.createLineBorder(Color.red));
					}else
					{
						b.setBorder(BorderFactory.createLineBorder(Color.GREEN));
					}
				}
				else
				{	
					if(o instanceof Cover)
					{
					ImageIcon icon= new ImageIcon("Images/Cover.png");
					b.setActionCommand("Cover");
					b.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
					b.setIcon(icon);
					j.setForeground(Color.BLACK);
					}

				}	
				String s="              "+ ((Damageable)boardObject.get(i)).getCurrentHP();
				j.setText(s);
				p.getBoardButtons().get(i).add(j);
				
				
			}
			else {
				b.setIcon(null);
				b.setActionCommand(null);
				j.setText("");
				b.setBorder( new JButton().getBorder());
				b.setFocusable(true);
			}
			
			
			
		}
		
		
		
		
		ImageIcon icon= new ImageIcon("Images/" + g.getCurrentChampion().getName()+".png");
		p.getMyChampion().setIcon(icon);
		
		
		String s="";
		s+= g.getCurrentChampion().ToString() + "Is Leader: ";
		if(g.getFirstPlayer().getLeader().equals(g.getCurrentChampion()) || g.getSecondPlayer().getLeader().equals(g.getCurrentChampion()) )
		{
			s+= "true";
		}
		else
			s+= "false";
		
		p.getCurrentInfo().setText(s);
		
		
		for(int i=AbilitiesButtons.size()-1 ; i>=0; i--)
		{
			
			p.getAbilitiesCast().remove(AbilitiesButtons.get(i));
			AbilitiesButtons.remove(i);
			p.repaint();
			p.revalidate();

		}
		for(int i=0; i<game.getCurrentChampion().getAbilities().size(); i++)
		{
			JButton j= new JButton(game.getCurrentChampion().getAbilities().get(i).getName());
			j.setPreferredSize(new Dimension(200,50));
			j.setActionCommand(""+i);
			j.addActionListener(this);
			j.addKeyListener(this);
			j.setToolTipText(game.getCurrentChampion().getAbilities().get(i).toString());
			AbilitiesButtons.add(j);
			p.getAbilitiesCast().add(j);
		}
		
		
		ArrayList<Champion> ca=new ArrayList<Champion>();
		for(int i=0; i<6 ; i++)
		{
			JLabel j = p.getTurnLabels().get(i);
			if (!g.getTurnOrder().isEmpty()) 
			{
				j.setIcon(new ImageIcon("Images/"+ ((Champion) g.getTurnOrder().peekMin()).getName()+ "s.png"));
				ca.add((Champion) g.getTurnOrder().remove());
			}
			else{
				j.setIcon(null);
			}
		}
		for(int i= ca.size()-1; i>=0 ;i--)
		{
			g.getTurnOrder().insert(ca.remove(i));
		}
		
		
		
		
		
		
		
				
		
		p.revalidate();
		p.repaint();
		start.repaint();
		start.revalidate();
	}
	public void setGame(Game g , GameView p){
		
		resetGame(g,p);
		for(int i=0; i<3 ;i++)
		{	
			
			p.getFirstButtons().get(i).addActionListener(this);
			p.getFirstButtons().get(i).addKeyListener(this);
			p.getFirstButtons().get(i).setText(g.getFirstPlayer().getTeam().get(i).getName());
		}
		for(int i=0; i<3 ;i++)
		{	
			
			p.getSecondButtons().get(i).addActionListener(this);;
			p.getSecondButtons().get(i).addKeyListener(this);
			p.getSecondButtons().get(i).setText(g.getSecondPlayer().getTeam().get(i).getName());
		}
		
		p.getPlayer1().setText("Player 1: "+ selection.getPlayer1().getText() );
		p.getPlayer2().setText("Player 2: " + selection.getPlayer2().getText());
		
		String s="";
		if(game.isFirstLeaderAbilityUsed())
			s+= p.getDisplay1().getText()+"true" ;
		else
			s+=  p.getDisplay1().getText()+"false" ;
		p.getDisplay1().setText(s);
		
		String r="";
		if(game.isSecondLeaderAbilityUsed())
			r+= p.getDisplay2().getText()+"true" ;
		else
			r+=  p.getDisplay2().getText()+"false" ;
		p.getDisplay2().setText(r);

		
		
		p.getEndTurn().addActionListener(this);
		p.getUseLeaderAbility().addActionListener(this);
		p.getAbilities1().addActionListener(this);
		p.getAbilities2().addActionListener(this);
		p.getEffects1().addActionListener(this);
		p.getEffects2().addActionListener(this);
		p.revalidate();
		p.repaint();
		
	}
	
	public void actionPerformed(ActionEvent e)  {

		JButton b = (JButton) e.getSource();

		if (p1 == true) {
			// the Entrance Scene
			if (b.getActionCommand().equals("Play")) {
				start.remove(start.getBack());
				p1 = false;

				try {
					selection = new SelectionView(game.getAvailableChampions());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println(123);
				
				clip.close();
				playSound("Sounds/Assemble.wav");
				selection.getStart().addActionListener(this);
				selection.getSetLeader().addActionListener(this);
				selection.getAddToTeam().addActionListener(this);

				buttons = selection.getButtons();
				for (JButton x : buttons) {
					x.addActionListener(this);
				}
				start.add(selection);
				start.repaint();
				start.revalidate();
				selection.repaint();
				selection.revalidate();
				start.setFocusable(true);
				p1=false;
				p2 = true;

			}

			if (b.getActionCommand().equals("Controls")) {
				start.remove(start.getBack());
				start.add(controls);
				controls.getBack().addActionListener(this);
				p4=true;
				start.repaint();
				start.revalidate();
			}

		}
		
		if(p4==true)
		{
			System.out.println(987);
			if(b.getActionCommand().equals("Back"))
			{
				start.remove(controls);
				start.add(start.getBack());
				start.repaint();
				start.revalidate();
				p4=false;
				clip.close();
				playSound("Sounds/Marvel Intro.wav");
			}
		}
		// the Start Page.
		if (p2 == true) {
			if (b.getActionCommand().equals("Add To Team") && Selected != null) {
				int x = buttons.indexOf(Selected);
				if (counter % 2 == 0 && firstPlayerTeam.size() <= 3 && addCounter < 2 && Selected != null) {
					Champion c = game.getAvailableChampions().get(x);
					firstPlayerTeam.add(c);
					addCounter++;
					Selected.setEnabled(false);
					Selected = null;
					counter++;
					selection.getLabel4().setText("Player 2, Please Choose A Champion.");

				} else {
					if (secondPlayerTeam.size() <= 3 && addCounter2 < 2) {
						Champion c = game.getAvailableChampions().get(x);
						secondPlayerTeam.add(c);
						addCounter2++;
						Selected.setEnabled(false);
						Selected = null;
						counter++;
						selection.getLabel4().setText("Player 1, Please Choose A Champion.");

					} else {
						if (counter % 2 == 0 && firstPlayerTeam.size() == 3)
						{
							new Warning("Teams Are Already Full");
							
						}
						else {
							new Warning("Please Set Your Leader");
						}

					}
				}

				
				selection.repaint();
				selection.revalidate();
				start.setFocusable(true);
			} else {
				if (!b.getActionCommand().equals("Add To Team") && !b.getActionCommand().equals("Set Leader")) {

					if (buttons.indexOf(b) != -1) {
						Selected = b;
						int x = buttons.indexOf(Selected);
						Champion c = game.getAvailableChampions().get(x);
						selection.getDetails().setText(c.toString());
					}

				} else {
					if (b.getActionCommand().equals("Set Leader") && Selected != null) {
						int x = buttons.indexOf(Selected);
						if (counter % 2 == 0 && firstLeader == false) {
							firstLeader = true;
							Champion c = game.getAvailableChampions().get(x);
							Leader1 = c;
							firstPlayerTeam.add(c);
							Selected.setEnabled(false);
							counter++;
							Selected = null;
							selection.getLabel4().setText("Player 2, Please Choose A Champion.");
						} else {
							if (counter % 2 == 1 && secondLeader == false) {
								secondLeader = true;
								Champion c = game.getAvailableChampions().get(x);
								Leader2 = c;
								secondPlayerTeam.add(c);
								buttons.get(x).setEnabled(false);
								counter++;
								Selected = null;
								selection.getLabel4().setText("Player 1, Please Choose A Champion.");
							} else {
								if (counter % 2 == 0 && firstLeader == true) {
									new Warning("First Player Leader Already Set");
								} else {
									if (counter % 2 == 1 && secondLeader == true)
										new Warning("Second Player Leader Already Set");
								}
							}
						}

					}

				}
				selection.repaint();
				selection.revalidate();
				start.setFocusable(true);
			}
			if(firstPlayerTeam.size()==3 && secondPlayerTeam.size()==3)
			{
				if(!selection.getPlayer1().getText().equals("") && ! selection.getPlayer2().getText().equals(""))
				{
					selection.getLabel3().setText(null);
					selection.getLabel4().setText("                     You May Start.");
					selection.getAddToTeam().setEnabled(false);
					selection.getSetLeader().setEnabled(false);
				}
				else{
					selection.getLabel3().setText(null);
					selection.getLabel4().setText("                 Please Enter Your Names.");
					selection.getAddToTeam().setEnabled(false);
					selection.getSetLeader().setEnabled(false);
				}
			}
			
			

			// Selection Ends

			if (b.getActionCommand().equals("Start")) {

				if (!selection.getPlayer1().getText().equals("") && !selection.getPlayer2().getText().equals("")
						&& firstPlayerTeam.size() == 3 && secondPlayerTeam.size() == 3) {
					Player first = new Player(selection.getPlayer1().getText());
					Player second = new Player(selection.getPlayer2().getText());
					for (Champion c : firstPlayerTeam) {
						first.getTeam().add(c);
					}
					for (Champion c : secondPlayerTeam) {
						second.getTeam().add(c);
					}
					first.setLeader(Leader1);
					second.setLeader(Leader2);
					try {
						game = new Game(first, second);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					start.remove(selection);
					
					clip.close();
					playSound("Sounds/AvengersTheme.wav");
					p2 = false;
					play = new GameView(this,this);
					play.getBack().addKeyListener(this);
					play.getLeft().addKeyListener(this);
					play.getRight().addKeyListener(this);
					play.getBoard().addKeyListener(this);
					play.getPlayer1().addKeyListener(this);
					play.getPlayer2().addKeyListener(this);
					play.getAbilities1().addKeyListener(this);
					play.getAbilities2().addKeyListener(this);
					play.getEffects1().addKeyListener(this);
					play.getEffects2().addKeyListener(this);
					play.getEndTurn().addKeyListener(this);
					play.getDisplay1().addKeyListener(this);
					play.getDisplay2().addKeyListener(this);
					play.getMyChampion().addKeyListener(this);
					play.getUseLeaderAbility().addKeyListener(this);
					play.getCurrentInfo().addKeyListener(this);
					play.getAbilitiesCast().addKeyListener(this);
					play.getBoardPanel().addKeyListener(this);
					start.setFocusable(true);
					setGame(game, play);
					start.add(play);
					System.out.println("bhbk");
					p3 = true;
					play.repaint();
					play.revalidate();
					start.repaint();
					start.revalidate();

				} else {
					if (selection.getPlayer1().getText().equals("") || selection.getPlayer2().getText().equals("")) {

						new Warning("Please Enter Your Names");
					} else {
						if (!(firstPlayerTeam.size() == 3 && secondPlayerTeam.size() == 3)) {
							new Warning("Please Choose 3 Champions");
						}
					}

				}

			}
			//Start page ends
		}
		// Game Begins
		if (p3 = true) {
			int x = -1;
			int y = -1;

			switch (b.getActionCommand()) {
			case ("11"):
				x = 0;
				break;
			case ("12"):
				x = 1;
				break;
			case ("13"):
				x = 2;
				break;
			case ("21"):
				x = 3;
				break;
			case ("22"):
				x = 4;
				break;
			case ("23"):
				x = 5;
				break;

			}

			if (x != -1) {
				if (x < 3) {
					Champion c = game.getFirstPlayer().getTeam().get(x);
					String s = c.ToString();

					if (game.isFirstLeaderAbilityUsed())
						s += "\n" + "Leader Ability Used: true";
					else
						s += "\n" + "Leader Ability Used: false";
					play.getDisplay1().setText(s);
					play.getDisplay1().setEditable(false);
					play.getAbilities1().setEnabled(true);
					play.getEffects1().setEnabled(true);
					play.getEffects1().setActionCommand("e" + x);
					play.getAbilities1().setActionCommand("a" + x);

				} else {
					if (x >= 3) {
						Champion c = game.getSecondPlayer().getTeam().get(x - 3);
						String s = c.ToString();

						if (game.isSecondLeaderAbilityUsed())
							s += "\n" + "Leader Ability Used: true";
						else
							s += "\n" + "Leader Ability Used: false";
						play.getDisplay2().setText(s);
						play.getDisplay2().setEditable(false);
						play.getAbilities2().setEnabled(true);
						play.getEffects2().setEnabled(true);
						play.getEffects2().setActionCommand("e" + x);
						play.getAbilities2().setActionCommand("a" + x);

					}
				}
				x = -1;
				play.revalidate();
				play.repaint();
			}

			switch (b.getActionCommand()) {
			case ("a0"):
				y = 0;
				;
				break;
			case ("a1"):
				y = 1;
				;
				break;
			case ("a2"):
				y = 2;
				;
				break;
			case ("a3"):
				y = 3;
				;
				break;
			case ("a4"):
				y = 4;
				;
				break;
			case ("a5"):
				y = 5;
				;
				break;
			}
			if (y != -1) {
				if (y < 3) {
					Champion c = game.getFirstPlayer().getTeam().get(y);
					display = new ChampionView(c.getName());
					display.getDisplay().setText(c.TheAbilities());
					display.repaint();
					display.revalidate();
					start.repaint();
					start.revalidate();

				} else {

					Champion c = game.getSecondPlayer().getTeam().get(y - 3);
					display = new ChampionView(c.getName());
					display.getDisplay().setText(c.TheAbilities());
					display.repaint();
					display.revalidate();
					start.repaint();
					start.revalidate();

				}
				play.revalidate();
				play.repaint();
			}

			y = -1;
			switch (b.getActionCommand()) {
			case ("e0"):
				y = 0;
				;
				break;
			case ("e1"):
				y = 1;
				;
				break;
			case ("e2"):
				y = 2;
				;
				break;
			case ("e3"):
				y = 3;
				;
				break;
			case ("e4"):
				y = 4;
				;
				break;
			case ("e5"):
				y = 5;
				;
				break;
			}
			if (y != -1) {

				if (y < 3) {
					Champion c = game.getFirstPlayer().getTeam().get(y);
					display = new ChampionView(c.getName());
					System.out.println(c.getName());
					display.getDisplay().setText(c.TheEffects());
					display.repaint();
					display.revalidate();
					start.repaint();
					start.revalidate();
				} else {

					Champion c = game.getSecondPlayer().getTeam().get(y - 3);
					display = new ChampionView(c.getName());
					System.out.println(c.getName());
					display.getDisplay().setText(c.TheEffects());
					display.repaint();
					display.revalidate();
					start.repaint();
					start.revalidate();

				}
				y = -1;
				play.revalidate();
				play.repaint();
			}

			if (b.getActionCommand().equals("End Turn")) {
				game.endTurn();
				resetGame(game, play);
			}
			if (b.getActionCommand().equals("Use Leader Ability")) {
				try {
					game.useLeaderAbility();
				} catch (LeaderAbilityAlreadyUsedException e1) {
					new Warning(e1.getMessage());
					e1.printStackTrace();
				} catch (LeaderNotCurrentException e1) {
					new Warning(e1.getMessage());
					e1.printStackTrace();
				}
				resetGame(game, play);

			}
			
			switch(b.getActionCommand())
			{
				case "0":
					a= game.getCurrentChampion().getAbilities().get(0);break;
				case "1":
					a= game.getCurrentChampion().getAbilities().get(1);break;
				case "2":
					a= game.getCurrentChampion().getAbilities().get(2);break;
				case "3":
					a= game.getCurrentChampion().getAbilities().get(3);break;
			}
			
			if(a!=null)
			{
				int k=0;
				if (SA == true) {
					for (int i = 4; i >= 0; i--)
					{
						for(int j=4 ; j>=0 ; j--)
						{
							if(play.getBoardButtons().get(k).equals(b))
							{
								try {
									game.castAbility(a, i, j);
								} catch (AbilityUseException e1) {
									new Warning(e1.getMessage());
									e1.printStackTrace();
								} catch (NotEnoughResourcesException e1) {
									new Warning(e1.getMessage());
									e1.printStackTrace();
								} catch (InvalidTargetException e1) {
									new Warning(e1.getMessage());
									e1.printStackTrace();
								} catch (CloneNotSupportedException e1) {
									new Warning(e1.getMessage());
									e1.printStackTrace();
								}
								resetGame(game, play);SA=false;a=null;
							}
							k++;
						}
					}
				}
				if(!a.getCastArea().equals(AreaOfEffect.DIRECTIONAL) && 
						!a.getCastArea().equals(AreaOfEffect.SINGLETARGET))
				{
					try {
						game.castAbility(a);
					} catch (AbilityUseException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					} catch (NotEnoughResourcesException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					}
					resetGame(game,play); 
					a=null;
				}
				else{
					if(a.getCastArea().equals(AreaOfEffect.DIRECTIONAL))
					{
						DA=true;
					}
					else{
						SA=true;
					}
				}
			}
			if(game.checkGameOver()!= null)
			{
				start.remove(play);
				if(game.checkGameOver().equals(game.getFirstPlayer()))
				{
					end.getWinner().setText(game.getFirstPlayer().getName() + " Wins.");
				}else{
					end.getWinner().setText(game.getSecondPlayer().getName() + " Wins.");
				}
				start.add(end);
				start.repaint();
				start.revalidate();
				
				
				Timer timer= new Timer();
				
				TimerTask t= new TimerTask()
				{

					@Override
					public void run() {
						clip.close();
						end.remove(end.getBack());
						end.add(end.getEndgame());
						playSound("Sounds/snap2.wav");
						end.repaint();
						end.revalidate();
						start.repaint();
						start.revalidate();
						start.repaint();
						start.revalidate();
					}
					
					
					
					
				};
				timer.schedule(t, 10000); 
				
				System.out.println(789);
				

				Timer tend=new Timer();
				TimerTask endGame= new TimerTask(){

					@Override
					public void run() {
						System.out.println("bla2");
						
						start.dispatchEvent(new WindowEvent(start, WindowEvent.WINDOW_CLOSING));
						start.repaint();
						start.revalidate();
					}
					
				};
				tend.schedule(endGame, 11900);
				
				start.repaint();
				start.revalidate();
				
				end.repaint();
				end.revalidate();
				
			}
		}
	}
	
	
	
	public ArrayList<Champion> getFirstPlayerTeam() {
		return firstPlayerTeam;
	}


	public ArrayList<Champion> getSecondPlayerTeam() {
		return secondPlayerTeam;
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int x= e.getKeyCode();
		System.out.println(x);
		
		if (p3 == true) {

			switch (x) {

			case 38:

				try {
					game.move(Direction.UP);
				} catch (UnallowedMovementException e1) {
					new Warning(e1.getMessage());

				} catch (NotEnoughResourcesException e1) {
					new Warning(e1.getMessage());

				}
				resetGame(game, play);
				a = null;
				DA = false;
				break;

			case 40:

				try {
					game.move(Direction.DOWN);
				} catch (UnallowedMovementException e1) {
					new Warning(e1.getMessage());

				} catch (NotEnoughResourcesException e1) {
					new Warning(e1.getMessage());

				}
				resetGame(game, play);
				a = null;
				DA = false;
				break;

			case 37:

				try {
					game.move(Direction.RIGHT);
				} catch (UnallowedMovementException e1) {
					new Warning(e1.getMessage());

				} catch (NotEnoughResourcesException e1) {
					new Warning(e1.getMessage());

				}
				resetGame(game, play);
				a = null;
				DA = false;
				break;

			case 39:

				try {
					game.move(Direction.LEFT);
				} catch (UnallowedMovementException e1) {
					new Warning(e1.getMessage());

				} catch (NotEnoughResourcesException e1) {
					new Warning(e1.getMessage());

				}
				resetGame(game, play);
				a = null;
				DA = false;
				break;

			case 87:
				if (DA == true) {
					try {
						game.castAbility(a, Direction.UP);
					} catch (AbilityUseException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					} catch (NotEnoughResourcesException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					}
					resetGame(game, play);
					a = null;
					DA = false;
					break;
				} else {
					try {
						game.attack(Direction.UP);
					} catch (ChampionDisarmedException e1) {
						new Warning(e1.getMessage());

					} catch (NotEnoughResourcesException e1) {
						new Warning(e1.getMessage());

					} catch (InvalidTargetException e1) {
						new Warning(e1.getMessage());

					}
					resetGame(game, play);
					break;
				}
			case 83:
				if (DA == true) {
					try {
						game.castAbility(a, Direction.DOWN);
					} catch (AbilityUseException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					} catch (NotEnoughResourcesException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					}
					resetGame(game, play);
					a = null;
					DA = false;
					break;
				} else {
					try {
						game.attack(Direction.DOWN);
					
					} catch (ChampionDisarmedException e1) {
						new Warning(e1.getMessage());

					} catch (NotEnoughResourcesException e1) {
						new Warning(e1.getMessage());

					} catch (InvalidTargetException e1) {
						new Warning(e1.getMessage());

					}
					resetGame(game, play);
					break;
				}
			case 65:
				if (DA == true) {
					try {
						game.castAbility(a, Direction.RIGHT);
						
					} catch (AbilityUseException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					} catch (NotEnoughResourcesException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					}
					resetGame(game, play);
					a = null;
					DA = false;
					break;
				} else {
					try {
						game.attack(Direction.RIGHT);
						
					} catch (ChampionDisarmedException e1) {
						new Warning(e1.getMessage());

					} catch (NotEnoughResourcesException e1) {
						new Warning(e1.getMessage());

					} catch (InvalidTargetException e1) {
						new Warning(e1.getMessage());

					}
					resetGame(game, play);
					break;
				}
			case 68:
				if (DA == true) {
					try {
						game.castAbility(a, Direction.LEFT);
					} catch (AbilityUseException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					} catch (NotEnoughResourcesException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						new Warning(e1.getMessage());
						e1.printStackTrace();
					}
					resetGame(game, play);
					a = null;
					DA = false;
					break;
				} else {
					try {
						game.attack(Direction.LEFT);
					} catch (ChampionDisarmedException e1) {
						new Warning(e1.getMessage());

					} catch (NotEnoughResourcesException e1) {
						new Warning(e1.getMessage());

					} catch (InvalidTargetException e1) {
						new Warning(e1.getMessage());

					}
					resetGame(game, play);
					break;
				}
			case 10: game.endTurn(); resetGame(game ,play);break;
			}
			
			if(game.checkGameOver()!= null)
			{
				start.remove(play);
				if(game.checkGameOver().equals(game.getFirstPlayer()))
				{
					end.getWinner().setText(game.getFirstPlayer().getName() + " Wins.");
				}else{
					end.getWinner().setText(game.getSecondPlayer().getName() + " Wins.");
				}
				start.add(end);
				start.repaint();
				start.revalidate();
				
				
				Timer timer= new Timer();
				
				TimerTask t= new TimerTask()
				{

					@Override
					public void run() {
						clip.close();
						end.remove(end.getBack());
						end.add(end.getEndgame());
						playSound("Sounds/snap2.wav");
						end.repaint();
						end.revalidate();
						start.repaint();
						start.revalidate();
						start.repaint();
						start.revalidate();
					}
					
					
					
					
				};
				timer.schedule(t, 10000); 
				
				System.out.println(789);
				

				Timer tend=new Timer();
				TimerTask endGame= new TimerTask(){

					@Override
					public void run() {
						System.out.println("bla2");
						
						start.dispatchEvent(new WindowEvent(start, WindowEvent.WINDOW_CLOSING));
						start.repaint();
						start.revalidate();
					}
					
				};
				tend.schedule(endGame, 12000);
				
				start.repaint();
				start.revalidate();
				
				end.repaint();
				end.revalidate();
				
			}
		}
	}
	public static void playInstance(String filepath){
		try {
			AudioInputStream sound= AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
			clip2= AudioSystem.getClip();
			clip2.open(sound);
			clip2.start();
		} catch (UnsupportedAudioFileException | IOException e1) {
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}
	public static void playSound(String filepath){
		try {
			audio= AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
			clip= AudioSystem.getClip();
			clip.open(audio);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException | IOException e1) {
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public static Clip getClip() {
		return clip;
	}
	public static void setClip(Clip clip) {
		Controller.clip = clip;
	}
	public static void main(String[] args) throws IOException {
		Controller c= new Controller();
		
	}

}
