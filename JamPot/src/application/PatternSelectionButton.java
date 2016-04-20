package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class PatternSelectionButton extends Button {

	public PatternSelectionButton(double x, double y, EventHandler<ActionEvent> value) {
		this.setOnAction(value);
		this.setPrefSize(330, 255);
		this.setLayoutX(x);
		this.setLayoutY(y);

		this.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
	}
	
}
