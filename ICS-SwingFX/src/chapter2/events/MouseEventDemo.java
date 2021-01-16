package chapter2.events;

import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

/**A Hello World JavaFX program.
 * @author mulliganaceous
 */
public class MouseEventDemo extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	/**Set up GUI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello, World!");
		
		StackPane root = new StackPane();
		root.setStyle("-fx-background-color: lightgray");
		MouseHandler handler = new MouseHandler();
		root.addEventFilter(MouseEvent.MOUSE_CLICKED, handler);
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();
	}
	
	/**Event handler for the button
	 */
	private class MouseHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent e) {
			System.out.println("(" + e.getSceneX() + "," + e.getSceneY() + ")");
		}
	}
}
