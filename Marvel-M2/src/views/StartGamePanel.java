package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import engine.Game;
import engine.PriorityQueue;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.AreaOfEffect;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;
import model.world.Hero;

public class StartGamePanel extends JPanel implements ActionListener, MouseListener, ItemListener{
	private Control control;
	private JLabel firstPlayerName;
	private JLabel secondPlayerName;
	private JButton MoveUp;
	private JButton MoveDown;
	private JButton MoveLeft;
	private JButton MoveRight;
	private JButton AttackUp;
	private JButton AttackDown;
	private JButton AttackRight;
	private JButton AttackLeft;
	private JButton[][] boardPanel;
	private JLabel isLeaderAbilityUsedfirstPlayer;
	private JLabel isLeaderAbilityUsedsecondPlayer;
	private JButton useLeaderAbiltyfirstPlayer;
	private JButton useLeaderAbilitysecondPlayer; 
	private JButton CastAbilityUp;
	private JButton CastAbilityDown;
	private JButton CastAbilityRight;
	private JButton CastAbilityLeft;
	private JButton CastAbility;
	private JTextField CAX;
	private JTextField CAY;
	private JComboBox currentChampionsEffects;
	private JTextField currentChampionName;
	private JTextField currentChampionType;
	private JTextField currentChampionHP;
	private JTextField currentChampionMana;
	private JTextField currentChampionAP;
	private JTextArea coolDown;
	private Game game;
	private JPanel boardPanelview;
	private JPanel actionKeysPanel;
	private JPanel currentChampionInfo;
	private JComboBox appliedeff;
	private JComboBox appliedabi;
	private JButton endTurn;
	private JTextArea effectOrAbilityInfo;
	private JList championstoplaynextinorder;
	private JTextField currentChampionSpeed;

