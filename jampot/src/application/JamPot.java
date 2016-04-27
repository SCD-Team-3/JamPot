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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JamPot extends Application {

	private Stage theStage;
	private File storageFile;
	private FileStorage storage;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		
		storageFile = new File("patterns.jpt");
		FileInputStream fin = new FileInputStream("patterns.jpt");
		ObjectInputStream ois = new ObjectInputStream(fin);
		storage = (FileStorage) ois.readObject();
		ois.close();
		
		theStage = primaryStage; 
		
		goToHomePane(storage.data.get(0));
		primaryStage.setTitle("JamPot");
		primaryStage.show();

	}
	
	public void save() {
		try {
			FileOutputStream fout = new FileOutputStream(storageFile, false);
			FileChannel outChan = fout.getChannel();
			outChan.truncate(0);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(storage);
			oos.flush();
			oos.close();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<MotionPattern> getPatterns() {
		return storage.data;
	}

	public void addPattern(MotionPattern pattern) {
		storage.data.add(pattern);
		save();
	}
	
	public void removePattern(MotionPattern pattern) {
		storage.data.remove(pattern);
		save();
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

