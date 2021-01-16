package chapter2.events;

import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.Scene;
import javafx.stage.*;

/**A Hello World JavaFX program.
 * @author mulliganaceous
 */
public class MouseEventScribble extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	/**Set up GUI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello, World!");
		
		ScribblePane root = new ScribblePane();
		root.setStyle("-fx-background-color: BLACK");
		
		MouseClickHandler click = new MouseClickHandler(root);
		root.addEventFilter(MouseEvent.MOUSE_CLICKED, click);
		MouseDragHandler drag = new MouseDragHandler(root);
		root.addEventFilter(MouseEvent.MOUSE_DRAGGED, drag);
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();
	}
	
	/**Event handler for mouse clicks
	 * @author !MULLIGANACEOUS!
	 */
	private class MouseClickHandler implements EventHandler<MouseEvent> {
		private ScribblePane root;
		public MouseClickHandler(ScribblePane root) {
			this.root = root;
		}
		@Override
		public void handle(MouseEvent e) {
			System.out.println("(" + e.getSceneX() + "," + e.getSceneY() + ")");
			this.root.setCircle(e.getSceneX(), e.getSceneY());
		}
	}
	
	/**Event handler for dragging
	 * @author !MULLIGANACEOUS!
	 */
	private class MouseDragHandler implements EventHandler<MouseEvent> {
		private ScribblePane root;
		public MouseDragHandler(ScribblePane root) {
			this.root = root;
		}
		@Override
		public void handle(MouseEvent e) {
			this.root.scribble(e.getSceneX(), e.getSceneY());
		}
	}
}
