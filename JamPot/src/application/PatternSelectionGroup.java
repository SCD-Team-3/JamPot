package application;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class PatternSelectionGroup extends Group{
	
	private static final int BACKGROUND_GRAY = 26;
	private static final int OUTLINE_GRAY = 127;
	private static final double WIDTH = 330;
	private static final double HEIGHT = 255;
	private static final double OUTLINE_WIDTH = 1;
	private static final double ARC_RADIUS = 50;
	private static final double FONT_SIZE = 24;
	private static final double PADDING = 30;
	private MotionPattern pattern;
	private PatternSelectionPane manager;
	private Rectangle outline;
	private Rectangle fill;
	private Label title;
	private PatternSelectionButton button;
	
	public PatternSelectionGroup(MotionPattern pattern, double x, double y, PatternSelectionPane manager) 
	{
		this.manager = manager;
		
		// Create and configure outline
		outline = new Rectangle(x, y, WIDTH, HEIGHT);
		outline.setFill(Color.rgb(OUTLINE_GRAY, OUTLINE_GRAY, OUTLINE_GRAY));
		outline.setArcHeight(ARC_RADIUS);
		outline.setArcWidth(ARC_RADIUS);
		getChildren().add(outline);
		
		// Create and configure fill
		fill = new Rectangle(x + OUTLINE_WIDTH, y + OUTLINE_WIDTH, WIDTH - OUTLINE_WIDTH * 2, HEIGHT - OUTLINE_WIDTH * 2);
		fill.setFill(Color.rgb(BACKGROUND_GRAY, BACKGROUND_GRAY, BACKGROUND_GRAY));
		fill.setArcHeight(ARC_RADIUS);
		fill.setArcWidth(ARC_RADIUS);
		getChildren().add(fill);
		
		// Create and configure label
		title = new Label(pattern.getName());
		title.setFont(Font.font("Calibri", FontWeight.NORMAL, FONT_SIZE));
		title.setAlignment(Pos.TOP_CENTER);
		title.setTextAlignment(TextAlignment.CENTER);
		title.setPrefWidth(WIDTH);
		title.setLayoutX(x);
		title.setLayoutY(y + HEIGHT - PADDING);
		title.setTextFill(Color.WHITE);
		getChildren().add(title);
		
		
		// Create and configure button
		button = new PatternSelectionButton(x, y, e->select());
		getChildren().add(button);
	}
	
	public void select() {
		manager.selectPattern(pattern);
	}
	
	public MotionPattern getPattern() {
		return pattern;
	}
	
	public void setPattern(MotionPattern newPattern) {
		pattern = newPattern;
	}
}