package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class QuickAccessGroup extends Group {
	public QuickAccessGroup(double x, double y) {
		
		Rectangle outline = new Rectangle(x, y, 255, 255);
		Rectangle fill = new Rectangle(x+1, y+1, 253, 253);
		outline.setFill(Color.rgb(127, 127, 127));
		fill.setFill(Color.rgb(26, 26, 26));
		outline.setArcHeight(100);
		fill.setArcHeight(100);
		outline.setArcWidth(100);
		fill.setArcWidth(100);
		this.getChildren().add(outline);
		this.getChildren().add(fill);
		
	}
}
