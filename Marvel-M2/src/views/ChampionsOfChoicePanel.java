package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import engine.Game;
import model.world.Champion;

public class ChampionsOfChoicePanel extends JPanel implements ActionListener ,  ItemListener{
	String [] ChampionNames;
	ArrayList<Integer> selectedChampions;
	JComboBox a;
	JComboBox b;
	JComboBox c;
	JComboBox x;
	JComboBox y;
	JComboBox z;
	JButton next;
	Control control;
	JTextArea championsdata;
	JLabel title1;
	JLabel title2;
	JLabel name1;
	JLabel name2;
	JLabel title3;
	public ChampionsOfChoicePanel(Control control, String NameofPlayerone, String nameofPlayertwo) {
			this.control=control;
			this.setLayout(null);
			ChampionNames = new String [Game.getAvailableChampions().size()+1];
			name1 = new JLabel (NameofPlayerone);
			name2 = new JLabel(nameofPlayertwo);
			title1 = new JLabel("-Select 3 Champions-");
			title2 = new JLabel ("-Select 3 Champions-");
			title3 = new JLabel ("Champion's Information");
			ChampionNames[0]="-Select a Champion-";
			this.add(name1);
			this.add(name2);
			this.add(title1);
			this.add(title2);
			this.add(title3);
			for(int i=1;i<Game.getAvailableChampions().size()+1;i++) {
			ChampionNames[i]=Game.getAvailableChampions().get(i-1).getName();
			}
			a = new JComboBox (ChampionNames);
			b = new JComboBox (ChampionNames);
			c = new JComboBox (ChampionNames);
			x = new JComboBox (ChampionNames);
			y = new JComboBox (ChampionNames);
			z = new JComboBox (ChampionNames);
			this.add(a);
			this.add(b);
			this.add(c);
			this.add(x);
			this.add(y);
			this.add(z);
			next= new JButton("Next");
			next.addActionListener(this);
			championsdata = new JTextArea();
			championsdata.setEditable(false);
			this.add(championsdata);
			this.add(next);
			name1.setBounds(10, 20, 150, 30);
			name2.setBounds(200, 20, 150, 30);
			title3.setBounds(360, 20, 150, 30);
			title1.setBounds(10, 60, 150, 30);
			title2.setBounds(200, 60, 150, 30);
			championsdata.setBounds(360, 60, 200, 650);
			a.setBounds(10, 100, 150, 30);
			b.setBounds(10, 140, 150, 30);
			c.setBounds(10,180,150,30);
			x.setBounds(200, 100, 150, 30);
			y.setBounds(200, 140, 150, 30);
			z.setBounds(200, 180, 150, 30);
			next.setBounds(150,260 , 150, 30);
			a.addItemListener(this);
			b.addItemListener(this);
			c.addItemListener(this);
			x.addItemListener(this);
			y.addItemListener(this);
			z.addItemListener(this);
			
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==next) {
			if(a.getSelectedIndex()==0||b.getSelectedIndex()==0||c.getSelectedIndex()==0||x.getSelectedIndex()==0||y.getSelectedIndex()==0||z.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(this, "Each Player Must Choose 3 Champions", "Warnin!", JOptionPane.WARNING_MESSAGE);
			}
			else {
			selectedChampions= new ArrayList<Integer>();
			selectedChampions.add(a.getSelectedIndex()-1);
			if(selectedChampions.contains(b.getSelectedIndex()-1))
				JOptionPane.showMessageDialog(this, " A champion must be chosen only once by either players", "ERROR", JOptionPane.ERROR_MESSAGE);
			else {
				selectedChampions.add(b.getSelectedIndex()-1);
			if(selectedChampions.contains(c.getSelectedIndex()-1)) {
				JOptionPane.showMessageDialog(this, " A champion must be chosen only once by either players", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			else {
				selectedChampions.add(c.getSelectedIndex()-1);
				if(selectedChampions.contains(x.getSelectedIndex()-1)) {
					JOptionPane.showMessageDialog(this, " A champion must be chosen only once by either players", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else {
					selectedChampions.add(x.getSelectedIndex()-1);
					if(selectedChampions.contains(y.getSelectedIndex()-1)) {
						JOptionPane.showMessageDialog(this, " A champion must be chosen only once by either players", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					else {
						selectedChampions.add(y.getSelectedIndex()-1);
						if(selectedChampions.contains(z.getSelectedIndex()-1)) {
							JOptionPane.showMessageDialog(this, " A champion must be chosen only once by either players", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
						else {
							selectedChampions.add(z.getSelectedIndex()-1);
							control.LeaderPanel(Game.getAvailableChampions().get(a.getSelectedIndex()-1),Game.getAvailableChampions().get(b.getSelectedIndex()-1),Game.getAvailableChampions().get(c.getSelectedIndex()-1),Game.getAvailableChampions().get(x.getSelectedIndex()-1),Game.getAvailableChampions().get(y.getSelectedIndex()-1),Game.getAvailableChampions().get(z.getSelectedIndex()-1));
						}
					}
				}
			}
		}
		}
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==a && a.getSelectedIndex()!=0) {
			championsdata.setText(control.ChampiontoString(a.getSelectedIndex()-1));
		}
		if(e.getSource()==b && b.getSelectedIndex()!=0) {
			championsdata.setText(control.ChampiontoString(b.getSelectedIndex()-1));
		}
		if(e.getSource()==c && c.getSelectedIndex()!=0) {
			championsdata.setText(control.ChampiontoString(c.getSelectedIndex()-1));
		}
		if(e.getSource()==x && x.getSelectedIndex()!=0) {
			championsdata.setText(control.ChampiontoString(x.getSelectedIndex()-1));
		}
		if(e.getSource()==y && y.getSelectedIndex()!=0) {
			championsdata.setText(control.ChampiontoString(y.getSelectedIndex()-1));
		}
		if(e.getSource()==z && z.getSelectedIndex()!=0) {
			championsdata.setText(control.ChampiontoString(z.getSelectedIndex()-1));
		}
		if(a.getSelectedIndex()==0&&b.getSelectedIndex()==0&&c.getSelectedIndex()==0&&x.getSelectedIndex()==0&&y.getSelectedIndex()==0&&z.getSelectedIndex()==0) {
			championsdata.setText("");
		}
	}

}
