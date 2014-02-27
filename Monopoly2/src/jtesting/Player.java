//Brian Hatcher Gary Donovich Jacob Antoine Cody Mathena

package jtesting;

//Class for tracking players and their actions

public class Player 
{
	//Position tracker.
	public int doubles, player, location;
	public boolean inJail;
	
	//Initialize placemarkers.
	public Player(int playerNum)
	{
		player = playerNum;
		location = 0;	
		doubles = 0;
		inJail = false;
	}
	
	//Checks which player rolled, based on the roll from rollDice.
	//Sets the new location accordingly.
	public void setLocation(int newLocation, int whichPlayer)
	{
		location = newLocation;
	}
}
