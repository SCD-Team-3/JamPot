package application;

import java.util.Vector;


public class storage {
	private String name;
	private Vector<String> SavePatternName = new Vector<String>(0,1);
	
	
	public storage(String name)
	{
		this.name = name;
	}

	public void store()
	{
		SavePatternName.add(name);
	}
	
	public void remove(int index)
	{
		SavePatternName.remove(index);
	}
	
	public Vector<String> getSavePatternName()
	{
		return SavePatternName;
	}

}
