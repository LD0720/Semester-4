package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import engine.Game;
import engine.Player;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Hero;

public class Control extends JFrame {
	private NamesPanel namesPanel;
	private ChampionsOfChoicePanel championsOfChoicePanel;
	private LeaderOfChoicePanel isleader; 
	private Game game;
	private Champion firstPlayerchoiceone;
	private Champion firstPlayerchoicetwo;
	private Champion firstPlayerchoiceThree;
	private Champion firstPlayerLeaderChoice;
	private Champion secondPlayerLeaderchoice;
	private Champion secondPlayerchoiceone;
	private Champion secondPlayerchoicetwo;
	private Champion secondPlayerchoicethree;
	private StartGamePanel StartGame;
	private String firstPlayername;
	private String secondPlayername;
	private Player firstPlayer;
	private Player secondPlayer;
	public Control(){
		namesPanel = new NamesPanel(this);
		this.getContentPane().add(namesPanel);
		this.setTitle("Marvel: Ultimate War");
		ImageIcon icon = new ImageIcon("marvel.jpg");
		this.setIconImage(icon.getImage());
		this.setSize(500,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void chooseChampionsPanel(String text, String text2) {
		try {
			firstPlayername = text;
			secondPlayername = text2;
			Game.loadAbilities("Abilities.csv");
			Game.loadChampions("Champions.csv");
			
			this.getContentPane().remove(namesPanel);
			this.setSize(1000,1000);
			championsOfChoicePanel=new ChampionsOfChoicePanel(this,text,text2);
			this.getContentPane().add(championsOfChoicePanel);
			this.validate();
			this.repaint();
			this.setVisible(true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void GamePanel(Champion leader1, Champion leader2) {
		firstPlayerLeaderChoice=leader1;
		secondPlayerLeaderchoice=leader2;
		
		firstPlayer = new Player(firstPlayername);
		firstPlayer.getTeam().add(firstPlayerchoiceone);
		firstPlayer.getTeam().add(firstPlayerchoicetwo);
		firstPlayer.getTeam().add(firstPlayerchoiceThree);
		firstPlayer.setLeader(firstPlayerLeaderChoice);
		
		secondPlayer = new Player(secondPlayername);
		secondPlayer.getTeam().add(secondPlayerchoiceone);
		secondPlayer.getTeam().add(secondPlayerchoicetwo);
		secondPlayer.getTeam().add(secondPlayerchoicethree);
		secondPlayer.setLeader(secondPlayerLeaderchoice);
		
		game=new Game(firstPlayer,secondPlayer);
		
		this.remove(isleader);
		StartGame = new StartGamePanel(this,game);
		this.getContentPane().add(StartGame);
		this.setSize(1200, 700);
		this.setVisible(true);
		this.validate();
		this.repaint();
		
	}
	public String ChampiontoString(int selectedChampionindex) {
		Champion c = Game.getAvailableChampions().get(selectedChampionindex);
		String type = "";
		if(c instanceof Hero)
			type="Hero";
		else if(c instanceof AntiHero)
			type="AntiHero";
		else
			type="Villain";
		String Data = "Name: " + c.getName()+"\n"+"Type: "+type+"\n"+"Attack: "+c.getAttackDamage()+"\n"+
		"Range: "+c.getAttackRange()+ "\n" +"Health: "+c.getCurrentHP() + "\n" + "Mana: "+
				c.getMana()+"\n" + "Action Points : " + c.getMaxActionPointsPerTurn()+"\n"+"Abilities: "+"\n" ;
		int i =1;
		for (Ability a :c.getAbilities() ) {
			Data = Data +i+"] "+AblitiesinfotoString(a)+"\n"+"......................"+"\n";
			i++;
		}
		
		return Data;
	}
	public void LeaderPanel(Champion champion, Champion champion2, Champion champion3, Champion champion4,
			Champion champion5, Champion champion6) {
		firstPlayerchoiceone=champion;
		firstPlayerchoicetwo=champion2;
		firstPlayerchoiceThree=champion3;
		secondPlayerchoiceone=champion4;
		secondPlayerchoicetwo=champion5;
		secondPlayerchoicethree=champion6;
		this.remove(championsOfChoicePanel);
		isleader = new LeaderOfChoicePanel(champion,champion2,champion3,champion4,champion5,champion6, this);
		this.getContentPane().add(isleader);
		this.validate();
		this.repaint();
	}
	public String AblitiesinfotoString(Ability a) {
		
		String info = "";
		info = "Name: "+a.getName()+"\n"+"Current Cool Down: "+a.getCurrentCooldown()+"\n"+"Base Cool Down : "+a.getBaseCooldown()+"\n"+"CastArea: "+a.getCastArea()
		+"\n"+"Cast Range: "+a.getCastRange()+"\n"+"Mana Cost: " + a.getManaCost()+"\n"+"Required Action Points: "+a.getRequiredActionPoints();
		if(a instanceof DamagingAbility)
			info = info + "\n"+"Damaging Amount: "+ ((DamagingAbility) a).getDamageAmount()+"\n"+"Type: Damaging Ability";
		else if (a instanceof HealingAbility)
			info = info +"\n"+ "Healing Amount: "+ ((HealingAbility) a).getHealAmount()+"\n"+"Type: Healing Ability";
		else {
			info = info +"\n" + "Effect: "+ ((CrowdControlAbility) a).getEffect().getName()+"\n"+"Duration : "+ ((CrowdControlAbility) a).getEffect().getDuration()+"\n"+"Type: Crowd Control Abiility";
		}
		return info;
	}
	public String infoofanyChampion(Champion c) {
		String info="";
		info = "<html>"+"Name: "+c.getName()+"<br>"+"Current Health Points: "+c.getCurrentHP()+"<br>"+"Mana: "+c.getMana()+"<br>"+
		"Speed: "+ c.getSpeed()+ "<br>"+"Max Action Points Per Turn: "+ c.getMaxActionPointsPerTurn()+"<br>"+ 
				"Attack Damage: "+ c.getAttackDamage()+"<br>" + "Attack Range: "+ c.getAttackRange()+ "<br>"+"Type: "+"<html>";
		if(c instanceof Hero) {
			info=info + "Hero";
		}
		else if (c instanceof AntiHero) {
			info=info+ "AntiHero";
		}
		else {
			info=info+"Villain";
		}
		
		
		if(game.getFirstPlayer().getTeam().contains(c)&&game.getFirstPlayer().getLeader()==c )
			info = info +"<html>"+"<br>"+"Leader: Yes"+"<html>";
		else if(game.getSecondPlayer().getTeam().contains(c)&&game.getSecondPlayer().getLeader()==c)
			info = info +"<html>"+"<br>"+"Leader: Yes"+"<html>";
		else 
			info = info+"<html>" + "<br>"+"Leader: No"+"<html>";
		
		
		info = info +"<html>" + "<br>"+ "Applied Effects: "+"<html>";
		if(c.getAppliedEffects().size()==0) {
			info = info + "<html>" + "<br>"+ "No Applied Effects on this Champion"+"<html>";
		}
		else {
		for(int i=0 ; i<c.getAppliedEffects().size();i++) {
			info = info + "<html>" + "<br>"+ (i+1)+"]"+ "Name: "+c.getAppliedEffects().get(i).getName()+"<br>"+"Duration: "+c.getAppliedEffects().get(i).getDuration()+"<html>"; 
		}
		}
		info = info + "<html>" +"<br>"+ "Abilities: "+"<br>"+"<html>";
		int i =1 ;
		for(Ability a : c.getAbilities()) {
			info = info +"<html>"+i+"]"+ "Name: "+a.getName()+"<br>"+"Current Cool Down: "+a.getCurrentCooldown()+"<br>"+"Base Cool Down : "+a.getBaseCooldown()+"<br>"+"CastArea: "+a.getCastArea()
			+"<br>"+"Cast Range: "+a.getCastRange()+"<br>"+"Mana Cost: " + a.getManaCost()+"<br>"+"Required Action Points: "+a.getRequiredActionPoints();
			if(a instanceof DamagingAbility)
				info = info + "<br>"+"Damaging Amount: "+ ((DamagingAbility) a).getDamageAmount()+"<br>"+"Type: Damaging Ability"+"<br>"+"<html>";
			else if (a instanceof HealingAbility)
				info = info +"<br>"+ "Healing Amount: "+ ((HealingAbility) a).getHealAmount()+"<br>"+"Type: Healing Ability"+"<br>"+"<html>";
			else {
				info = info +"<br>" + "Effect: "+ ((CrowdControlAbility) a).getEffect().getName()+"<br>"+"Duration : "+ ((CrowdControlAbility) a).getEffect().getDuration()+"<br>"+"Type: Crowd Control Abiility"+"<br>"+"<html>";
			}
			i++;
		}
		return info;
	}
	public String EffectToString(Effect e) {
		String info="";
		info = "Name: "+e.getName()+"\n"+"Duration: "+e.getDuration();
		return info;
	}
	public static void main(String [] args) {
		Control x = new Control();
	}
}
