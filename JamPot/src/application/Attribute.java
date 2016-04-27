/* Attribute.java
 *
 * AUTHOR
 * Will Weaver
 * Computer Engineering Major
 * Mechanical Engineering Senior Capstone Design Team 3
 * Michigan Technological University
 *
 * SPONSOR
 * Mitch Baldwin
 * Stryker Instruments
 *
 * DESCRIPTION
 * Represents an attribute of a motion pattern as it is being manipulated by the
 * user in the GUI.
 */

package application;

import java.io.Serializable;

public class Attribute implements Serializable {
	private static final long serialVersionUID = 7665084137230844408L;
	private String name;
	private double value;
	private String unit;
	transient private QuickAccessGroup displayer;
	private MotionPattern pattern;
	private double min;
	private double max;

	public Attribute(MotionPattern pattern, String name, String unit, double def, double min, double max) {
		this.pattern = pattern;
		this.name = name;
		this.unit = unit;
		this.value = def;
		this.min = min;
		this.max = max;
	}
	
	public void setDisplayer(QuickAccessGroup display) {
		this.displayer = display;
	}
	
	public double change(String type) {
		if (type.equals("+")) {
			if (value < max)
				value++;
		} else if (type.equals("-")) {
			if (value > min)
				value--;
		}
		
		displayer.updateValue();
		pattern.update();
		
		return value;
	}
	
	public void setValue(double newVal) {
		this.value = newVal;
	}
	
	public String getName() {
		return name;
	}
	
	public double getValue() {
		return value;
	}
	
	public String getUnit() {
		return unit;
	}
}
