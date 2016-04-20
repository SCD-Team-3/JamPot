package application;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class KeyboardPane extends Pane {
	private static final double[] xs = {121, 178, 235, 292, 349, 406, 463, 520, 577, 634,
										149.5, 206.5, 263.5, 320.5, 377.5, 434.5, 491.5, 548.5, 605.5,
										104.5, 206.5, 263.5, 320.5, 377.5, 434.5, 491.5, 548.5, 605.5,
										104.5, 206.5, 548.5};
	private static final double[] ys = {295, 295, 295, 295, 295, 295, 295, 295, 295, 295,
										350, 350, 350, 350, 350, 350, 350, 350, 350,
										405, 405, 405, 405, 405, 405, 405, 405, 405,
										460, 460, 460};
	private static final double[] widths = {45, 45, 45, 45, 45, 45, 45, 45, 45, 45,
											45, 45, 45, 45, 45, 45, 45, 45, 45,
											90, 45, 45, 45, 45, 45, 45, 45, 90,
											90, 330, 45};
	private final static int NUMKEYS = 31;
	
	// Non-visible properties
	private JamPot main;
	private MotionPattern pattern;
	private String patternName;
	private KeyButton[] keyButtons;
	
	// On-Screen Elements
	private TextField KeyboardTextbox;

	public KeyboardPane(JamPot main, MotionPattern pattern) {
		this.main = main;
		this.pattern = pattern;
		
		setBackground(new Background(new BackgroundFill(Color.rgb(26,26,26), null, null)));	
		
		// Create and configure text box
		KeyboardTextbox = new TextField();
		KeyboardTextbox.setDisable(true);
		KeyboardTextbox.setPrefWidth(500);
		KeyboardTextbox.setLayoutX(150);
		KeyboardTextbox.setLayoutY(215);
		KeyboardTextbox.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		KeyboardTextbox.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), null)));
		KeyboardTextbox.setStyle("-fx-text-fill: white; -fx-opacity: 1.0;");
	    KeyboardTextbox.setAlignment(Pos.CENTER);
		KeyboardTextbox.setText(patternName);
		getChildren().add(KeyboardTextbox);
		
		keyButtons = new KeyButton[NUMKEYS];
		for (int i = 0; i < NUMKEYS; i++) {
			keyButtons[i] = new KeyButton("", xs[i], widths[i], ys[i], e->key(""));
			getChildren().add(keyButtons[i]);
		}
		lower();
		
		// Create & configure "Accept" button
		NavButton accept = new NavButton("", 221.5, 130, e->acceptKeyboard());
		Image ok = new Image("file:accept.png", 40, 40, true, true);
		accept.setGraphic(new ImageView(ok));
		getChildren().add(accept);
		
		// Create & configure "Cancel" button
		NavButton cancel = new NavButton("", 391.5, 130, e->cancelKeyboard());
		Image x = new Image("file:cancel.png", 40, 40, true, true);
		cancel.setGraphic(new ImageView(x));
		getChildren().add(cancel);
	}

	public KeyboardPane(Node... arg0) {
		super(arg0);
	}
	
	public void acceptKeyboard()
	{
		pattern.setName(patternName);
		main.getPatterns().add(pattern);
		
		main.goToHomePane(pattern);
	}
		
	public void cancelKeyboard()
	{
		main.goToHomePane(pattern);
	}
	
	public void key(String letter)
	{
		switch (letter) {
			case "Caps":
				caps();
				break;
				
			case "Lower":
				lower();
				break;
				
			case "Back":
				backspace();
				break;
				
			case "?123":
				numbers();
				break;
				
			case "abcd":
				lower();
				break;
				
			case "Space":
				letter = " ";
				
			default:
				if (patternName.length() < 25) {
					patternName += letter;
					KeyboardTextbox.setText(patternName);
				}
				break;
		}
	}
	
	private void setKeys(String[] keys) {
		if (keys.length != NUMKEYS)
			throw new IllegalArgumentException("Invalid array length");
		
		for (int i = 0; i < NUMKEYS; i++) {
			String str = keys[i];
			keyButtons[i].setText(str);
			keyButtons[i].setOnAction(e->key(str));
		}
	}
	
	private void caps()
	{
		setKeys(new String[] {	"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
								"A", "S", "D", "F", "G", "H", "J", "K", "L",
								"Lower", "Z", "X", "C", "V", "B", "N", "M", "Back",
								"?123", "Space", "."});
	}
		
	private void lower()
	{
		setKeys(new String[] {	"q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
				  				"a", "s", "d", "f", "g", "h", "j", "k", "l",
				  				"Caps", "z", "x", "c", "v", "b", "n", "m", "Back",
				  				"?123", "Space", "."});
	}
		
	private void numbers()
	{
		setKeys(new String[] {	"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
								"-", "/", ":", ";", "(", ")", "$", "&", "@",
								"Caps", "\"", "\'", "?", "!", "%", "#", "*", "Back",
								"abcd", "Space", ","});
	}
	
	private void backspace()
	{
		if(patternName.length() > 0)
			patternName = patternName.substring(0, patternName.length() - 1);
		KeyboardTextbox.setText(patternName);
	}
}
