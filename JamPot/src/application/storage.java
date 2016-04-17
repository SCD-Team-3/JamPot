package application;

import java.util.Vector;


public class storage {
	private String name;
	private static Vector<String> SavePatternName = new Vector<String>(0,1);
	
	
	
	public storage(String name)
	{
		this.name = name;
	}
	
	public void store()
	{
		if(SavePatternName.size()<11)
		{
			SavePatternName.add(name);
		}
		//SavePatternName.add(name);
	}
	
	public void remove(int index)
	{
		SavePatternName.remove(index);
	}
	
	public static String getSavePatternName(int index)
	{	
		if (index == 1)
			return "Default 1";
		else if (index == 2)
			return "Default 2";
		else if (index == 3)
			return "Default 3";
		else if (index == 4)
			return "Default 4";
		else if(index > SavePatternName.size()+4)
			return "Empty";
		else
			return SavePatternName.get(index-5);
	}

}
