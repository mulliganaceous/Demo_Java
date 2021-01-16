package gui;

import java.util.Iterator;

import body.Body;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import space.Space;
import space.Vector;

public class RealSpaceMap extends Canvas {
	private final RealSpacePane spacepane;
	private final Model model;
	private final static int POINTSIZE = 16;
	
	public RealSpaceMap(RealSpacePane spacepane, Model model) {
		super(750, 750);
		this.spacepane = spacepane;
		this.model = model;
		this.addEventFilter(MouseEvent.ANY, new PaneNavigationHandler());
		this.addEventFilter(ScrollEvent.SCROLL, new ZoomNavigationHandler());
	}
	
	public void guiUpdate() {
		/* DRAW BACKGROUND */
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		/* DRAW GRIDS */
		gc.setStroke(Color.GRAY);
		gc.setLineWidth(1);
		gc.strokeOval(spacepane.convertX(-1), spacepane.convertY(1), spacepane.getZoom()*2, spacepane.getZoom()*2);
		gc.strokeLine(0, spacepane.convertY(0), this.spacepane.getWidth(), spacepane.convertY(0));
		gc.strokeLine(spacepane.convertX(0), 0, spacepane.convertX(0), this.spacepane.getHeight());
		
		/* DRAW BODIES */
		Space space = model.getSpace();
		Iterator<Body> iter = space.getBodiesIterator();
		while (iter.hasNext()) {
			Body body = iter.next();
			
			// Draw Body
			gc.setStroke(Color.WHITE);
			gc.setFill(Color.WHITE);
			double x = body.getPosition(0);
			double y = body.getPosition(1);
			if (body.getSize() == 0) {
				gc.strokeOval(
						spacepane.convertX(x) - POINTSIZE/2, 
						spacepane.convertY(y) - POINTSIZE/2, 
						POINTSIZE, POINTSIZE);
			}
			else {
				double apparentSize = spacepane.getZoom()*body.getSize();
				gc.fillOval(
						spacepane.convertX(x) - apparentSize/2, 
						spacepane.convertY(y) - apparentSize/2, 
						apparentSize, apparentSize);
			}
			
			// Draw Velocity
			gc.setStroke(Color.GREEN);
			gc.setLineWidth(2);
			Vector v = body.getVelocity();
			double dx = v.entry(0)*Math.pow(Vector.abs(v),-0.5);
			double dy = v.entry(1)*Math.pow(Vector.abs(v),-0.5);
			gc.strokeLine(
					spacepane.convertX(x), spacepane.convertY(y), 
					spacepane.convertX(x + dx), spacepane.convertY(y + dy));
			
			// Draw Gravitational Acceleration
			gc.setStroke(Color.TEAL);
			gc.setLineWidth(1);
			Vector a = body.getAcceleration();

			double ddx = a.entry(0);
			double ddy = a.entry(1);
			if (Vector.norm2(a) < 0.25 && Vector.norm2(a) > 1E-6) {
				ddx = a.entry(0)*Math.pow(Vector.abs(a),-1)/2;
				ddy = a.entry(1)*Math.pow(Vector.abs(a),-1)/2;
			}

			gc.strokeLine(
					spacepane.convertX(x + dx), spacepane.convertY(y + dy), 
					spacepane.convertX(x + dx + ddx), spacepane.convertY(y + dy + ddy));
		}
	}
	
	private class PaneNavigationHandler implements EventHandler<MouseEvent> {
		private double x, y;
		private PaneNavigationHandler() {
			this.x = Double.NaN;
			this.y = Double.NaN;
		}
		
		@Override
		public void handle(MouseEvent ev) {
			if (ev.getEventType() == MouseEvent.MOUSE_DRAGGED) {
				double x = ev.getX();
				double y = ev.getY();
				if (!Double.isNaN(this.x) && !Double.isNaN(this.y)) {
					double dx = x - this.x;
					double dy = y - this.y;
					RealSpaceMap.this.spacepane.addOriginBy(dx, dy);
					RealSpaceMap.this.guiUpdate();
				} else {System.out.println(x + " " + y );}
				this.x = x;
				this.y = y;
			}
			if (ev.getEventType() == MouseEvent.MOUSE_RELEASED) {
				this.x = Double.NaN;
				this.y = Double.NaN;
			}
			if (ev.getEventType() == MouseEvent.MOUSE_MOVED) {
				GraphicsContext gc = RealSpaceMap.this.getGraphicsContext2D();
				guiUpdate(); // TODO Improve live 
				gc.setFont(new Font("monospace", 10));
				gc.setFill(Luminescent.GITD);
				gc.fillText("[" + spacepane.convertBackX(ev.getX()) + "," + spacepane.convertBackY(ev.getY()) + ")]"
							, ev.getX(), ev.getY());
			}
			if (ev.getEventType() == MouseEvent.MOUSE_EXITED) {
				guiUpdate();
			}
		}
	}
	
	private class ZoomNavigationHandler implements EventHandler<ScrollEvent> {
		@Override
		public void handle(ScrollEvent ev) {
			if (ev.getDeltaY() > 0) {
				double zoom = RealSpaceMap.this.spacepane.getZoom();
				RealSpaceMap.this.spacepane.setZoom(zoom * 2);
			}
			if (ev.getDeltaY() < 0) {
				double zoom = RealSpaceMap.this.spacepane.getZoom();
				RealSpaceMap.this.spacepane.setZoom(zoom / 2);
			}
			RealSpaceMap.this.guiUpdate();
		}
	}
}
