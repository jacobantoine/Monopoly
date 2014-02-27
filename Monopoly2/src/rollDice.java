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
	
    public int die1;
	public int die2;
	
	//Tracking which player is moving.
	private int tracker, playerNum;
	String [] parts;
	drawingBoard component = new drawingBoard();
	boardPosition [] positionArray = new boardPosition [40];        //Property array of coordinates
	Player [] player = new Player [4];
	
	//Makes the dice panel and sets the roll to player one on intialization
	public rollDice() throws IOException
	{
		panel = new JPanel();
		listener = new ClickRollDice();
		tracker = 0;
		playerNum = 0;
		
		createPlayers();
		createDicePanel();
		setArray();
	}
	
	public void createPlayers()
	{
		for (int x = 0; x < 4; x++)
		{
			player[x] = new Player(x);
			
		}
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
    
    /**
     * This rolls the dice.
     */
    public void getMoves()
    {
    	Random randomGen = new Random();
		die1 =6; //randomGen.nextInt(6) + 1;
		die2 =6; //randomGen.nextInt(6) + 1;
		
		textField.setText(die1 + " , " + die2);
		
		//The playerNum tracks which player is rolling.
		//The tracker variable is edited for later use in determining which player is next.
		

		//Player and tracker updated for the next players roll.
		playerNum = tracker;
		
		if (die1 == die2)
			player[playerNum].doubles++;
		else
		{
			//If player 3's turn is over then we change to player 1.
			if (tracker == 3)  
				tracker = 0;
			else
				tracker = tracker + 1;
			
			player[playerNum].doubles = 0;
		}
		
		//If player rolled 3 dbls in a row, off to jail they go.
		if (player[playerNum].doubles == 3)
		{
			player[playerNum].location = 10;
			player[playerNum].inJail = true;
			player[playerNum].doubles = 0;
			
			if (tracker == 3)
				tracker = 0;
			else
				tracker = tracker + 1;
		}
		else
			player[playerNum].location = player[playerNum].location + die1 + die2;
		//If the player's position extends past the board locations, set the true location.
		if (player[playerNum].location > 39)
		{
			player[playerNum].location = player[playerNum].location - 40;
				
		}
	}
		
    
    /**
     *This listens for the roll dice button to be clicked.
     *The component takes the position of the player that rolled the dice.
     *The location is also sent to the player class, so each players current location is updated.
     */
    class ClickRollDice implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			getMoves();
			
			component.appendXY(positionArray[(player[playerNum].location)].xCoordinate, positionArray[(player[playerNum].location)].yCoordinate, playerNum);
			player[playerNum].setLocation((player[playerNum].location), playerNum);
			
		}
	}
    
    
    
}
