//Brian Hatcher Gary Donovich Jacob Antoine Cody Mathena

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

//Class that runs the program

public class RuntheGame
{
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException 
	{
	         
		 GameActions board = new GameActions();              //Makes the board
		 board.setTitle("Monopoly");
	     board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //Sets its size and display to true
	     board.setSize(1100, 750);
	     board.setLocationByPlatform(true);
	     board.setVisible(true);
	}

}
