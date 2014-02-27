//Brian Hatcher Gary Donovich Jacob Antoine Cody Mathena

import javax.swing.JPanel;
import javax.swing.JButton;

import java.util.Random;

import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import jtesting.Player;

//Class that controls the dice. Controls that place to go and which players turn it is.

public class rollDice extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton btnRollDice;
	private JTextField textField;
	private ActionListener listener;
	
    private int die1, die2;
	
	//Tracking which player is moving.
	private int tracker, playerNum;
	
	drawingBoard component = new drawingBoard();
	boardPosition boardSpaces = new boardPosition(); 
	
	Player [] player = new Player [4];
	
	//Makes the dice panel and sets the roll to player one on intialization
	public rollDice() throws IOException
	{
		panel = new JPanel();
		listener = new ClickRollDice();
		tracker = 0;
		playerNum = 0;
		
		boardSpaces.setArray();
		createPlayers();
		createDicePanel();
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
	
	
    
    /**
     * This rolls the dice.
     */
    public void getMoves()
    {
    	Random randomGen = new Random();
		die1 = randomGen.nextInt(6) + 1;
		die2 = randomGen.nextInt(6) + 1;
		
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
			player[playerNum].addCash(200);
			player[playerNum].location = player[playerNum].location - 40;
				
		}
		
		//boardPosition.checkLocation(player[playerNum].location);
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
			
			component.appendXY(boardSpaces.positionArray[(player[playerNum].location)].xCoordinate, boardSpaces.positionArray[(player[playerNum].location)].yCoordinate, playerNum);
			player[playerNum].setLocation((player[playerNum].location), playerNum);
			
		}
	}
    
    
    
}
