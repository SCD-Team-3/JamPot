package application;

import javafx.geometry.Orientation;
import javafx.scene.control.Slider;


public class SelectionSlider extends Slider{
	private int value;
	private int min;
	private int max;
	
	public SelectionSlider(int minimum, int maximum, int current)
	{
		this.value = current;
		this.min = minimum;
		this.max = maximum;
		this.setMin(min);
		this.setMax(max);
		this.setValue(value);
		
		this.setMajorTickUnit(1);
		this.setMinorTickCount(0);
		this.setShowTickLabels(false);
		this.setShowTickMarks(false);
		this.setSnapToTicks(true);
		this.setOrientation(Orientation.VERTICAL);
		this.setPrefHeight(133.33);
		this.setLayoutX(725);
		this.setLayoutY(200);
		this.setScaleX(3);
		this.setScaleY(3);
	
		this.getStylesheets().add(getClass().getResource("Slider.css").toExternalForm());
	}
}
