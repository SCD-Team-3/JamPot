package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PatternSelectionPane extends Pane {
	private static final int HORIZONTAL_PATTERNS = 2;
	private static final int VERTICAL_PATTERNS = 2;
	private static final double[] xs = {10, 350};
	private static final double[] ys = {10, 275};
	
	// Non-visible properties
	private JamPot main;
	private MotionPattern selectedPattern = null;
	private MotionPattern oldPattern;
	
	// On-screen elements
	private Slider slider;
	private Rectangle greenBorder;
	private NavButton accept;
	private NavButton back;
	private NavButton delete;
	private PatternSelectionGroup[][] visiblePatterns;
	
	public PatternSelectionPane(JamPot newMain, MotionPattern prev) {
		main = newMain;
		oldPattern = prev;
		
		setBackground(new Background(new BackgroundFill(Color.rgb(26,26,26), null, null)));
		
		// Create & configure "Accept" button
		accept = new NavButton("", 72.5, 130, e->acceptPatternSelection());
		Image ok = new Image("file:accept.png", 35, 35, true, true);
		accept.setGraphic(new ImageView(ok));
		getChildren().add(accept);
		
		// Create & configure "Delete" button
		delete = new NavButton("", 275, 130, e->delete());
		Image x = new Image("file:delete.png", 35, 35, true, true);
		delete.setGraphic(new ImageView(x));
		getChildren().add(delete);		
		
		// Create & configure "Back" button
		back = new NavButton("", 477.5, 130, e->back());
		Image arrow = new Image("file:back.png", 35, 35, true, true);
		back.setGraphic(new ImageView(arrow));
		getChildren().add(back);	
		
		//Create and configure slider
		int pages = -main.getPatterns().size() / (VERTICAL_PATTERNS * HORIZONTAL_PATTERNS);
		if (main.getPatterns().size() % (VERTICAL_PATTERNS * HORIZONTAL_PATTERNS) > 0)
			pages++;
		slider = new SelectionSlider(-pages,-1,-1); 
		slider.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {

		    	change(""); 	
		    }
		});
		getChildren().add(slider);
		
		//Create and configure slider buttons		
		CircleButton increment = new CircleButton(712.5, 25, e->change("+"));
		Image up = new Image("file:up.png", 25, 35, true, true);
		increment.setGraphic(new ImageView(up));
		getChildren().add(increment);
		CircleButton decrement = new CircleButton(712.5, 470, e->change("-"));
		Image down = new Image("file:down.png", 25, 35, true, true);
		decrement.setGraphic(new ImageView(down));
		getChildren().add(decrement);
		
		// Create and configure patterns
		visiblePatterns = new PatternSelectionGroup[VERTICAL_PATTERNS][HORIZONTAL_PATTERNS];
		for (int i = 0; i < visiblePatterns.length; i++) {
			for (int j = 0; j < visiblePatterns[i].length; j++) {
				int storageIndex = (int) (-slider.getValue()-1) + i * VERTICAL_PATTERNS + j;
				if (storageIndex < main.getPatterns().size()) {
					visiblePatterns[i][j] = new PatternSelectionGroup(main.getPatterns().get(storageIndex), xs[i], ys[j], this);
					getChildren().add(visiblePatterns[i][j]);
				}
			}
		}
		
		// Create and configure selected border
		greenBorder = new Rectangle();
		greenBorder.setStyle("-fx-border-color: green;");
		greenBorder.setArcHeight(50);
		greenBorder.setArcWidth(50);
		greenBorder.setWidth(330);
		greenBorder.setHeight(255);
		greenBorder.setFill(Color.TRANSPARENT);
		greenBorder.setStroke(Color.GREEN);
		greenBorder.setStrokeWidth(3);
	}
	
	public void selectPattern(MotionPattern selected)
	{
		getChildren().remove(greenBorder);
		selectedPattern = selected;
		
		int selectedIndex = main.getPatterns().indexOf(selected);
		int topLeftIndex = main.getPatterns().indexOf(visiblePatterns[0][0].getPattern());
		
		if (selectedIndex < 0 || topLeftIndex < 0 || selectedIndex < topLeftIndex)
			System.out.println("Pattern does not exist");
		else {
			greenBorder.setX(xs[(selectedIndex - topLeftIndex) / HORIZONTAL_PATTERNS]);
			greenBorder.setY(ys[(selectedIndex - topLeftIndex) % HORIZONTAL_PATTERNS]);
		
			getChildren().add(greenBorder);
		}
	}
	
	public void acceptPatternSelection()
	{
		if (selectedPattern != null)
			main.goToHomePane(selectedPattern);
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
		
		for (int i = 0; i < visiblePatterns.length; i++) {
			for (int j = 0; j < visiblePatterns[i].length; j++) {
				int storageIndex = (int) slider.getValue() + i * VERTICAL_PATTERNS + j;
				if (storageIndex < main.getPatterns().size())
					visiblePatterns[i][j].setPattern(main.getPatterns().get(storageIndex));
			}
		}
		selectPattern(selectedPattern);
	}
	
	public void delete()
	{
		// Remove pattern from storage
		main.removePattern(selectedPattern);
		
		// Remove patterns from display
		for (int i = 0; i < visiblePatterns.length; i++)
			for (int j = 0; j < visiblePatterns[i].length; j++)
				getChildren().remove(visiblePatterns[i][j]);
		getChildren().remove(greenBorder);
		
		// Re-add all patterns to display
		visiblePatterns = new PatternSelectionGroup[VERTICAL_PATTERNS][HORIZONTAL_PATTERNS];
		for (int i = 0; i < visiblePatterns.length; i++) {
			for (int j = 0; j < visiblePatterns[i].length; j++) {
				int storageIndex = (int) (-slider.getValue()-1) + i * VERTICAL_PATTERNS + j;
				if (storageIndex < main.getPatterns().size()) {
					visiblePatterns[i][j] = new PatternSelectionGroup(main.getPatterns().get(storageIndex), xs[i], ys[j], this);
					getChildren().add(visiblePatterns[i][j]);
				}
			}
		}
	}
		
	public void back()
	{
		main.goToHomePane(oldPattern);
	}

}
