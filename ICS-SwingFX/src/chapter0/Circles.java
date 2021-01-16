package chapter0;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.stage.*;

/**A JavaFX Program that plots circles.
 * @author mulliganaceous
 */
public class Circles extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	/**Set up GUI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello, World!");
		Group root = new Group();
		
		// Add a new group called circles, and put that to the root group
		Group circles = new Group();
		for (int i = 0; i < 127; i++) {
		   Circle c = new Circle(10);
		   c.setStrokeType(StrokeType.OUTSIDE);
		   c.setStroke(Color.web("white", 1));
		   c.setStrokeWidth(2);
		   c.setCenterX(Math.random() * 480);
		   c.setCenterY(Math.random() * 360);
		   c.setFill(Color.hsb(Math.random()*255, 1, 1));
		   circles.getChildren().add(c);
		}
		root.getChildren().add(circles);
		
		primaryStage.setScene(new Scene(root, 480, 360, Color.BLACK));
		primaryStage.show();
	}
}
