package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class CircleButton extends Button{
	private Stop[] stops;
	private LinearGradient unpressedGradient;
	private LinearGradient pressedGradient;
	private String type;
	private Attribute attr;
	

	public CircleButton(String type, double x, double y, EventHandler<ActionEvent> value) {
		this.type = type;
		
		//Set layout
		this.setLayoutX(x);
		this.setLayoutY(y);
		
		// Set behavior
		this.setOnAction(value);
		this.setOnMousePressed(e->setClickStyle());
		this.setOnMouseReleased(e->setUnclickStyle());
		
		//Set style
		this.setStyle(
                "-fx-background-radius: 30em; " +
                "-fx-background-color: gray;" +
                "-fx-min-width: 40px; " +
                "-fx-min-height: 40px; " +
                "-fx-max-width: 40px; " +
                "-fx-max-height: 40px;"
        );
		
		
		
		stops = new Stop[] {new Stop(0, Color.rgb(26, 26, 26)), new Stop(1, Color.rgb(39, 39, 39))};
		unpressedGradient = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops);
		pressedGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
	}
	
	public void setClickStyle() {
		this.setBackground(new Background(new BackgroundFill(pressedGradient, null, null)));
	}
	public void setUnclickStyle() {
		this.setBackground(new Background(new BackgroundFill(unpressedGradient, null, null)));
	}

}
