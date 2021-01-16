package chapter2.events;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.*;

/**A Hello World JavaFX program.
 * @author mulliganaceous
 */
public class CircleClicker extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	/**Set up GUI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello, World!");
		
		StackPane root = new StackPane();
		root.setStyle("-fx-background-color: BLACK; -fx-border-color: SILVER; -fx-border-width: 16");
		Group clickables = new Group();
		final int N = 12;
		for (int k = 0; k < N; k++) {
			ClickableCircle circle = new ClickableCircle("#" + k, 16);
			circle.setFill(Color.hsb((double)k/N*360.0, 1, 1));
			circle.setCenterX(250 + 150*Math.cos(2*Math.PI*k/N));
			circle.setCenterY(250 - 150*Math.sin(2*Math.PI*k/N));
			clickables.getChildren().add(circle);
		}
		root.getChildren().add(clickables);
		
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();
	}
}

