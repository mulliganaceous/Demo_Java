package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GuiMain extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initializeScene(primaryStage, Model.getInstance());
		primaryStage.show();
		System.out.print(primaryStage.getHeight());
		primaryStage.setMinHeight(primaryStage.getHeight());
		primaryStage.setMinWidth(primaryStage.getWidth());
	}
	
	public static void initializeScene(Stage primaryStage, Model model) {
		primaryStage.setTitle("SpacePane");
		
		HBox root = new HBox();
		Scene scene = new Scene(root, 1024, 768);
		scene.getStylesheets().add("luminescent.ccs");
		
		SpacePane spacepane = new SpacePane(model);
		spacepane.relocate(0,0);
		root.getChildren().add(spacepane);
		
		InfoPane infopane = new InfoPane(model);
		infopane.setPrefSize(256, 768);
		infopane.relocate(768, 0);
		root.getChildren().add(infopane);
		
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);

		primaryStage.sizeToScene();
	}
}
