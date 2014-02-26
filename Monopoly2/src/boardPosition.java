//Brian Hatcher Gary Donovich Jacob Antoine Cody Mathena

import java.io.IOException;

//This class is used as an array of itself to keep track of property coordinates

public class boardPosition
{
	int position;
	int xCoordinate;
	int yCoordinate;
	
	boardPosition () throws IOException
	{
		position = 0;
		xCoordinate = 0;
		yCoordinate = 0;
	}
	
	//Reads Coordinates and position in from a text file for each property
	public void setInfo (int number, int X, int Y) throws IOException
	{
		position = number;
		xCoordinate = X;
		yCoordinate = Y;
	}	
}
