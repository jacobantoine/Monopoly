//Brian Hatcher Gary Donovich Jacob Antoine Cody Mathena

package jtesting;

//Class for tracking players and their actions

public class Player 
{
	//Position trackers.
	int P1_Location;
	int P2_Location;
	int P3_Location;
	int P4_Location;
	
	public int doubles;
	public boolean P1_inJail;
	public boolean P2_inJail;
	public boolean P3_inJail;
	public boolean P4_inJail;
	
	//Initialize placemarkers.
	public Player()
	{
		P1_Location = 0;
		P2_Location = 0;
		P3_Location = 0;
		P4_Location = 0;
		
		doubles = 0;
		P1_inJail = false;
		P2_inJail = false;
		P3_inJail = false;
		P4_inJail = false;
	}
	
	//Checks which player rolled, based on the roll from rollDice.
	//Sets the new location accordingly.
	public void setLocation(int location, int whichPlayer)
	{
		if (whichPlayer == 1)
		{
			P1_Location = location;
		}
		else if (whichPlayer == 2)
		{
			P2_Location = location;
		}
		else if (whichPlayer == 3)
		{
			P3_Location = location;
		}
		else
		{
			P4_Location = location;
		}
	}
}
