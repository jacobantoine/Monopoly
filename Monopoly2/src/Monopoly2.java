//Brian Hatcher, Gary Donovich, Jacob Antoine, Cody Mathena

import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;

//Class that creates the dice panel on the board and initializes the array of property coordinates 
//to move and player tracking

	@SuppressWarnings("serial")
	public class Monopoly2 extends JFrame 
	{

	   public Monopoly2() throws IOException 
	   {
		   createAndShowGui();
	   }

	   public void createAndShowGui() throws IOException
	   {
		   	 rollDice dice = new rollDice();
		     getContentPane().add(dice.component);
		     getContentPane().add(dice, BorderLayout.EAST);
		     pack();

	   }
	}
