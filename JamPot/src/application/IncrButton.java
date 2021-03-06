/* IncrButton.java
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
 * Represents a button used to increment and decrement attributes of motion
 * patterns on screen.
 */

package application;

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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class IncrButton extends Button {
	private Stop[] stops;
	private LinearGradient unpressedGradient;
	private LinearGradient pressedGradient;
	private String type;
	private Attribute attr;

	public IncrButton(String type, double x, double y, double radius, Attribute attr) {
		// Store and compute properties
		this.type = type;
		this.attr = attr;
		stops = new Stop[] {new Stop(0, Color.rgb(26, 26, 26)), new Stop(1, Color.rgb(39, 39, 39))};
		unpressedGradient = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops);
		pressedGradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
		
		// Set behavior
		this.setOnAction(e->clickAction());
		this.setOnMousePressed(e->setClickStyle());
		this.setOnMouseReleased(e->setUnclickStyle());
		
		// Set visual appearance
		this.setText(type);
		this.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		this.setTextFill(Color.WHITE);
		this.setShape(new Circle(radius));
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setBackground(new Background(new BackgroundFill(unpressedGradient, null, null)));
		this.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
	}
	
	public void setClickStyle() {
		this.setBackground(new Background(new BackgroundFill(pressedGradient, null, null)));
	}
	
	public void setUnclickStyle() {
		this.setBackground(new Background(new BackgroundFill(unpressedGradient, null, null)));
	}
	
	public void clickAction() {
		attr.change(type);
	}
}