	public StartGamePanel(Control control, Game game) {
		this.control = control;
		this.game = game;
		this.setLayout(null);
		
		actionKeysPanel = new JPanel();
		actionKeysPanel.setLayout(null);
		firstPlayerName = new JLabel(game.getFirstPlayer().getName());
		secondPlayerName = new JLabel(game.getSecondPlayer().getName());
		firstPlayerName.setBounds(50, 10, 120, 30);
		secondPlayerName.setBounds(250, 10, 120, 30);
		isLeaderAbilityUsedfirstPlayer = new JLabel();
		isLeaderAbilityUsedsecondPlayer=new JLabel();
		isLeaderAbilityUsedfirstPlayer.setBounds(10, 410, 280, 30);
		isLeaderAbilityUsedsecondPlayer.setBounds(10, 440, 280, 30);
		useLeaderAbiltyfirstPlayer = new JButton("Use Leader Ability");
		/* useLeaderAbilitysecondPlayer = new JButton("Use Leader Ability"); */
		useLeaderAbiltyfirstPlayer.setBounds(40, 470, 140, 30);
		/* useLeaderAbilitysecondPlayer.setBounds(40,500,140,30); */
		MoveUp = new JButton("Move UP");
		MoveDown = new JButton("Move Down");
		MoveLeft = new JButton("Move Left");
		MoveRight = new JButton("Move Right");
		AttackUp = new JButton("Attack Up");
		AttackDown = new JButton("Attack Down");
		AttackRight = new JButton("Attack Right");
		AttackLeft = new JButton("Attack Left");
		MoveUp.setBounds(70, 60, 120, 30  );
		MoveDown.setBounds(70, 160, 120, 30);
		MoveLeft.setBounds(10, 110, 120, 30);
		MoveRight.setBounds(130, 110, 120, 30);
		AttackUp.setBounds(350, 60, 120, 30 );
		AttackDown.setBounds(350,160,120,30);
		AttackLeft.setBounds(300, 110, 120, 30);
		AttackRight.setBounds(420, 110, 120, 30);
		MoveUp.addActionListener(this);
		MoveDown.addActionListener(this);
		MoveLeft.addActionListener(this);
		MoveRight.addActionListener(this);
		AttackUp.addActionListener(this);
		AttackDown.addActionListener(this);
		AttackLeft.addActionListener(this);
		AttackRight.addActionListener(this);
		
		actionKeysPanel.add(firstPlayerName);
		actionKeysPanel.add(secondPlayerName);
		actionKeysPanel.add(MoveUp);
		actionKeysPanel.add(MoveDown);
		actionKeysPanel.add(MoveLeft);
		actionKeysPanel.add(MoveRight);
		actionKeysPanel.add(AttackUp);
		actionKeysPanel.add(AttackDown);
		actionKeysPanel.add(AttackRight);
		actionKeysPanel.add(AttackLeft);
		boardPanelview = new JPanel();
		boardPanelview.setLayout(new GridLayout(5, 5));
		boardPanel = new JButton[5][5];
		for (int i = 4; i >= 0; i--) {
			for (int j = 0; j < 5; j++) {
				boardPanel[i][j] = new JButton();
				boardPanel[i][j].addMouseListener(this);
				boardPanelview.add(boardPanel[i][j]);
			}
		}

		 

		coolDown = new JTextArea();
		coolDown.setBounds(280, 530, 150, 25);
		
		currentChampionName = new JTextField();
		currentChampionType = new JTextField();
		
		currentChampionHP = new JTextField();
		currentChampionHP.setBounds(280, 410, 230,25);
		currentChampionMana = new JTextField(); 
		currentChampionMana.setBounds(280, 440, 220, 25);
		currentChampionAP = new JTextField();
		currentChampionAP.setBounds(280,470, 220, 25);

		/*
		 * currentChampionHP.setBackground(Color.GREEN);
		 * currentChampionMana.setBackground(Color.GREEN);
		 * currentChampionAP.setBackground(Color.GREEN);
		 * coolDown.setBackground(Color.blue);
		 */
		
		appliedeff = new JComboBox();
		appliedeff.addItemListener(this);
		appliedabi = new JComboBox();
		appliedabi.addItemListener(this);

		actionKeysPanel.add(appliedeff);
		actionKeysPanel.add(appliedabi);
		actionKeysPanel.add(currentChampionHP);
		actionKeysPanel.add(currentChampionMana);
		actionKeysPanel.add(currentChampionAP);
		actionKeysPanel.add(isLeaderAbilityUsedfirstPlayer);
		actionKeysPanel.add(isLeaderAbilityUsedsecondPlayer);
		actionKeysPanel.add(currentChampionName);
		actionKeysPanel.add(currentChampionType);
		actionKeysPanel.add(coolDown);
		CastAbilityUp = new JButton("Cast Ability UP");
		CastAbilityDown = new JButton ("Cast Ability Down");
		CastAbilityLeft = new JButton ("Cast Ability Left");
		CastAbilityRight = new JButton ("Cast Ability Right");
		CastAbility = new JButton ("Cast Ability");
		CAX = new JTextField("X-coor.");
		CAY = new JTextField("Y-coor.");
		CastAbilityUp.setBounds(200,210,140,30);
		CastAbilityDown.setBounds( 200, 310, 120, 30);
		CastAbilityLeft.setBounds(130, 260,130 , 30);
		CastAbilityRight.setBounds(260, 260, 130, 30);
		CAX.setBounds(150, 360, 50, 30);
		CAY.setBounds(200, 360, 50, 30);
		CastAbility.setBounds(260, 360, 120, 30);
		CastAbilityUp.addActionListener(this);
		CastAbilityDown.addActionListener(this);
		CastAbilityRight.addActionListener(this);
		CastAbilityLeft.addActionListener(this);
		CastAbility.addActionListener(this);
		actionKeysPanel.add(CastAbilityUp);
		actionKeysPanel.add(CastAbilityDown);
		actionKeysPanel.add(CastAbilityRight);
		actionKeysPanel.add(CastAbilityLeft);
		actionKeysPanel.add(CastAbility);
		actionKeysPanel.add(CAX);
		actionKeysPanel.add(CAY);
		endTurn = new JButton("End Turn");
		endTurn.setBounds(170, 617, 200, 30);
		
		actionKeysPanel.add(endTurn);
		endTurn.addActionListener(this);
		effectOrAbilityInfo = new JTextArea();
		effectOrAbilityInfo.setBounds(490,230,170,170);
		actionKeysPanel.add(effectOrAbilityInfo);
		effectOrAbilityInfo.setEditable(false);
		
		 
		 currentChampionSpeed = new JTextField();
		 currentChampionSpeed.setBounds(280, 500, 220, 25);
		 actionKeysPanel.add(currentChampionSpeed);
		 
		useLeaderAbiltyfirstPlayer.addActionListener(this);
		/* useLeaderAbilitysecondPlayer.addActionListener(this); */ 

		actionKeysPanel.add(useLeaderAbiltyfirstPlayer);
		/* actionKeysPanel.add(useLeaderAbilitysecondPlayer); */
		
		championstoplaynextinorder = new JList();
		 actionKeysPanel.add(championstoplaynextinorder);
		
		boardPanelview.setBounds(10, 10, 500, 500);
		this.add(boardPanelview);
		actionKeysPanel.setBounds(520, 10, 1000, 1000);
		this.add(actionKeysPanel);
		fillingTheBoard();
		getCurrentChampionInformation();
	}
	public void getCurrentChampionInformation() {
		/*
		 * appliedeff = new JComboBox(); appliedabi = new JComboBox();
		 */
		appliedabi.removeItemListener(this);
		actionKeysPanel.remove(appliedabi);
		appliedabi = new JComboBox();
		appliedabi.setBounds(520, 410, 130, 25);
		actionKeysPanel.add(appliedabi);
		appliedabi.addItemListener(this);
		appliedeff.removeItemListener(this);
		actionKeysPanel.remove(appliedeff);
		appliedeff = new JComboBox();
		appliedeff.setBounds(520, 440, 130, 25);
		actionKeysPanel.add(appliedeff);
		appliedeff.addItemListener(this);
		PriorityQueue temp = new PriorityQueue(game.getTurnOrder().size());
		String [] tempNames = new String[game.getTurnOrder().size()];
		int i=0;
		while(!game.getTurnOrder().isEmpty()&& i<tempNames.length) {
			temp.insert(game.getTurnOrder().peekMin());
			tempNames[i]=((Champion) game.getTurnOrder().peekMin()).getName();
			game.getTurnOrder().remove();
			i++;
		}
		actionKeysPanel.remove(championstoplaynextinorder);
		championstoplaynextinorder = new JList(tempNames);
		championstoplaynextinorder.setBounds(520, 470, 140, 120);
		actionKeysPanel.add(championstoplaynextinorder);
		while(!temp.isEmpty()) {
			game.getTurnOrder().insert(temp.remove());
		}
		/*
		 * currentChampionHP = new JProgressBar(0,game.getCurrentChampion().getMaxHP());
		 * currentChampionMana = new
		 * JProgressBar(0,game.getCurrentChampion().getMana()); currentChampionAP = new
		 * JProgressBar(0,game.getCurrentChampion().getMaxActionPointsPerTurn());
		 */
		//appliedeff.removeAll();
		//appliedabi.removeAll();
		/*
		 * currentChampionHP.setValue(game.getCurrentChampion().getCurrentHP());
		 * currentChampionMana.setValue(game.getCurrentChampion().getMana());
		 * currentChampionAP.setValue(game.getCurrentChampion().getCurrentActionPoints()
		 * );
		 */
		currentChampionHP.setText("Current Champion's Health Points: "+game.getCurrentChampion().getCurrentHP());
		currentChampionAP.setText("Current Champion's Action Points: "+game.getCurrentChampion().getCurrentActionPoints());
		currentChampionMana.setText("Current Champion's Mana: "+game.getCurrentChampion().getMana());
		currentChampionSpeed.setText("Current Champion's Speed: "+game.getCurrentChampion().getSpeed());
		currentChampionSpeed.setEditable(false);
		currentChampionMana.setEditable(false);
		currentChampionAP.setEditable(false);
		currentChampionHP.setEditable(false);
		currentChampionName.setEditable(false);
		currentChampionType.setEditable(false);
		
		String firstleaderused="";
		String secondleaderused="";
		if(game.isFirstLeaderAbilityUsed()==true) {
			firstleaderused="Yes";
		}
		else {
			firstleaderused="No";
		}
		if(game.isSecondLeaderAbilityUsed()==true) {
			secondleaderused="Yes";
		}
		else {
			secondleaderused="No";
		}
		isLeaderAbilityUsedfirstPlayer.setText(game.getFirstPlayer().getName()+" Leader Ability used: "+firstleaderused);
		isLeaderAbilityUsedsecondPlayer.setText(game.getSecondPlayer().getName()+" Leader Ability used: "+secondleaderused);
		
		//String[] effects = new String [game.getCurrentChampion().getAppliedEffects().size()];
		for( i=0; i<game.getCurrentChampion().getAppliedEffects().size();i++) {
			appliedeff.addItem(game.getCurrentChampion().getAppliedEffects().get(i).getName());
		}
		
		//String [] abilitiess = new String[game.getCurrentChampion().getAbilities().size()];		
		for(i=0; i<game.getCurrentChampion().getAbilities().size();i++) {
			appliedabi.addItem((game.getCurrentChampion().getAbilities().get(i).getName()));
		}
		currentChampionName.setText("Current Champion's Name: " + game.getCurrentChampion().getName());
		if(game.getCurrentChampion() instanceof Hero)
		currentChampionType.setText("Current Champion's Type : Hero");
		else if (game.getCurrentChampion() instanceof AntiHero)
		currentChampionType.setText("Current Champion's Type : AntiHero");
		else
		currentChampionType.setText("Current Champion's Type : Villain");
		currentChampionName.setBounds(280, 580, 230,25);
		currentChampionType.setBounds(280, 550, 230,25);

		this.validate();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == useLeaderAbiltyfirstPlayer/* ||e.getSource()==useLeaderAbilitysecondPlayer */) {
			try {
				game.useLeaderAbility();
				/*
				 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
				 */
				/*
				 * for(int i=0;i<game.getCurrentChampion().getAppliedEffects().size();i++) {
				 * System.out.println(game.getCurrentChampion().getAppliedEffects().get(i).
				 * getName()); }
				 */
				fillingTheBoard();
				getCurrentChampionInformation();
			} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Take Care!", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(e.getSource()== MoveUp) {
				try {
					game.move(Direction.UP);
					/*
					 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
					 */
					fillingTheBoard();
					getCurrentChampionInformation();
				} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Take Care!", JOptionPane.ERROR_MESSAGE);
				}
				}
		else if(e.getSource()==MoveDown) {
				try {
					game.move(Direction.DOWN);
					/*
					 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
					 */
					fillingTheBoard();
					getCurrentChampionInformation();
				} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Take Care!", JOptionPane.ERROR_MESSAGE);
				}
				
		}
		
		else if(e.getSource()==MoveLeft) {
			   try {
				game.move(Direction.LEFT);
				/*
				 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
				 */
				fillingTheBoard();
				getCurrentChampionInformation();
			} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Take Care!", JOptionPane.ERROR_MESSAGE);
			}
			   
		}
		
		else if(e.getSource()==MoveRight) {
			   try {
				game.move(Direction.RIGHT);
				/*
				 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
				 */
				 fillingTheBoard();
				 getCurrentChampionInformation();
			} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Take Care!", JOptionPane.ERROR_MESSAGE);
			}
			  
		}
		if(e.getSource()== AttackUp) {
			
				try {
					game.attack(Direction.UP);
					/*
					 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
					 */
					fillingTheBoard();
					getCurrentChampionInformation();
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Take Care!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
	else if(e.getSource()==AttackDown) {
			
				try {
					game.attack(Direction.DOWN);
					/*
					 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
					 */
					fillingTheBoard();
					getCurrentChampionInformation();
				} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Take Care!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
	
	
	else if(e.getSource()==AttackLeft) {
		  
			try {
				game.attack(Direction.LEFT);
				/*
				 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
				 */
				fillingTheBoard();
				getCurrentChampionInformation();
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Take Care!", JOptionPane.ERROR_MESSAGE);
			}
			
		} 
	
	else if(e.getSource()==AttackRight) {
		   
			try {
				game.attack(Direction.RIGHT);
				/*
				 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
				 */
				fillingTheBoard();
				getCurrentChampionInformation();
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Take Care!", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	if(e.getSource()==endTurn) {
		game.endTurn();
		/*
		 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
		 */
		fillingTheBoard();
		getCurrentChampionInformation();
	}
		
	if(e.getSource()==CastAbilityUp) {
		try {
			
			game.castAbility(game.getCurrentChampion().getAbilities().get(appliedabi.getSelectedIndex()),Direction.UP);
			/*
			 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
			 */
			fillingTheBoard();
			getCurrentChampionInformation();
		} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);		}
	}
	
	if(e.getSource()==CastAbilityDown) {
		try {
			game.castAbility(game.getCurrentChampion().getAbilities().get(appliedabi.getSelectedIndex()),Direction.DOWN);
			/*
			 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
			 */
			fillingTheBoard();
			getCurrentChampionInformation();
		} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);		}
	}
	if(e.getSource()==CastAbilityLeft) {
		try {
			game.castAbility(game.getCurrentChampion().getAbilities().get(appliedabi.getSelectedIndex()),Direction.LEFT);
			/*
			 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
			 */
			fillingTheBoard();
			getCurrentChampionInformation();
		} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);		}
	}
	if(e.getSource()==CastAbilityRight) {
		try {
			game.castAbility(game.getCurrentChampion().getAbilities().get(appliedabi.getSelectedIndex()),Direction.RIGHT);
			/*
			 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
			 */
			fillingTheBoard();
			getCurrentChampionInformation();
		} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);		}
	}
	if(e.getSource()==CastAbility) {
		if((game.getCurrentChampion().getAbilities().get(appliedabi.getSelectedIndex()).getCastArea()==AreaOfEffect.SINGLETARGET)){
			try {
				game.castAbility(game.getCurrentChampion().getAbilities().get(appliedabi.getSelectedIndex()),Integer.parseInt(CAX.getText()), Integer.parseInt(CAY.getText()));
				/*
				 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
				 */
				fillingTheBoard();
				getCurrentChampionInformation();
			} catch (NumberFormatException | NotEnoughResourcesException | AbilityUseException | InvalidTargetException
					| CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		else {
			try {
				game.castAbility(game.getCurrentChampion().getAbilities().get(appliedabi.getSelectedIndex()));
				/*
				 * appliedeff.removeAllItems(); appliedabi.removeAllItems();
				 */
				fillingTheBoard();
				getCurrentChampionInformation();
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
		if(game.checkGameOver()!=null) {
			JOptionPane.showMessageDialog(this, "Congratulations "+game.checkGameOver().getName() + " You WON !!!!!!", "GAMEOVER!", JOptionPane.INFORMATION_MESSAGE);
			control.dispose();
		}
	}
			
		
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for(int i=0;i<5;i++) {
			for(int j=0; j<5;j++) {
				if(game.getBoard()[i][j] instanceof Cover) {
					boardPanel[i][j].setToolTipText(((Cover) game.getBoard()[i][j]).getCurrentHP()+"");
				}
				else if (game.getBoard()[i][j] instanceof Champion) {
					boardPanel[i][j].setToolTipText(control.infoofanyChampion((Champion) game.getBoard()[i][j]));
				}
				else {
					boardPanel[i][j].setToolTipText("Empty");
				}
			}
		}
		this.validate();
		this.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void fillingTheBoard() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (game.getBoard()[i][j] instanceof Cover) {
					//boardPanel[i][j].setIcon(new ImageIcon("MarveltobeusedasCover.jpg"));
					boardPanel[i][j].setText("Cover");
					/*
					 * boardPanel[i][j].setText("COVER");
					 * boardPanel[i][j].setBackground(Color.pink);
					 */
				} else if (game.getBoard()[i][j] instanceof Champion) {

					/*
					 * if(((Champion) game.getBoard()[i][j]).getName().equals("Captain America")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("CaptainAmerica.jpg")); } else
					 * if(((Champion) game.getBoard()[i][j]).getName().equals("Deadpool")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("DeadPool.jpg")); } else
					 * if(((Champion) game.getBoard()[i][j]).getName().equals("Dr Strange")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("DrStrange.jpg")); } else
					 * if(((Champion) game.getBoard()[i][j]).getName().equals("Electro")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("Electro.jpg")); } else if(((Champion)
					 * game.getBoard()[i][j]).getName().equals("Ghost Rider")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("GhostRider.jpg")); } else
					 * if(((Champion) game.getBoard()[i][j]).getName().equals("Hela")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("Hela.jpg")); } else if(((Champion)
					 * game.getBoard()[i][j]).getName().equals("Hulk")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("Hulk.jpg")); } else if(((Champion)
					 * game.getBoard()[i][j]).getName().equals("Iceman")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("IceMan.jpg")); } else if(((Champion)
					 * game.getBoard()[i][j]).getName().equals("Ironman")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("IronMan.jpg")); } else if(((Champion)
					 * game.getBoard()[i][j]).getName().equals("Loki")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("Loki.jpg")); } else if(((Champion)
					 * game.getBoard()[i][j]).getName().equals("Quicksilver")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("QuickSilver.jpg")); } else
					 * if(((Champion) game.getBoard()[i][j]).getName().equals("Spiderman")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("SpiderMan.jpg")); } else
					 * if(((Champion) game.getBoard()[i][j]).getName().equals("Thor")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("Thor.jpg")); } else if(((Champion)
					 * game.getBoard()[i][j]).getName().equals("Venom")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("Venom.jpg")); } else if(((Champion)
					 * game.getBoard()[i][j]).getName().equals("Yellow Jacket")) {
					 * boardPanel[i][j].setIcon(new ImageIcon("YellowJacket.jpg")); }
					 */
					

					if (((Champion) game.getBoard()[i][j]).getName().equals("Captain America")) {
						boardPanel[i][j].setText("Captain America");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Deadpool")) {
						boardPanel[i][j].setText("Deadpool");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Dr Strange")) {
						boardPanel[i][j].setText("Dr Strange");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Electro")) {
						boardPanel[i][j].setText("ELECTRO");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Ghost Rider")) {
						boardPanel[i][j].setText("Ghost Rider");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Hela")) {
						boardPanel[i][j].setText("HELA");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Hulk")) {
						boardPanel[i][j].setText("HULK");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Iceman")) {
						boardPanel[i][j].setText("ICEMAN");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Ironman")) {
						boardPanel[i][j].setText("IRONMAN");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Loki")) {
						boardPanel[i][j].setText("Loki");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Quicksilver")) {
						boardPanel[i][j].setText("Quicksilver");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Spiderman")) {
						boardPanel[i][j].setText("Spiderman");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Thor")) {
						boardPanel[i][j].setText("THOR");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Venom")) {
						boardPanel[i][j].setText("VENOM");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					} else if (((Champion) game.getBoard()[i][j]).getName().equals("Yellow Jacket")) {
						boardPanel[i][j].setText("Yellow Jacket");
						if((Champion) game.getBoard()[i][j]!=game.getCurrentChampion()) {
						if(game.getFirstPlayer().getTeam().contains((Champion) game.getBoard()[i][j])) {
							boardPanel[i][j].setBackground(Color.darkGray);
						}
						else {
							boardPanel[i][j].setBackground(Color.BLUE);
						}
						}
					}
					if((Champion) game.getBoard()[i][j]==game.getCurrentChampion()) {
						boardPanel[i][j].setBackground(Color.GREEN);
					}

				} else {
					boardPanel[i][j].setBackground(Color.pink);
					boardPanel[i][j].setText("");
				}
			}
		}
		this.validate();
		this.repaint();
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
		  if(e.getSource()==appliedabi) {
		  effectOrAbilityInfo.setText(control.AblitiesinfotoString(game.getCurrentChampion().getAbilities().get(appliedabi.getSelectedIndex())));
		  coolDown.setText("Ability cool Down: "+ game.getCurrentChampion().getAbilities().get(appliedabi.getSelectedIndex()).getCurrentCooldown());}
		  if(e.getSource()==appliedeff) {
		  effectOrAbilityInfo.setText(control.EffectToString(game.getCurrentChampion().getAppliedEffects().get(appliedeff.getSelectedIndex()))); }
		 this.validate();
		 this.repaint();
	}
	
}
