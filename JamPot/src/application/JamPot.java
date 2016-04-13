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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class JamPot extends Application {

	Stage theStage;
	String PatternName = "";
	TextField KeyboardTextbox;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		
		theStage = primaryStage; 
		
		Scene scene = new Scene(getHomePane(), 800, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hello World!");
		primaryStage.show();
	}
	
	public Pane getHomePane() {
		Pane frame = new Pane();
		frame.setBackground(new Background(new BackgroundFill(Color.rgb(26,26,26), null, null)));
		
		// Create & configure "Select Pattern" button
		NavButton patternSelect = new NavButton("Select Pattern", 10, 500, e->selectPattern());
		frame.getChildren().add(patternSelect);
		
		// Create & configure "Edit" button
		NavButton edit = new NavButton("Edit", 520, 130, e->edit());
		frame.getChildren().add(edit);
		
		// Create & configure "Save" button
		NavButton save = new NavButton("Save", 660, 130, e->save());
		frame.getChildren().add(save);
		
		// Create and configure Amplitude grouping
		Attribute amplitude = new Attribute("Amplitude", "%", 50, 0, 100);
		QuickAccessGroup amplitudeGrp = new QuickAccessGroup(amplitude, 535, 10);
		frame.getChildren().add(amplitudeGrp);
		
		// Create and configure Frequency grouping
		Attribute frequency = new Attribute("Frequency", "Hz", 100, 1, 200);
		QuickAccessGroup frequencyGrp = new QuickAccessGroup(frequency, 535, 275);
		frame.getChildren().add(frequencyGrp);
		
		return frame;
	}
	
	public Pane getSettingPane() {
		Pane frame = new Pane();
		frame.setBackground(new Background(new BackgroundFill(Color.rgb(26,26,26), null, null)));
		
		// Create & configure "Accept" button
		NavButton accept = new NavButton("Accept", 125, 130, e->accept());
		frame.getChildren().add(accept);
		
		// Create & configure "Cancel" button
		NavButton cancel = new NavButton("Cancel", 335, 130, e->cancel());
		frame.getChildren().add(cancel);		
		
		// Create & configure "Back" button
		NavButton back = new NavButton("Back", 545, 130, e->back());
		frame.getChildren().add(back);	
		
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

	public Pane getKeyboardPane() {
		Pane frame = new Pane();
		frame.setBackground(new Background(new BackgroundFill(Color.rgb(26,26,26), null, null)));	
		
		// Create and configure text box
		KeyboardTextbox = new TextField();
		KeyboardTextbox.setDisable(true);
		KeyboardTextbox.setPrefWidth(500);
		KeyboardTextbox.setLayoutX(150);
		KeyboardTextbox.setLayoutY(150);
		KeyboardTextbox.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		KeyboardTextbox.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), null)));
		KeyboardTextbox.setStyle("-fx-text-fill: white; -fx-opacity: 1.0;");
	    KeyboardTextbox.setAlignment(Pos.CENTER);
		KeyboardTextbox.setText(PatternName);
		frame.getChildren().add(KeyboardTextbox);
		
		// Create and configure first row of keys
		KeyButton qKey = new KeyButton("q", 121, 45, 230, e->key("q"));
		frame.getChildren().add(qKey);		
		KeyButton wKey = new KeyButton("w", 178, 45, 230, e->key("w"));
		frame.getChildren().add(wKey);		
		KeyButton eKey = new KeyButton("e", 235, 45, 230, e->key("e"));
		frame.getChildren().add(eKey);		
		KeyButton rKey = new KeyButton("r", 292, 45, 230, e->key("r"));
		frame.getChildren().add(rKey);		
		KeyButton tKey = new KeyButton("t", 349, 45, 230, e->key("t"));
		frame.getChildren().add(tKey);		
		KeyButton yKey = new KeyButton("y", 406, 45, 230, e->key("y"));
		frame.getChildren().add(yKey);		
		KeyButton uKey = new KeyButton("u", 463, 45, 230, e->key("u"));
		frame.getChildren().add(uKey);
		KeyButton iKey = new KeyButton("i", 520, 45, 230, e->key("i"));
		frame.getChildren().add(iKey);
		KeyButton oKey = new KeyButton("o", 577, 45, 230, e->key("o"));
		frame.getChildren().add(oKey);
		KeyButton pKey = new KeyButton("p", 634, 45, 230, e->key("p"));
		frame.getChildren().add(pKey);
		
		// Create and configure second row of keys
		KeyButton aKey = new KeyButton("a", 149.5, 45, 285, e->key("a"));
		frame.getChildren().add(aKey);
		KeyButton sKey = new KeyButton("s", 206.5, 45, 285, e->key("s"));
		frame.getChildren().add(sKey);
		KeyButton dKey = new KeyButton("d", 263.5, 45, 285, e->key("d"));
		frame.getChildren().add(dKey);
		KeyButton fKey = new KeyButton("f", 320.5, 45, 285, e->key("f"));
		frame.getChildren().add(fKey);
		KeyButton gKey = new KeyButton("g", 377.5, 45, 285, e->key("g"));
		frame.getChildren().add(gKey);
		KeyButton hKey = new KeyButton("h", 434.5, 45, 285, e->key("h"));
		frame.getChildren().add(hKey);
		KeyButton jKey = new KeyButton("j", 491.5, 45, 285, e->key("j"));
		frame.getChildren().add(jKey);
		KeyButton kKey = new KeyButton("k", 548.5, 45, 285, e->key("k"));
		frame.getChildren().add(kKey);
		KeyButton lKey = new KeyButton("l", 605.5, 45, 285, e->key("l"));
		frame.getChildren().add(lKey);
		
		// Create and configure third row of keys
		KeyButton capsKey = new KeyButton("Caps", 104.5, 90, 340, e->caps());
		frame.getChildren().add(capsKey);
		KeyButton zKey = new KeyButton("z", 206.5, 45, 340, e->key("z"));
		frame.getChildren().add(zKey);
		KeyButton xKey = new KeyButton("x", 263.5, 45, 340, e->key("x"));
		frame.getChildren().add(xKey);
		KeyButton cKey = new KeyButton("c", 320.5, 45, 340, e->key("c"));
		frame.getChildren().add(cKey);
		KeyButton vKey = new KeyButton("v", 377.5, 45, 340, e->key("v"));
		frame.getChildren().add(vKey);
		KeyButton bKey = new KeyButton("b", 434.5, 45, 340, e->key("b"));
		frame.getChildren().add(bKey);
		KeyButton nKey = new KeyButton("n", 491.5, 45, 340, e->key("n"));
		frame.getChildren().add(nKey);
		KeyButton mKey = new KeyButton("m", 548.5, 45, 340, e->key("m"));
		frame.getChildren().add(mKey);
		KeyButton backKey = new KeyButton("Back", 605.5, 90, 340, e->backspace());
		frame.getChildren().add(backKey);
		
		// Create and configure fourth row of keys
		KeyButton numberKey = new KeyButton("?123", 104.5, 90, 395, e->numbers());
		frame.getChildren().add(numberKey);
		KeyButton spaceKey = new KeyButton("Space", 206.5, 330, 395, e->key(" "));
		frame.getChildren().add(spaceKey);
		KeyButton periodKey = new KeyButton(".", 548.5, 45, 395, e->key("."));
		frame.getChildren().add(periodKey);
		
		// Create & configure "Accept" button
		NavButton accept = new NavButton("Accept", 221.5, 130, e->acceptKeyboard());
		frame.getChildren().add(accept);
		
		// Create & configure "Cancel" button
		NavButton cancel = new NavButton("Cancel", 391.5, 130, e->cancelKeyboard());
		frame.getChildren().add(cancel);
		
		return frame;
	}

	public Pane getKeyboardCapsPane() {
		Pane frame = new Pane();
		frame.setBackground(new Background(new BackgroundFill(Color.rgb(26,26,26), null, null)));
		
		// Create and configure text box
		KeyboardTextbox = new TextField();
		KeyboardTextbox.setDisable(true);
		KeyboardTextbox.setPrefWidth(500);
		KeyboardTextbox.setLayoutX(150);
		KeyboardTextbox.setLayoutY(150);
		KeyboardTextbox.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		KeyboardTextbox.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), null)));
		KeyboardTextbox.setStyle("-fx-text-fill: white; -fx-opacity: 1.0;");
		KeyboardTextbox.setAlignment(Pos.CENTER);
		KeyboardTextbox.setText(PatternName);
		frame.getChildren().add(KeyboardTextbox);
		
		// Create and configure first row of keys
		KeyButton qKey = new KeyButton("Q", 121, 45, 230, e->key("Q"));
		frame.getChildren().add(qKey);		
		KeyButton wKey = new KeyButton("W", 178, 45, 230, e->key("W"));
		frame.getChildren().add(wKey);		
		KeyButton eKey = new KeyButton("E", 235, 45, 230, e->key("E"));
	    frame.getChildren().add(eKey);		
		KeyButton rKey = new KeyButton("R", 292, 45, 230, e->key("R"));
		frame.getChildren().add(rKey);		
		KeyButton tKey = new KeyButton("T", 349, 45, 230, e->key("T"));
		frame.getChildren().add(tKey);		
		KeyButton yKey = new KeyButton("Y", 406, 45, 230, e->key("Y"));
		frame.getChildren().add(yKey);		
		KeyButton uKey = new KeyButton("U", 463, 45, 230, e->key("U"));
		frame.getChildren().add(uKey);
		KeyButton iKey = new KeyButton("I", 520, 45, 230, e->key("I"));
		frame.getChildren().add(iKey);
		KeyButton oKey = new KeyButton("O", 577, 45, 230, e->key("O"));
		frame.getChildren().add(oKey);
		KeyButton pKey = new KeyButton("P", 634, 45, 230, e->key("P"));
		frame.getChildren().add(pKey);
		
		// Create and configure second row of keys
		KeyButton aKey = new KeyButton("A", 149.5, 45, 285, e->key("A"));
		frame.getChildren().add(aKey);
		KeyButton sKey = new KeyButton("S", 206.5, 45, 285, e->key("S"));
		frame.getChildren().add(sKey);
		KeyButton dKey = new KeyButton("D", 263.5, 45, 285, e->key("D"));
		frame.getChildren().add(dKey);
		KeyButton fKey = new KeyButton("F", 320.5, 45, 285, e->key("F"));
		frame.getChildren().add(fKey);
		KeyButton gKey = new KeyButton("G", 377.5, 45, 285, e->key("G"));
		frame.getChildren().add(gKey);
		KeyButton hKey = new KeyButton("H", 434.5, 45, 285, e->key("H"));
		frame.getChildren().add(hKey);
		KeyButton jKey = new KeyButton("J", 491.5, 45, 285, e->key("J"));
		frame.getChildren().add(jKey);
		KeyButton kKey = new KeyButton("K", 548.5, 45, 285, e->key("K"));
		frame.getChildren().add(kKey);
		KeyButton lKey = new KeyButton("L", 605.5, 45, 285, e->key("L"));
		frame.getChildren().add(lKey);
		
		// Create and configure third row of keys
		KeyButton capsKey = new KeyButton("Lower", 104.5, 90, 340, e->lower());
		frame.getChildren().add(capsKey);
		KeyButton zKey = new KeyButton("Z", 206.5, 45, 340, e->key("Z"));
		frame.getChildren().add(zKey);
		KeyButton xKey = new KeyButton("X", 263.5, 45, 340, e->key("X"));
		frame.getChildren().add(xKey);
		KeyButton cKey = new KeyButton("C", 320.5, 45, 340, e->key("C"));
		frame.getChildren().add(cKey);
		KeyButton vKey = new KeyButton("V", 377.5, 45, 340, e->key("V"));
		frame.getChildren().add(vKey);
		KeyButton bKey = new KeyButton("B", 434.5, 45, 340, e->key("B"));
		frame.getChildren().add(bKey);
		KeyButton nKey = new KeyButton("N", 491.5, 45, 340, e->key("N"));
		frame.getChildren().add(nKey);
		KeyButton mKey = new KeyButton("M", 548.5, 45, 340, e->key("M"));
		frame.getChildren().add(mKey);
		KeyButton backKey = new KeyButton("Back", 605.5, 90, 340, e->backspace());
		frame.getChildren().add(backKey);
		
		// Create and configure fourth row of keys
		KeyButton numberKey = new KeyButton("?123", 104.5, 90, 395, e->numbers());
		frame.getChildren().add(numberKey);
		KeyButton spaceKey = new KeyButton("Space", 206.5, 330, 395, e->key(" "));
		frame.getChildren().add(spaceKey);
		KeyButton periodKey = new KeyButton(".", 548.5, 45, 395, e->key("."));
		frame.getChildren().add(periodKey);
			
		// Create & configure "Accept" button
		NavButton accept = new NavButton("Accept", 221.5, 130, e->acceptKeyboard());
		frame.getChildren().add(accept);
		
		// Create & configure "Cancel" button
		NavButton cancel = new NavButton("Cancel", 391.5, 130, e->cancelKeyboard());
		frame.getChildren().add(cancel);
			
		return frame;
	}
	
	public Pane getKeyboardNumberPane() {
		Pane frame = new Pane();
		frame.setBackground(new Background(new BackgroundFill(Color.rgb(26,26,26), null, null)));
		
		// Create and configure text box
		KeyboardTextbox = new TextField();
		KeyboardTextbox.setDisable(true);
		KeyboardTextbox.setPrefWidth(500);
		KeyboardTextbox.setLayoutX(150);
		KeyboardTextbox.setLayoutY(150);
		KeyboardTextbox.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		KeyboardTextbox.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), null)));
		KeyboardTextbox.setStyle("-fx-text-fill: white; -fx-opacity: 1.0;");
		KeyboardTextbox.setAlignment(Pos.CENTER);
		KeyboardTextbox.setText(PatternName);
		frame.getChildren().add(KeyboardTextbox);
				
		// Create and configure first row of keys
		KeyButton zeroKey = new KeyButton("0", 121, 45, 230, e->key("0"));
		frame.getChildren().add(zeroKey);		
		KeyButton oneKey = new KeyButton("1", 178, 45, 230, e->key("1"));
		frame.getChildren().add(oneKey);		
		KeyButton twoKey = new KeyButton("2", 235, 45, 230, e->key("2"));
		frame.getChildren().add(twoKey);		
		KeyButton threeKey = new KeyButton("3", 292, 45, 230, e->key("3"));
		frame.getChildren().add(threeKey);		
		KeyButton fourKey = new KeyButton("4", 349, 45, 230, e->key("4"));
		frame.getChildren().add(fourKey);		
		KeyButton fiveKey = new KeyButton("5", 406, 45, 230, e->key("5"));
		frame.getChildren().add(fiveKey);		
		KeyButton sixKey = new KeyButton("6", 463, 45, 230, e->key("6"));
		frame.getChildren().add(sixKey);
		KeyButton sevenKey = new KeyButton("7", 520, 45, 230, e->key("7"));
		frame.getChildren().add(sevenKey);
		KeyButton eightKey = new KeyButton("8", 577, 45, 230, e->key("8"));
		frame.getChildren().add(eightKey);
		KeyButton nineKey = new KeyButton("9", 634, 45, 230, e->key("9"));
		frame.getChildren().add(nineKey);
		
		// Create and configure second row of keys
		KeyButton dashKey = new KeyButton("-", 149.5, 45, 285, e->key("-"));
		frame.getChildren().add(dashKey);
		KeyButton fslashKey = new KeyButton("/", 206.5, 45, 285, e->key("/"));
		frame.getChildren().add(fslashKey);
		KeyButton semiKey = new KeyButton(":", 263.5, 45, 285, e->key(":"));
		frame.getChildren().add(semiKey);
		KeyButton colonKey = new KeyButton(";", 320.5, 45, 285, e->key(";"));
		frame.getChildren().add(colonKey);
		KeyButton lparKey = new KeyButton("(", 377.5, 45, 285, e->key("("));
	    frame.getChildren().add(lparKey);
		KeyButton rparKey = new KeyButton(")", 434.5, 45, 285, e->key(")"));
		frame.getChildren().add(rparKey);
		KeyButton cashKey = new KeyButton("$", 491.5, 45, 285, e->key("$"));
		frame.getChildren().add(cashKey);
		KeyButton andKey = new KeyButton("&", 548.5, 45, 285, e->key("&"));
		frame.getChildren().add(andKey);
		KeyButton atKey = new KeyButton("@", 605.5, 45, 285, e->key("@"));
		frame.getChildren().add(atKey);
				
		// Create and configure third row of keys
		KeyButton capsKey = new KeyButton("Caps", 104.5, 90, 340, e->caps());
		frame.getChildren().add(capsKey);
		KeyButton quoteKey = new KeyButton("\"", 206.5, 45, 340, e->key("\""));
		frame.getChildren().add(quoteKey);
		KeyButton squoteKey = new KeyButton("\'", 263.5, 45, 340, e->key("\'"));
		frame.getChildren().add(squoteKey);
		KeyButton questionKey = new KeyButton("?", 320.5, 45, 340, e->key("?"));
		frame.getChildren().add(questionKey);
		KeyButton exclaimKey = new KeyButton("!", 377.5, 45, 340, e->key("!"));
		frame.getChildren().add(exclaimKey);
		KeyButton percentKey = new KeyButton("%", 434.5, 45, 340, e->key("%"));
		frame.getChildren().add(percentKey);
		KeyButton numKey = new KeyButton("#", 491.5, 45, 340, e->key("#"));
		frame.getChildren().add(numKey);
		KeyButton asterikKey = new KeyButton("*", 548.5, 45, 340, e->key("*"));
		frame.getChildren().add(asterikKey);
		KeyButton backKey = new KeyButton("Back", 605.5, 90, 340, e->backspace());
		frame.getChildren().add(backKey);
				
		// Create and configure fourth row of keys
		KeyButton lowerKey = new KeyButton("abcd", 104.5, 90, 395, e->lower());
		frame.getChildren().add(lowerKey);
		KeyButton spaceKey = new KeyButton("Space", 206.5, 330, 395, e->key(" "));
		frame.getChildren().add(spaceKey);
		KeyButton commaKey = new KeyButton(",", 548.5, 45, 395, e->key(","));
		frame.getChildren().add(commaKey);
			
		// Create & configure "Accept" button
		NavButton accept = new NavButton("Accept", 221.5, 130, e->acceptKeyboard());
		frame.getChildren().add(accept);
			
		// Create & configure "Cancel" button
		NavButton cancel = new NavButton("Cancel", 391.5, 130, e->cancelKeyboard());
		frame.getChildren().add(cancel);
			
		return frame;
	}

	public void selectPattern()
	{
	    System.out.println("Select Pattern");
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
		if (PatternName.length() < 25) {
			PatternName += letter;
			KeyboardTextbox.setText(PatternName);
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
		if(PatternName.length() > 0) {
			PatternName = PatternName.substring(0, PatternName.length() - 1);
			}
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

}

