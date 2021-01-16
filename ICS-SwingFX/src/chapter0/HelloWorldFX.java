package chapter0;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

/**A Hello World JavaFX program.
 * @author mulliganaceous
 */
public class HelloWorldFX extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	/**Set up GUI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello, World!");
		Button b1 = new Button();
		b1.setText("B1");;
		b1.setOnAction(new ButtonHandler());
		
		StackPane root = new StackPane();
		root.getChildren().add(b1);
		primaryStage.setScene(new Scene(root, 200, 100));
		primaryStage.show();
	}
	
	/**Event handler for the button
	 */
	private class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			System.out.println(event.getSource() + "clicked!");
		}
	}
}
