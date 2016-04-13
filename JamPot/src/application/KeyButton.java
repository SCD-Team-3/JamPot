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

public class KeyButton extends Button {
	private Stop[] stops;
	private LinearGradient unpressedGradient;
	private LinearGradient pressedGradient;

	public KeyButton(String text, double x, double width, double y, EventHandler<ActionEvent> value) {
		this.setText(text);
		this.setOnAction(value);
		this.setOnMousePressed(e->setClickStyle());
		this.setOnMouseReleased(e->setUnclickStyle());
		this.setPrefSize(width, 45);
		this.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
		this.setTextFill(Color.WHITE);
		this.setLayoutX(x);
		this.setLayoutY(y);
		stops = new Stop[] {new Stop(0, Color.rgb(26, 26, 26)), new Stop(1, Color.rgb(39, 39, 39))};
		unpressedGradient = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops);
		pressedGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
		this.setBackground(new Background(new BackgroundFill(unpressedGradient, null, null)));
		this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
	}
	 

	public void setClickStyle() {
		this.setBackground(new Background(new BackgroundFill(pressedGradient, null, null)));
	}
	public void setUnclickStyle() {
		this.setBackground(new Background(new BackgroundFill(unpressedGradient, null, null)));
	}
}

