/* JamPot.java
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
 * Provides a GUI for interacting with the JamJel device by generating and
 * modifying motion patterns.
 */

package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class JamPot extends Application {

	Stage theStage;
	String PatternName = "";
	TextField KeyboardTextbox;
	Slider slider = new SelectionSlider(-3,-1,-1); 
	Rectangle GreenBorder = new Rectangle();
	MotionPattern curPattern;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		
		theStage = primaryStage; 
		
		curPattern = MotionPattern.CIRCLE;
		
		Scene scene = new Scene(getHomePane(), 800, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JamPot");
		primaryStage.show();

	}
	
	public Pane getHomePane() {
		Pane frame = new Pane();
		frame.setBackground(new Background(new BackgroundFill(Color.rgb(26,26,26), null, null)));
		
		// Create & configure "Select Pattern" button
		NavButton patternSelect = new NavButton("Select Pattern", 10, 500, e->selectPattern());
		Image preset = new Image("file:preset.png", 35, 35, true, true);
		patternSelect.setGraphic(new ImageView(preset));
		frame.getChildren().add(patternSelect);
		
		// Create & configure "Edit" button
		NavButton edit = new NavButton("Edit", 520, 130, e->edit());
		Image pencil = new Image("file:edit.png", 35, 35, true, true);
		edit.setGraphic(new ImageView(pencil));
		frame.getChildren().add(edit);
		
		// Create & configure "Save" button
		NavButton save = new NavButton("", 660, 130, e->save());
		Image disk = new Image("file:save.png", 35, 35, true, true);
		save.setGraphic(new ImageView(disk));
		frame.getChildren().add(save);
		
		// Create and configure Amplitude grouping
		int amp = ((int) curPattern.getAmplitude()) & 0xFFFF;
		Attribute amplitude = new Attribute("Amplitude", "%", (int) (((double) amp) / 65535 * 100), 0, 100);
		QuickAccessGroup amplitudeGrp = new QuickAccessGroup(amplitude, 535, 10);
		frame.getChildren().add(amplitudeGrp);
		
		// Create and configure Frequency grouping
		Attribute frequency = new Attribute("Frequency", "Hz", curPattern.getFrequency(), 1, 200);
		QuickAccessGroup frequencyGrp = new QuickAccessGroup(frequency, 535, 275);
		frame.getChildren().add(frequencyGrp);
		
		// Display motion pattern image
		Canvas image = curPattern.getImage(515, 515, Color.rgb(26, 26, 26), Color.WHITE, 2);
		image.setTranslateX(10);
		image.setTranslateY(10);
		frame.getChildren().add(image);
		
		return frame;
	}
	
	public Pane getSettingPane() {
		Pane frame = new Pane();
		frame.setBackground(new Background(new BackgroundFill(Color.rgb(26,26,26), null, null)));
		
		// Create & configure "Accept" button
		NavButton accept = new NavButton("", 125, 130, e->accept());
		Image ok = new Image("file:accept.png", 35, 35, true, true);
		accept.setGraphic(new ImageView(ok));
		frame.getChildren().add(accept);
		
		// Create & configure "Cancel" button
		NavButton cancel = new NavButton("", 335, 130, e->cancel());
		Image x = new Image("file:cancel.png", 35, 35, true, true);
		cancel.setGraphic(new ImageView(x));
		frame.getChildren().add(cancel);		
		
		// Create and configure Rotation grouping
		Attribute rotation = new Attribute("Rotation", "%", 0, 0, 100);
		QuickAccessGroup rotationGrp = new QuickAccessGroup(rotation, 535, 10);
		frame.getChildren().add(rotationGrp);	
		
		// Create and configure Distortion grouping
		Attribute distortion = new Attribute("Distortion", "%", 0, 0, 200);
		QuickAccessGroup distortionGrp = new QuickAccessGroup(distortion, 535, 275);
		frame.getChildren().add(distortionGrp);	
		
		return frame;
	}
	

	public Pane getPatternSelectionPane() {
		
		Pane frame = new Pane();
		frame.setBackground(new Background(new BackgroundFill(Color.rgb(26,26,26), null, null)));
		
		// Create & configure "Accept" button
		NavButton accept = new NavButton("", 72.5, 130, e->acceptPatternSelection());
		Image ok = new Image("file:accept.png", 35, 35, true, true);
		accept.setGraphic(new ImageView(ok));
		frame.getChildren().add(accept);
		
		// Create & configure "Delete" button
		NavButton delete = new NavButton("", 275, 130, e->cancel());
		Image x = new Image("file:delete.png", 35, 35, true, true);
		delete.setGraphic(new ImageView(x));
		frame.getChildren().add(delete);		
		
		// Create & configure "Back" button
		NavButton back = new NavButton("", 477.5, 130, e->back());
		Image arrow = new Image("file:back.png", 35, 35, true, true);
		back.setGraphic(new ImageView(arrow));
		frame.getChildren().add(back);	
		
		//Create and configure slider
		slider.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {

		    	change(""); 	
		    }
		});
		frame.getChildren().add(slider);
		
		//Create and configure slider buttons		
		CircleButton increment = new CircleButton("+", 712.5, 25, e->change("+"));
		Image up = new Image("file:up.png", 25, 35, true, true);
		increment.setGraphic(new ImageView(up));
		frame.getChildren().add(increment);
		CircleButton decrement = new CircleButton("-", 712.5, 470, e->change("-"));
		Image down = new Image("file:down.png", 25, 35, true, true);
		decrement.setGraphic(new ImageView(down));
		frame.getChildren().add(decrement);
		
		//Create saved group
		int CurrentSliderValue = (int) slider.getValue();
		PatternSelectionGroup UpperLeft = new PatternSelectionGroup(storage.getSavePatternName(CurrentSliderValue*(-1)*4-3), 10, 10);
		frame.getChildren().add(UpperLeft); 
		PatternSelectionGroup UpperRight = new PatternSelectionGroup(storage.getSavePatternName(CurrentSliderValue*(-1)*4-2), 350, 10);
		frame.getChildren().add(UpperRight);
		PatternSelectionGroup LowerLeft = new PatternSelectionGroup(storage.getSavePatternName(CurrentSliderValue*(-1)*4-1), 10, 275);
		frame.getChildren().add(LowerLeft);
		PatternSelectionGroup LowerRight = new PatternSelectionGroup(storage.getSavePatternName(CurrentSliderValue*(-1)*4), 350, 275);
		frame.getChildren().add(LowerRight);
		
		//Create pattern selection buttons
		PatternSelectionButton UL = new PatternSelectionButton(10, 10, e->SelectPattern(10, 10));
		frame.getChildren().add(UL);
		PatternSelectionButton UR = new PatternSelectionButton(350, 10, e->SelectPattern(350, 10));
		frame.getChildren().add(UR);
		PatternSelectionButton LL = new PatternSelectionButton(10, 275, e->SelectPattern(10, 275));
		frame.getChildren().add(LL);
		PatternSelectionButton LR = new PatternSelectionButton(350, 275, e->SelectPattern(350, 275));
		frame.getChildren().add(LR);
		
		//frame.getChildren().add(GreenBorder);
		
			
		return frame;
		
	}
	
	private Pane getKeyboardPane(String[] keys) {
		final int NUMKEYS = 31;
		Pane frame = new Pane();
		frame.setBackground(new Background(new BackgroundFill(Color.rgb(26,26,26), null, null)));	
		
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
		KeyboardTextbox.setText(PatternName);
		frame.getChildren().add(KeyboardTextbox);
		
		if (keys.length != NUMKEYS)
			throw new IllegalArgumentException("Invalid number of keys");
		
		final double[] xs = new double[] {	121, 178, 235, 292, 349, 406, 463, 520, 577, 634,
											149.5, 206.5, 263.5, 320.5, 377.5, 434.5, 491.5, 548.5, 605.5,
											104.5, 206.5, 263.5, 320.5, 377.5, 434.5, 491.5, 548.5, 605.5,
											104.5, 206.5, 548.5};
		final double[] widths = new double[] {	45, 45, 45, 45, 45, 45, 45, 45, 45, 45,
												45, 45, 45, 45, 45, 45, 45, 45, 45,
												90, 45, 45, 45, 45, 45, 45, 45, 90,
												90, 330, 45};
		final double[] ys = new double[] {	295, 295, 295, 295, 295, 295, 295, 295, 295, 295,
											350, 350, 350, 350, 350, 350, 350, 350, 350,
											405, 405, 405, 405, 405, 405, 405, 405, 405,
											460, 460, 460};
		
		for (int i = 0; i < NUMKEYS; i++) {
			String str = keys[i];
			frame.getChildren().add(new KeyButton(str, xs[i], widths[i], ys[i], e->key(str)));
		}
		
		// Create & configure "Accept" button
		NavButton accept = new NavButton("", 221.5, 130, e->acceptKeyboard());
		Image ok = new Image("file:accept.png", 40, 40, true, true);
		accept.setGraphic(new ImageView(ok));
		frame.getChildren().add(accept);
		
		// Create & configure "Cancel" button
		NavButton cancel = new NavButton("", 391.5, 130, e->cancelKeyboard());
		Image x = new Image("file:cancel.png", 40, 40, true, true);
		cancel.setGraphic(new ImageView(x));
		frame.getChildren().add(cancel);
		
		return frame;
	}

	public Pane getKeyboardPane() {
		return getKeyboardPane(new String[] {	"q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
												"a", "s", "d", "f", "g", "h", "j", "k", "l",
												"Caps", "z", "x", "c", "v", "b", "n", "m", "Back",
												"?123", "Space", "."});
	}
	
	public Pane getKeyboardCapsPane() {
		return getKeyboardPane(new String[] {	"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
												"A", "S", "D", "F", "G", "H", "J", "K", "L",
												"Lower", "Z", "X", "C", "V", "B", "N", "M", "Back",
												"?123", "Space", "."});
	}
	
	public Pane getKeyboardNumberPane() {
		return getKeyboardPane(new String[] {	"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
												"-", "/", ":", ";", "(", ")", "$", "&", "@",
												"Caps", "\"", "\'", "?", "!", "%", "#", "*", "Back",
												"abcd", "Space", ","});
	}

	public void selectPattern()
	{
	    System.out.println("Select Pattern");
	   	Scene scene= new Scene(getPatternSelectionPane(), 800, 600);
     	theStage.setScene(scene);
	}
	
	public void edit()
	{
	  	System.out.println("Edit");
	   	Scene scene= new Scene(getSettingPane(), 800, 600);
     	theStage.setScene(scene);
	}
	
	public void save()
	{
        System.out.println("Save");
		Scene scene = new Scene(getKeyboardPane(), 800, 600);
		theStage.setScene(scene);
	}	
	
	public void accept()
	{
		System.out.println("Accept");
		Scene scene= new Scene(getHomePane(), 800, 600);
		theStage.setScene(scene);
	}
		
	public void cancel()
	{
		System.out.println("Cancel");
		Scene scene= new Scene(getHomePane(), 800, 600);
		theStage.setScene(scene);
	}
		
	public void back()
	{
		System.out.println("Back");
		Scene scene= new Scene(getHomePane(), 800, 600);
		theStage.setScene(scene);
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
				if (PatternName.length() < 25) {
					PatternName += letter;
					KeyboardTextbox.setText(PatternName);
				}
				break;
		}
	}
		
	public void caps()
	{
		Scene scene= new Scene(getKeyboardCapsPane(), 800, 600);
		theStage.setScene(scene);
	}
		
	public void lower()
	{
		Scene scene= new Scene(getKeyboardPane(), 800, 600);	
		theStage.setScene(scene);	
	}
		
	public void backspace()
	{
		if(PatternName.length() > 0)
			PatternName = PatternName.substring(0, PatternName.length() - 1);
		KeyboardTextbox.setText(PatternName);
	}
		
	public void numbers()
	{
		Scene scene= new Scene(getKeyboardNumberPane(), 800, 600);
		theStage.setScene(scene);
	}
		
	public void acceptKeyboard()
	{
		storage save = new storage(PatternName);
		save.store();
		PatternName="";
			
		Scene scene= new Scene(getHomePane(), 800, 600);
		theStage.setScene(scene);
	}
		
	public void cancelKeyboard()
	{
		Scene scene= new Scene(getHomePane(), 800, 600);
		theStage.setScene(scene);
		PatternName="";
	}
	
	public void acceptPatternSelection()
	{
		Scene scene= new Scene(getHomePane(), 800, 600);
		theStage.setScene(scene);
		
	}
	
	public void change(String type)
	{
		if (type.equals("+")) {
			if (slider.getValue() < slider.getMax())
				slider.setValue(slider.getValue()+1);
		} else if (type.equals("-")) {
			if (slider.getValue() > slider.getMin())
				slider.setValue(slider.getValue()-1);
		}
		
	   	Pane pane = getPatternSelectionPane();
	   	pane.getChildren().remove(GreenBorder);
		Scene scene= new Scene(pane, 800, 600);
		theStage.setScene(scene);
	
	}
	
	public void SelectPattern(double x, double y)
	{
		GreenBorder.setStyle("-fx-border-color: green;");
		GreenBorder.setArcHeight(50);
		GreenBorder.setArcWidth(50);
		GreenBorder.setWidth(330);
		GreenBorder.setHeight(255);
		GreenBorder.setX(x);
		GreenBorder.setY(y);
		GreenBorder.setFill(Color.TRANSPARENT);
		GreenBorder.setStroke(Color.GREEN);
		GreenBorder.setStrokeWidth(3);
		
		Pane frame = getPatternSelectionPane();
		frame.getChildren().add(GreenBorder);
		Scene scene= new Scene(frame, 800, 600);
		theStage.setScene(scene);
	}
}

