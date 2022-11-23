package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NamesPanel extends JPanel implements ActionListener{
private JButton next;
private JTextField firstPlayerName;
private JTextField secondPlayerName;
private JLabel firstPlayer;
private JLabel secondPlayer;
private Control control;
public NamesPanel(Control control) {
	this.control=control;
	this.setLayout(null);
	this.setSize(500, 500);
	this.setVisible(true);
	next = new JButton("Next");
	firstPlayer = new JLabel("Fisrt Player's Name: ");
	secondPlayer = new JLabel ("Second Player's Name: ");
	firstPlayerName= new JTextField("First Player Name");
	secondPlayerName = new JTextField ("Second Player Name");
	this.add(next);
	this.add(firstPlayer);
	this.add(secondPlayer);
	this.add(firstPlayerName);
	this.add(secondPlayerName);
	next.addActionListener(this);
	firstPlayer.setBounds(20 , 20 , 150, 30);
	secondPlayer.setBounds(20, 70, 150, 30);
	firstPlayerName.setBounds(200, 20, 150, 30);
	secondPlayerName.setBounds(200, 70, 150, 30);
	next.setBounds(200, 130, 120, 30);
	this.setSize(350,350);
}
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==next) {
	if(firstPlayerName.getText().equals(""))
		JOptionPane.showMessageDialog(this, "Both Players must enter their names", "ERROR",JOptionPane.ERROR_MESSAGE);
	else if (secondPlayerName.getText().equals(""))
		JOptionPane.showMessageDialog(this, "Both Players must enter their names", "ERROR",JOptionPane.ERROR_MESSAGE);
	else
		control.chooseChampionsPanel(firstPlayerName.getText(),secondPlayerName.getText());
}
}
}
