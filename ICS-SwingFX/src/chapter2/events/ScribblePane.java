package chapter2.events;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class ScribblePane extends Pane {
	private Group clickGroup;
	private Group dragGroup;
	private Circle circle;
	public ScribblePane() {
		super();
		this.clickGroup = new Group();
		this.dragGroup = new Group();
		// Add the clickGroup and dragGroup just once.
		this.getChildren().add(clickGroup);
		this.getChildren().add(dragGroup);
	}
	
	public void setCircle(double x, double y) {
		if (this.circle == null) {
			this.circle = new Circle(16);
			this.circle.setStrokeType(StrokeType.OUTSIDE);
			this.circle.setStrokeWidth(4);
			this.circle.setFill(Color.TRANSPARENT);
			this.clickGroup.getChildren().add(this.circle);
		}
		
		this.circle.setCenterX(x);
		this.circle.setCenterY(y);
		this.circle.setStroke(Color.hsb(Math.random() * 255, 1, 1));
	}
	
	public void scribble(double x, double y) {
		Circle c = new Circle(4);
		c.setFill(Color.WHITE);
		c.setCenterX(x);
		c.setCenterY(y);
		this.dragGroup.getChildren().add(c);
	}
}
