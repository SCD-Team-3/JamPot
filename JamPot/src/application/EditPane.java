package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class EditPane extends Pane implements MotionPatternDisplayer {
	
	// Non-visible properties
	private JamPot main;
	private MotionPattern pattern;
	
	// Images
	private Image ok;
	private Image x;
	
	// On-Screen elements
	private NavButton accept;
	private NavButton cancel;
	private QuickAccessGroup rotationGrp;
	private QuickAccessGroup distortionGrp;
	private Canvas patternImage;
	
	public EditPane(JamPot newMain, MotionPattern newPattern) {
		main = newMain;
		pattern = newPattern;
		pattern.setDisplayer(this);
		
		setBackground(new Background(new BackgroundFill(Color.rgb(26, 26, 26), null, null)));
		
		// Create & configure "Accept" button
		accept = new NavButton("", 125, 130, e->accept());
		ok = new Image("file:accept.png", 35, 35, true, true);
		accept.setGraphic(new ImageView(ok));
		getChildren().add(accept);
		
		// Create & configure "Cancel" button
		cancel = new NavButton("", 335, 130, e->cancel());
		x = new Image("file:cancel.png", 35, 35, true, true);
		cancel.setGraphic(new ImageView(x));
		getChildren().add(cancel);		
		
		// Create and configure Rotation grouping
		rotationGrp = new QuickAccessGroup(pattern.getRotation(), 535, 10);
		getChildren().add(rotationGrp);	
		
		// Create and configure Distortion grouping
		distortionGrp = new QuickAccessGroup(pattern.getDistortion(), 535, 275);
		getChildren().add(distortionGrp);
		
		// Display motion pattern image
		patternImage = pattern.getImage(515, 515, Color.rgb(26, 26, 26), Color.WHITE, 2);
		patternImage.setTranslateX(10);
		patternImage.setTranslateY(10);
		getChildren().add(patternImage);
	}
	
	public void accept() {
		pattern.applyDistortion();
		main.goToHomePane(pattern);
	}
	
	public void cancel() {
		pattern.rejectDistortion();
		main.goToHomePane(pattern);
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
