//Brian Hatcher Gary Donovich Jacob Antoine

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
<<<<<<< HEAD
=======

import jtesting.Player;
>>>>>>> origin/Cody

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
	
	boardPosition [] positionArray = new boardPosition [40];        //Property array of coordinates
	String [] parts;
	private JPanel panel;
	private JRadioButton rdbtnPay, rdbtnPay_1;
	private ActionListener listener;
	
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
	       	FileInputStream fstream = new FileInputStream("C:\\position.txt");
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
	
	public int checkGO(int position)
	{
		if (position > 39)
			return 200;
		else
			return 0;		
	}
	
<<<<<<< HEAD
	public void checkLocation(int location)
	{
		switch (location)
		{
			case 4: incomeTax();
=======
	public void checkLocation(int location, Player player)
	{
		switch (location)
		{
			case 4: incomeTax(player);
>>>>>>> origin/Cody
					break;
		}
	}
	
<<<<<<< HEAD
	public void incomeTax()
	{
		listener = new ClickIncomeTax();
		
=======
	public void incomeTax(Player player)
	{
		listener = new ClickIncomeTax();

>>>>>>> origin/Cody
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		rdbtnPay = new JRadioButton("Pay 10%");
		rdbtnPay.addActionListener(listener);
		
		rdbtnPay_1 = new JRadioButton("Pay $200");
		rdbtnPay_1.addActionListener(listener);
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnPay);
		group.add(rdbtnPay_1);
		
		panel.add(rdbtnPay);
		panel.add(rdbtnPay_1);
		
		this.add(panel);
		this.setSize(200, 100);
		this.setVisible(true);
	}

	class ClickIncomeTax implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if (rdbtnPay.isSelected())
			{
<<<<<<< HEAD
				System.out.println("TEST");
			}
			else
			{
				System.out.println("TEST DOS");
=======
				GameRun.gameInfoText.append("TEST UNO\n\n");
				
			}
			if (rdbtnPay_1.isSelected())
			{
				GameRun.gameInfoText.append("TEST DOS\n\n");
				//player.addCash(200);
>>>>>>> origin/Cody
			}
		}
	}
	
}
