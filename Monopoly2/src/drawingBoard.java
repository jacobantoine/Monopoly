//Brian Hatcher Gary Donovich Jacob Antoine Cody Mathena

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;


public class drawingBoard extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String Monopoly_LINK = "http://forum.allaboutcircuits.com/image_cache/https3images.coroflot.comuser_filesindividual_filesoriginal_350136_JmlCJZGA5U6EggqGysPR4_1u3.jpg";
	private BufferedImage monopolyImage;
	
	//The coordinate variables are for player 1, 2, 3, and 4 respectively.
	//Only the player that moved has his coordinates edited at any given time.
	int xComponent = 740;
	int yComponent = 640;
	int x2Component = 745;
	int y2Component = 640;
	int x3Component = 735;
	int y3Component = 640;
	int x4Component = 740;
	int y4Component = 635;
	
	//Buffered variables for the images.
	private BufferedImage catImage;
	private BufferedImage hatImage;
	private BufferedImage ironImage;
	private BufferedImage shoeImage;
	
	//Holds which player moved when a rollDice was called.
	private int playerTracker;
	
	
	//Get the board/piece images for use.
	drawingBoard()
	{
		URL imageUrl;
		   try 
		   {
			   	imageUrl = new URL(Monopoly_LINK);
			   	monopolyImage = ImageIO.read(imageUrl);
			   	catImage = ImageIO.read(new File("C:\\CatPiece.jpg"));
			   	hatImage = ImageIO.read(new File("C:\\Hat.png"));
			   	ironImage = ImageIO.read(new File("C:\\Iron.jpg"));
			   	shoeImage = ImageIO.read(new File("C:\\Shoe.jpg"));
		   } 
		   catch (MalformedURLException e) 
		   {
			   e.printStackTrace();
			   System.exit(-1);
		   } 
		   catch (IOException e) 
		   {
			   e.printStackTrace();
			   System.exit(-1);
		   }
		   
	}
	
	//Sets up the board/pieces initially, and changes the piece locations after every roll.
	@Override
    protected void paintComponent(Graphics g) 
    {
	    super.paintComponent(g);
	    if (monopolyImage != null) 
	    {	
	    		g.drawImage(monopolyImage, 100, 0, 700, 700, this);
			    g.drawImage(catImage, xComponent, yComponent, 35, 35, this);
			    g.drawImage(hatImage, x2Component + 13, y2Component, 35, 35, this);
			    g.drawImage(ironImage, x3Component - 10, y3Component + 8, 35, 35, this);
			    g.drawImage(shoeImage, x4Component , y4Component - 8 , 35, 35, this);
		    
	    }
    }
	
	//Changes the current player that rolled's coordinates on the board.
	protected void appendXY (int X, int Y, int player)
	{
		 
		//Holds which current player rolled.
		playerTracker = player;
		
		//Checks which player rolled, and edits the coordinates of that players piece.
		if (playerTracker == 1)
		{
			xComponent = X;
			yComponent = Y;
		}
		else if (playerTracker == 2)
		{
			x2Component = X;
			y2Component = Y;
		}
		else if (playerTracker == 3)
		{
			x3Component = X;
			y3Component = Y;
		}
		else
		{
			x4Component = X;
			y4Component = Y;
		}
		repaint();
	}
}
