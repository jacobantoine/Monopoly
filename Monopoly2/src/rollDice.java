//Brian Hatcher Gary Donovich Jacob Antoine Cody Mathena

import javax.swing.JPanel;
import javax.swing.JButton;

import java.util.Random;

import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import jtesting.Player;

//Class that controls the dice. Controls that place to go and which players turn it is.

public class rollDice extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton btnRollDice;
	private JTextField textField;
	private ActionListener listener;
	
	//Holds each player's new position.
	public int position;
	public int position_P2;
	public int position_P3;
	public int position_P4;
    public int die1;
	public int die2;
	//Tracking which player is moving.
	private int tracker;
	private int player;
	String [] parts;
	drawingBoard component = new drawingBoard();
	boardPosition [] positionArray = new boardPosition [40];        //Property array of coordinates
	Player location = new Player();
	
	//Makes the dice panel and sets the roll to player one on intialization
	public rollDice() throws IOException
	{
		panel = new JPanel();
		listener = new ClickRollDice();
		player = 0;
		tracker = 1;
		
		createDicePanel();
		setArray();
	}
	
	//Create the panel that houses the ability to roll dice.
	public void createDicePanel()
    {
    	panel = new JPanel();
		
        btnRollDice = new JButton("Roll Dice");
		btnRollDice.addActionListener(listener);
        panel.add(btnRollDice);
        
        textField = new JTextField();
        textField.setEditable(false);
        panel.add(textField);
        textField.setColumns(4);
        this.add(panel);
    }
	
	//Makes the array of property coordinates to move the image to
	
	protected void setArray () throws IOException 
	   {
			// Open the file
	       	FileInputStream fstream = new FileInputStream("C:\\Program Files\\eclipse\\Monopoly2\\src\\position");
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
    
    /**
     * This rolls the dice.
     */
    public void getMoves()
    {
    	Random randomGen = new Random();
		die1 = randomGen.nextInt(6) + 1;
		die2 = randomGen.nextInt(6) + 1;
		
		textField.setText(die1 + " , " + die2);
		
		//The tracker tracks which player is rolling.
		//The player variable is edited for later use in determining which player moved.
		
		//Player 1.
		if (tracker == 1)
		{
			//Player and tracker updated for the next players roll.
			player = tracker;
			tracker = tracker + 1;
			position = position + die1 + die2;
			//If the player's position extends past the board locations, set the true location.
			if (position > 39)
			{
				position = position - 39;
				
			}
		}
		//Player 2.
		else if (tracker == 2)
		{
			//Player and tracker updated for the next players roll.
			player = tracker;
			tracker = tracker + 1;
			position_P2 = position_P2 + die1 + die2;
			//If the player's position extends past the board locations, set the true location.
			if (position_P2 > 39)
			{
				position_P2 = position_P2 - 39;
				
			}
		}
		//Player 3.
		else if (tracker == 3)
		{
			//Player and tracker updated for the next players roll.
			player = tracker;
			tracker = tracker + 1;
			position_P3 = position_P3 + die1 + die2;
			//If the player's position extends past the board locations, set the true location.
			if (position_P3 > 39)
			{
				position_P3 = position_P3 - 39;
				
			}
		}
		//Player 4.
		else
		{
			//Player and tracker updated for the next players roll.
			tracker = 1;
			player = 0;
			position_P4 = position_P4 + die1 + die2;
			//If the player's position extends past the board locations, set the true location.
			if (position_P4 > 39)
			{
				position_P4 = position_P4 - 39;
				
			}
		}
		
    }
    
    /**
     *This listens for the roll dice button to be clicked.
     *The component takes the position of the player that rolled the dice.
     *The if's are to check which players location was edited.
     *The location is also sent to the player class, so each players current location is updated.
     */
    class ClickRollDice implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			getMoves();
			if (player == 1)
			{
				component.appendXY(positionArray[position].xCoordinate, positionArray[position].yCoordinate, player);
				location.setLocation(position, player);
			}
			else if (player == 2)
			{
				component.appendXY(positionArray[position_P2].xCoordinate, positionArray[position_P2].yCoordinate, player);
				location.setLocation(position_P2, player);
			}
			else if (player == 3)
			{
				component.appendXY(positionArray[position_P3].xCoordinate, positionArray[position_P3].yCoordinate, player);
				location.setLocation(position_P3, player);
			}
			else
			{
				component.appendXY(positionArray[position_P4].xCoordinate, positionArray[position_P4].yCoordinate, player);
				location.setLocation(position_P4, player);
			}
			
		}
	}
    
    
    
}
