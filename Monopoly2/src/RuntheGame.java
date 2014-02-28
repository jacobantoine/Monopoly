//Brian Hatcher Gary Donovich Jacob Antoine Cody Mathena

import java.io.IOException;

import javax.swing.*;

//Class that runs the program

public class RuntheGame extends JPanel
{
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException 
	{
	         
		 Monopoly2 board = new Monopoly2();              //Makes the board
		 board.setTitle("Monopoly");
	     board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //Sets its size and display to true
	     board.setSize(1100, 750);
	     board.setLocationByPlatform(true);
	     board.setVisible(true);
	}

}
