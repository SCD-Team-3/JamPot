package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PatternSelectionButton extends Button {


	public PatternSelectionButton(double x, double y, EventHandler<ActionEvent> value) {
		this.setOnAction(value);
		this.setPrefSize(330, 255);
		this.setLayoutX(x);
		this.setLayoutY(y);

		this.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
	}
	
}
