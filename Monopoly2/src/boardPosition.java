//Brian Hatcher Gary Donovich Jacob Antoine Cody Mathena

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JPanel;

//This class is used as an array of itself to keep track of property coordinates

public class boardPosition extends JPanel
{
	int position;
	int xCoordinate;
	int yCoordinate;
	
	boardPosition [] positionArray = new boardPosition [40];        //Property array of coordinates
	String [] parts;
	
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
	
	public static int checkGO(int position)
	{
		if (position > 39)
			return 200;
		else
			return 0;		
	}
	
	public void checkLocation(int location)
	{
		switch (location)
		{
			case 4: incomeTax();
					break;
		}
	}
	
	public void incomeTax()
	{
		
	}

	
	
}
