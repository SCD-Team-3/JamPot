package application;

public class Attribute {
	private String name;
	private int value;
	private String unit;
	private QuickAccessGroup display;
	private int min;
	private int max;

	public Attribute(String name, String unit, int def, int min, int max) {
		this.name = name;
		this.unit = unit;
		this.value = def;
		this.min = min;
		this.max = max;
	}
	
	public void setDisplayer(QuickAccessGroup display) {
		this.display = display;
	}
	
	public int change(String type) {
		if (type.equals("+")) {
			if (value < max)
				value++;
		} else if (type.equals("-")) {
			if (value > min)
				value--;
		}
		
		display.updateValue();
		
		return value;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getUnit() {
		return unit;
	}
}
