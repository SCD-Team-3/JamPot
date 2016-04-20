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

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JamPot extends Application {

	private Stage theStage;
	private ArrayList<MotionPattern> patterns;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		
		// TODO Read patterns from file into arraylist
		patterns = new ArrayList<MotionPattern>();
		patterns.add(MotionPattern.LINE_A);
		patterns.add(MotionPattern.LINE_B);
		patterns.add(MotionPattern.LINE_C);
		patterns.add(MotionPattern.CIRCLE);
		
		theStage = primaryStage; 
		
		goToHomePane(patterns.get(0));
		primaryStage.setTitle("JamPot");
		primaryStage.show();

	}
	
	public ArrayList<MotionPattern> getPatterns() {
		return patterns;
	}

	public void addPattern(MotionPattern pattern) {
		patterns.add(pattern);
	}
	
	public void removePattern(MotionPattern pattern) {
		patterns.remove(pattern);
	}
	
	public void goToHomePane(MotionPattern curPattern) {
		Scene scene = new Scene (new HomePane(this, curPattern), 800, 600);
		theStage.setScene(scene);
	}
	
	public void goToKeyboardPane(MotionPattern curPattern) {
		Scene scene = new Scene (new KeyboardPane(this, curPattern), 800, 600);
		theStage.setScene(scene);
	}
	
	public void goToPatternSelectionPane(MotionPattern curPattern) {
		Scene scene = new Scene (new PatternSelectionPane(this, curPattern), 800, 600);
		theStage.setScene(scene);
	}
	
	public void goToEditPane(MotionPattern curPattern) {
		Scene scene = new Scene (new EditPane(this, curPattern), 800, 600);
		theStage.setScene(scene);
	}
}

