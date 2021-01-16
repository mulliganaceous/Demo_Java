package chapter2.events;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class ClickableCircle extends Circle {
	private String name;
	public ClickableCircle(String name, double r) {
		super(r);
		this.name = name;
		this.addEventFilter(MouseEvent.MOUSE_CLICKED, new MouseClickHandler());
	}
	
	/**Event handler for mouse clicks
	 * @author !MULLIGANACEOUS!
	 */
	private class MouseClickHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent e) {
			System.out.println(ClickableCircle.this.name);
		}
	}
}
