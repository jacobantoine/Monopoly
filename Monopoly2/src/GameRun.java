//Brian Hatcher Gary Donovich Jacob Antoine Cody Mathena

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jtesting.Player;

//Class that controls the dice. Controls that place to go and which players turn it is.

public class GameRun extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton btnRollDice;
	private JTextField textField;
	private ActionListener listener;
	public static JTextArea gameInfoText;
	
    private int die1, die2;
	static int changeMoney;
	
	//Tracking which player is moving.
	private int tracker, playerNum;
	
	drawingBoard component = new drawingBoard();
	boardPosition boardSpaces = new boardPosition(); 
	
    static Player [] player = new Player [4];
	
	//Makes the dice panel and sets the roll to player one on intialization
	public GameRun() throws IOException
	{
		listener = new ClickRollDice();
		tracker = 0;
		playerNum = 0;
		changeMoney = 0;
		
		boardSpaces.setArray();
		createPlayers();
		createGUI();
	}
	
	public void createPlayers()
	{
		for (int x = 0; x < 4; x++)
		{
			player[x] = new Player(x+1);
		}
	}
	
	//Create the panel that houses the ability to roll dice and creates the 
	//text area for the game updates.
	public void createGUI()
    {
    	panel = new JPanel(new GridLayout(2,1));
    	JPanel textPanel = new JPanel(new GridLayout(1,1));
    	JPanel dicePanel = new JPanel(new GridLayout(5,3));
		JPanel [] spacerPanel = new JPanel[20];
        int count = 0;
        for (int i = 0; i < 20; i++)
        {
        	spacerPanel [i] = new JPanel();
        }
        btnRollDice = new JButton("Roll Dice");
		btnRollDice.addActionListener(listener);
        textField = new JTextField();
        textField.setEditable(false);
        textField.setColumns(4);
        for (int i = 0; i < 5; i++)
        {
        	for(int j = 0; j < 3; j++)
        	{
        		if (i == 0 && j == 1)
        		{
        			dicePanel.add(btnRollDice);
        		}
        		else if (i == 1 && j == 1)
        		{
        			dicePanel.add(textField);
        		}
        		else
        		{
        			dicePanel.add(spacerPanel[count]);
        			count++;
        		}
        	}
        }
        count = 0;
        gameInfoText = new JTextArea("");
        textPanel.add(gameInfoText);
        gameInfoText.setBackground(Color.GRAY);
        gameInfoText.setForeground(Color.WHITE);
        gameInfoText.setEditable(false);
        
		JScrollPane textLabelScroll = new JScrollPane(gameInfoText);
		panel.add(dicePanel);
		panel.add(textLabelScroll);
        getContentPane().add(component, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.EAST);
		
        pack();
    }
	
	//Makes the array of property coordinates to move the image to
	
	
    
    /**
     * This rolls the dice.
     */
    public void getMoves()
    {
    	Random randomGen = new Random();
		die1= randomGen.nextInt(6) + 1;
		die2= randomGen.nextInt(6) + 1;
		
		
		textField.setText(die1 + " , " + die2);
		
		//The playerNum tracks which player is rolling.
		//The tracker variable is edited for later use in determining which player is next.
		

		player[playerNum].addCash(changeMoney);
		changeMoney = 0;
		
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
			 GameRun.gameInfoText.append("Player " + player[playerNum].player + 
					 " went to jail for 3 doubles rolled.\n");
			player[playerNum].location = 10;
			player[playerNum].inJail = true;
			player[playerNum].doubles = 0;
			
			if (tracker == 3)
				tracker = 0;
			else
				tracker = tracker + 1;
		}
		//IF the player is in jail, didn't not roll doubles, and it has not been three turns in jail,
		//The player stays in jail, and the time in jail counter is incremented.
		else if ((player[playerNum].inJail == true) && (die1 != die2) && (player[playerNum].jailTime != 2))
		{
			 GameRun.gameInfoText.append("Player " + player[playerNum].player + 
					 " is in jail and cannot move.\n");
			player[playerNum].jailTime = player[playerNum].jailTime + 1;
		}
		//If the player is in jail, and rolls doubles, the player is no longer in jail.
		//Jail time reset.
		else if ((player[playerNum].inJail == true) && (die1 == die2))
		{
			player[playerNum].inJail = false;
			player[playerNum].jailTime = 0;
			GameRun.gameInfoText.append("Player " + player[playerNum].player + 
					 " rolled doubles and is free!\n");
		}
		//If the player had to wait three turns to get out of jail, let the player out,
		//Reset the jail time counter.
		else if (player[playerNum].jailTime == 2)
		{
			player[playerNum].inJail = false;
			player[playerNum].jailTime = 0;
			GameRun.gameInfoText.append("Player " + player[playerNum].player + 
					 " time is up and is free to move their next roll!\n");
		}
		//If the player gets to move freely, without the hindrances of prison.
		else	
			player[playerNum].location = player[playerNum].location + die1 + die2;	
		
		
		
		//If the player's position extends past the board locations, set the true location.
		//Passed go so collect $200
		if (player[playerNum].location > 39)
		{
			player[playerNum].addCash(200);
			player[playerNum].location = player[playerNum].location - 40;
		   GameRun.gameInfoText.append("Player " + player[playerNum].player + " passed go and collected $200\n");	
		}
		
		
		boardSpaces.checkLocation(player[playerNum].location, player[playerNum]);
		GameRun.gameInfoText.append("Player " + player[playerNum].player + " money " +  player[playerNum].money + "\n");
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
