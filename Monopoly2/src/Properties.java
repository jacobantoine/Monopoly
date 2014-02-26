
public class Properties 
{
	public Properties()
	{
		property = new int[40];
		specialCase = new boolean[40];
		
		assignSpecialCases();
	}
	
	public void assignSpecialCases()
	{
		for (int x = 0; x < 40; x++)
		{
			if (x == 0 || x == 4 || x == 30 || x == 38)
			{
				specialCase[x] = true;
				
				//switch (x) 
				//{
				//	case 0: 
				//}
				
			}
			else
				specialCase[x] = false;
		}
		
		
	}

	private boolean[] specialCase;
	private int[] property;
	
	
}
