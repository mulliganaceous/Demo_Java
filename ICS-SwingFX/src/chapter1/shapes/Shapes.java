package chapter1.shapes;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.*;

/**A JavaFX Program that plots circles.
 * @author mulliganaceous
 */
public class Shapes extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	/**Set up GUI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		final int WIDTH = 480;
		final int HEIGHT = 360;
		primaryStage.setTitle("Hello, World!");
		Group root = new Group();
		
		// Add a new group containing shapes
		Group shapes = new Group();
		
		Circle circle = new Circle(100);
		circle.setCenterX(WIDTH/2);
		circle.setCenterY(HEIGHT/2);
		circle.setStroke(Color.hsb(Math.random()*255, 1, 1));
		circle.setStrokeWidth(4);
		shapes.getChildren().add(circle);
		
		Rectangle rectangle = new Rectangle(50, 50);
		rectangle.setX(10);
		rectangle.setY(10);
		rectangle.setFill(Color.WHITE);
		shapes.getChildren().add(rectangle);
		
		Rectangle rounded = new Rectangle(150, 150);
		rounded.setX(75);
		rounded.setY(75);
		rounded.setStroke(Color.hsb(Math.random()*255, 0.75, 1));
		rounded.setFill(Color.TRANSPARENT);
		rounded.setStrokeWidth(4);
		rounded.setArcWidth(10);
		rounded.setArcHeight(10);
		shapes.getChildren().add(rounded);
		
		Ellipse ellipse = new Ellipse(100,50);
		ellipse.setCenterX(WIDTH/2);
		ellipse.setCenterY(HEIGHT/2);
		ellipse.setFill(Color.hsb(Math.random()*255, 1, 1));
		shapes.getChildren().add(ellipse);
		
		Double[] points = new Double[10];
		for (int k = 0; k < 10; k += 2) {
			points[k] = 400 + 32*(double)Math.cos(Math.PI*2*k/5);
			points[k+1] = 300 + 32*(double)Math.sin(Math.PI*2*k/5);
		}
		
		Polygon star = new Polygon();
		star.getPoints().addAll(points);
		star.setStroke(Color.hsb(Math.random()*255, 1, 1));
		star.setStrokeWidth(4);
		shapes.getChildren().add(star);
		
		root.getChildren().add(shapes);
		
		primaryStage.setScene(new Scene(root, WIDTH, HEIGHT, Color.BLACK));
		primaryStage.show();
	}
}
