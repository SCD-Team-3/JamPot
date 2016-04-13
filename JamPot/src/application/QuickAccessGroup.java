/* MotionPattern.java
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
 * Represents the group of GUI elements used to display and manipulate
 * a specific attribute of a motion pattern on screen.
 */

package application;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class QuickAccessGroup extends Group {
	private static final int BACKGROUND_GRAY = 26;
	private static final int OUTLINE_GRAY = 127;
	private static final double WIDTH = 255;
	private static final double OUTLINE_WIDTH = 1;
	private static final double ARC_RADIUS = 100;
	private static final double FONT_SIZE = 44;
	private static final double PADDING = 10;
	private Attribute attr;
	private Rectangle outline;
	private Rectangle fill;
	private Label title;
	private TextField value;
	
	public QuickAccessGroup(Attribute attr, double x, double y) {
		
		this.attr = attr;
		attr.setDisplayer(this);
		
		// Create and configure outline
		outline = new Rectangle(x, y, WIDTH, WIDTH);
		outline.setFill(Color.rgb(OUTLINE_GRAY, OUTLINE_GRAY, OUTLINE_GRAY));
		outline.setArcHeight(ARC_RADIUS);
		outline.setArcWidth(ARC_RADIUS);
		this.getChildren().add(outline);
		
		// Create and configure fill
		fill = new Rectangle(x + OUTLINE_WIDTH, y + OUTLINE_WIDTH, WIDTH - OUTLINE_WIDTH * 2, WIDTH - OUTLINE_WIDTH * 2);
		fill.setFill(Color.rgb(BACKGROUND_GRAY, BACKGROUND_GRAY, BACKGROUND_GRAY));
		fill.setArcHeight(ARC_RADIUS);
		fill.setArcWidth(ARC_RADIUS);
		this.getChildren().add(fill);
		
		// Create and configure label
		title = new Label(attr.getName());
		title.setFont(Font.font("Calibri", FontWeight.NORMAL, FONT_SIZE));
		title.setAlignment(Pos.TOP_CENTER);
		title.setTextAlignment(TextAlignment.CENTER);
		title.setPrefWidth(WIDTH);
		title.setLayoutX(x);
		title.setLayoutY(y + PADDING);
		title.setTextFill(Color.WHITE);
		this.getChildren().add(title);
		
		// Create and configure text box
		value = new TextField();
		value.setDisable(true);
		value.setPrefWidth(WIDTH - PADDING * 2);
		value.setLayoutX(x + PADDING);
		value.setLayoutY(y + FONT_SIZE + PADDING * 2);
		value.setFont(Font.font("Calibri", FontWeight.BOLD, FONT_SIZE));
		value.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), null)));
		value.setStyle("-fx-text-fill: white; -fx-opacity: 1.0;");
		value.setAlignment(Pos.CENTER);
		updateValue();
		this.getChildren().add(value);
		
		// Create and configure +/- buttons
		IncrButton pos = new IncrButton("+", x + 2 * WIDTH / 3 - 25, y + FONT_SIZE * 2 + PADDING * 8, 50, attr);
		IncrButton neg = new IncrButton("-", x + 1 * WIDTH / 3 - 25, y + FONT_SIZE * 2 + PADDING * 8, 50, attr);
		this.getChildren().add(pos);
		this.getChildren().add(neg);
	}
	
	public void updateValue() {
		this.value.setText(Integer.toString(attr.getValue()) + " " + attr.getUnit());
	}
}
