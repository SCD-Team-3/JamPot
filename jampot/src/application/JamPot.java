package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JamPot extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		Button btn = new Button("Click me");
		btn.setOnAction(e->btn_click());
		StackPane frame = new StackPane();
		frame.getChildren().add(btn);
		Scene scene = new Scene(frame, 200, 50);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hello World!");
		primaryStage.show();
	}
	
	public void btn_click()
	{
		System.out.println("You clicked the button");
	}

}