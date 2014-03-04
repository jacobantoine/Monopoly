//Brian Hatcher Gary Donovich Jacob Antoine Cody Mathena

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.JTextField;

import jtesting.Player;

//This class is used as an array of itself to keep track of property coordinates

public class boardPosition extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int position;
	int xCoordinate;
	int yCoordinate;
	
	Player temp;
	
	boardPosition [] positionArray = new boardPosition [40];        //Property array of coordinates
	String [] parts;
	private JPanel panel;
	private JRadioButton rdbtnPay, rdbtnPay_1;
	private ActionListener listener;
	private static Dialog dialog;
	
	
	private JPanel jailPanel;
	private JRadioButton rdbtnPayYes, rdbtnPayNo;
	private JTextField txtPayTo;

	
	boardPosition () throws IOException
	{
		position = 0;
		xCoordinate = 0;
		yCoordinate = 0;
	}
	
	//Reads Coordinates and position in from a text file for each property
	public void setInfo (int number, int X, int Y) throws IOException
	{
		position = number;
		xCoordinate = X;
		yCoordinate = Y;
	}	
	
	protected void setArray () throws IOException 
	   {
			// Open the file
	       	FileInputStream fstream = new FileInputStream("C:\\Users\\Gary Danovich\\Documents\\GitHub\\Monopoly\\Monopoly2\\src\\position");

	       	BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	       	String line;
	       	
		   for(int i = 0; i < 40; i++)
	        {
	        	

	        	//Read File Line By Line
	        	if ((line = br.readLine()) == null)
	        	{
	        		System.out.println ("The File Did not Open");
	        		break;
	        	}
	        	else
	        	{
	        		parts = line.split("\\|");
	        	}
	        	
	        	positionArray[i] = new boardPosition();
	        	positionArray[i].setInfo(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
	        	
	        	//Close the input stream
	        	if (i == 39)
	        	{
	        		br.close();
	        	}

	        	
	        }
	   }
	
	
	//Directs you to which function the location you landed on does
	public void checkLocation(int location, Player currentPlayer)
	{
		switch (location)
		{
			case 4: incomeTax(currentPlayer); 
					break;
			case 30: Jail(currentPlayer);
					break;
			case 38: luxuryTax(currentPlayer);
		}
	}
	
	//This function sends you to jail and sets the variables likewise
	public void Jail(Player currentPlayer)
	{
		listener = new ClickJailOptions();
		
		temp = new Player(currentPlayer.player);
		temp = currentPlayer;
		
		jailPanel = new JPanel();
		getContentPane().add(jailPanel, BorderLayout.CENTER);
		
		txtPayTo = new JTextField();
		txtPayTo.setEditable(false);
		txtPayTo.setText("Pay $50 to get out of jail");
		jailPanel.add(txtPayTo);
		txtPayTo.setColumns(15);
		
		rdbtnPayYes = new JRadioButton("Yes");
		rdbtnPayYes.addActionListener(listener);
		
		rdbtnPayNo = new JRadioButton("No");
		rdbtnPayNo.addActionListener(listener);
		
		ButtonGroup jailGroup = new ButtonGroup();
		jailGroup.add(rdbtnPayYes);
		jailGroup.add(rdbtnPayNo);
		jailGroup.clearSelection();
		jailPanel.add(rdbtnPayYes);
		jailPanel.add(rdbtnPayNo);
		
		dialog = new JDialog(this , "Go To Jail?" , true);
		dialog.add(jailPanel);
		dialog.setSize(200, 100);                    
		dialog.setVisible(true);
	}
	
	//Function to make the income tax panel to appear
	public void incomeTax(Player currentPlayer)
	{
		listener = new ClickIncomeTax();
		
		temp = new Player(currentPlayer.player);
		temp = currentPlayer;

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		rdbtnPay = new JRadioButton("Pay 10%");
		rdbtnPay.addActionListener(listener);
		
		rdbtnPay_1 = new JRadioButton("Pay $200");
		rdbtnPay_1.addActionListener(listener);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnPay);
		group.add(rdbtnPay_1);
		group.clearSelection();
		panel.add(rdbtnPay);
		panel.add(rdbtnPay_1);

		dialog = new JDialog(this, true);
		dialog.setUndecorated(true);
		dialog.setLocation(800,500);
		dialog.add(panel);
		dialog.setSize(200, 100);                    
		dialog.setVisible(true);

		
		
	}

	//Function to pay your luxury tax
	public void luxuryTax(Player currentPlayer)
	{
		if(currentPlayer.money>75)
		{
		GameRun.gameInfoText.append("Player " + currentPlayer.player + " paid $" + 75 + " in taxes.\n");
		currentPlayer.money = currentPlayer.money-75;
		}
		else
		{
			GameRun.gameInfoText.append("Player " + currentPlayer.player + " cannot pay\n");
			GameRun.gameInfoText.append("Player " + currentPlayer.player + " has " + currentPlayer.money + " dollars \n");
			
		}
	}

	
	//Listener for Income tax. It decides on whether you pay 200 or 10%
	class ClickIncomeTax implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
			if (rdbtnPay.isSelected())
			{
				GameRun.gameInfoText.append("Player " + temp.player + " paid $" + 
						(int)(temp.money*.1) + " in taxes.\n");
				GameRun.changeMoney = (int) (-temp.money * .1);
			}
			if (rdbtnPay_1.isSelected())
			{
				GameRun.gameInfoText.append("Player " + temp.player + " paid $" + 200 + 
						" in taxes.\n");
				GameRun.changeMoney = -200;
			}
			dispose();

		}
	}
	
	class ClickJailOptions implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
			if (rdbtnPayYes.isSelected())
			{
				GameRun.changeMoney = -50;
				temp.location = 30;
				GameRun.gameInfoText.append("Player " + temp.player + " did not go to jail.\n");
			}
			if (rdbtnPayNo.isSelected())
			{
				temp.location = 10;
				temp.inJail = true;
				temp.doubles = 0;
				temp.jailTime = -1;
				GameRun.gameInfoText.append("Player " + temp.player + " went to jail.\n");
			}
			dispose();
		}
	}
	
}
