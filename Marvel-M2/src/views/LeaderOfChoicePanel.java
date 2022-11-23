package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.world.Champion;

public class LeaderOfChoicePanel extends JPanel implements ActionListener{
	private String [] x;
	private String [] y;
	private JComboBox firstPlayersLeader;
	private JComboBox secondPlayersLeader;
	private Champion Leader1;
	private Champion Leader2;
	private Champion[] Options1;
	private Champion[] Options2;
	private JButton next;
	private Control control;
	private JLabel Leader1choice;
	private JLabel Leader2choice;
	public LeaderOfChoicePanel(Champion champion, Champion champion2, Champion champion3, Champion champion4,
			Champion champion5, Champion champion6, Control control) {
		this.control=control;
		next=new JButton("Next");
		next.addActionListener(this);
		this.add(next);
		Options1=new Champion[3];
		Options2=new Champion[3];
		Champion [] one = {champion , champion2,champion3};
		Champion [] two = {champion4,champion5,champion6};
		for(int i=0; i<3;i++) {
			Options1[i]=one[i];
			Options2[i]=two[i];
		}
		x=new String[3];
		String [] b = {champion.getName(),champion2.getName(),champion3.getName()};
		for(int i=0; i<3;i++) {
			x[i]=b[i];
		}
		
		firstPlayersLeader = new JComboBox(x);
		firstPlayersLeader.setBounds(300,203,120,35);
		this.add(firstPlayersLeader);
		y = new String [3]; 
		String [] z = {champion4.getName(),champion5.getName(),champion6.getName()};
		for(int j =0;j<3;j++) {
			y[j]=z[j];
		}
		secondPlayersLeader = new JComboBox(y);
		this.add(secondPlayersLeader);
		Leader1choice = new JLabel("Player's 1 Leader");
		Leader2choice = new JLabel("Player's 2 Leader");
		this.add(Leader1choice);
		this.add(Leader2choice);
		Leader1choice.setBounds(50, 10, 200, 30);
		Leader2choice.setBounds(300, 10, 200, 30);
		firstPlayersLeader.setBounds(50,100,200,30);
		secondPlayersLeader.setBounds(300,100 , 200, 30);
		next.setBounds(150,200, 200, 30);
		this.setLayout(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==next) {
		for(int i =0; i<3;i++) {
			if(firstPlayersLeader.getSelectedIndex()==i) {
				Leader1 = Options1[i];
			}
	}
		for(int j =0; j<3; j++) {
			if(secondPlayersLeader.getSelectedIndex()==j) {
				Leader2 = Options2[j];
			}
		}
	}
			control.GamePanel(Leader1,Leader2);
			}
}
