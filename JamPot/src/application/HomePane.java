package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class HomePane extends Pane implements MotionPatternDisplayer {
	
	// Non-visible properties
	private MotionPattern pattern;
	private JamPot main;
	
	// Images
	private Image preset;
	private Image pencil;
	private Image disk;
	
	// On-screen elements
	private NavButton patternSelect;
	private NavButton edit;
	private NavButton save;
	private QuickAccessGroup amplitudeGrp;
	private QuickAccessGroup frequencyGrp;
	private Canvas patternImage;

	public HomePane(JamPot newMain, MotionPattern newPattern) {
		main = newMain;
		
		// Configure pattern
		pattern = newPattern;
		pattern.setDisplayer(this);
		
		// Establish background image
		setBackground(new Background(new BackgroundFill(Color.rgb(26,26,26), null, null)));
		
		// Create & configure "Select Pattern" button
		patternSelect = new NavButton("Select Pattern", 10, 500, e->selectPattern());
		preset = new Image("file:preset.png", 35, 35, true, true);
		patternSelect.setGraphic(new ImageView(preset));
		getChildren().add(patternSelect);
		
		// Create & configure "Edit" button
		edit = new NavButton("Edit", 520, 130, e->edit());
		pencil = new Image("file:edit.png", 35, 35, true, true);
		edit.setGraphic(new ImageView(pencil));
		getChildren().add(edit);
		
		// Configure "Save" button
		save = new NavButton("", 660, 130, e->save());
		disk = new Image("file:save.png", 35, 35, true, true);
		save.setGraphic(new ImageView(disk));
		getChildren().add(save);
		
		// Configure Amplitude grouping
		amplitudeGrp = new QuickAccessGroup(pattern.getAmplitude(), 535, 10);
		getChildren().add(amplitudeGrp);
		
		// Create and configure Frequency grouping
		frequencyGrp = new QuickAccessGroup(pattern.getFrequency(), 535, 275);
		getChildren().add(frequencyGrp);
		
		// Display motion pattern image
		patternImage = pattern.getImage(515, 515, Color.rgb(26, 26, 26), Color.WHITE, 2);
		patternImage.setTranslateX(10);
		patternImage.setTranslateY(10);
		getChildren().add(patternImage);
	}
	
	public void selectPattern() {
		main.goToPatternSelectionPane(pattern);
	}
	
	public void edit() {
		main.goToEditPane(pattern);
	}
	
	public void save() {
		main.goToKeyboardPane(pattern);
	}
	
	public void update(MotionPattern callingPattern) {
		if (callingPattern != pattern)
			throw new IllegalArgumentException("Calling pattern does not match current pattern");
		
		getChildren().remove(patternImage);
		patternImage = pattern.getImage(515, 515, Color.rgb(26, 26, 26), Color.WHITE, 2);
		patternImage.setTranslateX(10);
		patternImage.setTranslateY(10);
		getChildren().add(patternImage);
	}
}