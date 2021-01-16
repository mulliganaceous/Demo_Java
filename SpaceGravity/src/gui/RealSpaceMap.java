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
	private double xP = Double.NaN;
	private double yP = Double.NaN;
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
		}
		
		iter = space.getBodiesIterator();
		while (iter.hasNext()) {
			Body body = iter.next();
			Vector x = body.getPosition();

			// Draw Velocity
			gc.setStroke(Color.GREEN);
			gc.setLineWidth(2);
			Vector v = body.getVelocity();
			double dx = v.entry(0)*Math.pow(Vector.abs(v),-0.5);
			double dy = v.entry(1)*Math.pow(Vector.abs(v),-0.5);
			gc.strokeLine(
					spacepane.convertX(x.entry(0)), spacepane.convertY(x.entry(1)), 
					spacepane.convertX(x.entry(0) + dx), spacepane.convertY(x.entry(1) + dy));
			
			// Draw Gravitational Acceleration
			gc.setStroke(Color.TEAL);
			gc.setLineWidth(1);
			Vector a = body.getAcceleration();

			double ddx = a.entry(0);
			double ddy = a.entry(1);
			if (Vector.norm2(a) < 0.25 && Vector.norm2(a) > 1E-06) {
				ddx = a.entry(0)*Math.pow(Vector.abs(a),-1)/2;
				ddy = a.entry(1)*Math.pow(Vector.abs(a),-1)/2;
			}

			gc.strokeLine(
					spacepane.convertX(x.entry(0) + dx), spacepane.convertY(x.entry(1) + dy), 
					spacepane.convertX(x.entry(0) + dx + ddx), spacepane.convertY(x.entry(1) + dy + ddy));
		}
	}
	
	public void guiCursor(double xP, double yP) {
		GraphicsContext gc = RealSpaceMap.this.getGraphicsContext2D();
		gc.setFont(new Font("monospace", 10));
		gc.setFill(Luminescent.GITD);
		gc.fillText("[" + spacepane.convertBackX(xP) + "," + spacepane.convertBackY(yP) + ")]"
					, xP, yP);
		
		// Draw Gravitational Acceleration
		gc.setStroke(Color.TEAL);
		gc.setLineWidth(2);
		Vector x = new Vector(spacepane.convertBackX(xP), spacepane.convertBackY(yP));
		Vector a = this.model.getSpace().weightAt(x);
		double ddx = a.entry(0);
		double ddy = a.entry(1);
		if (Vector.norm2(a) < 0.25) {
			ddx = a.entry(0)*Math.pow(Vector.abs(a),-1)/2;
			ddy = a.entry(1)*Math.pow(Vector.abs(a),-1)/2;
		}
		gc.strokeLine(
				spacepane.convertX(x.entry(0)), spacepane.convertY(x.entry(1)), 
				spacepane.convertX(x.entry(0) + ddx), spacepane.convertY(x.entry(1) + ddy));
	}
	
	private class PaneNavigationHandler implements EventHandler<MouseEvent> {
		private double x, y;
		private PaneNavigationHandler() {
			this.x = Double.NaN;
			this.y = Double.NaN;
		}
		
		@Override
		public void handle(MouseEvent ev) {
			xP = ev.getX();
			yP = ev.getY();
			if (ev.getEventType() == MouseEvent.MOUSE_DRAGGED) {
				if (!Double.isNaN(this.x) && !Double.isNaN(this.y)) {
					double dx = xP - this.x;
					double dy = yP - this.y;
					RealSpaceMap.this.spacepane.addOriginBy(dx, dy);
					RealSpaceMap.this.model.update();
				} else {}
				this.x = xP;
				this.y = yP;
			}
			if (ev.getEventType() == MouseEvent.MOUSE_RELEASED) {
				this.x = Double.NaN;
				this.y = Double.NaN;
			}
			if (ev.getEventType() == MouseEvent.MOUSE_MOVED) {
				RealSpaceMap.this.model.update();
				guiCursor(xP, yP);
			}
			if (ev.getEventType() == MouseEvent.MOUSE_EXITED) {
				RealSpaceMap.this.model.update();
			}
		}
	}
	
	private class ZoomNavigationHandler implements EventHandler<ScrollEvent> {
		@Override
		public void handle(ScrollEvent ev) {
			double dxO = spacepane.getOrigin()[0] - xP;
			double dyO = spacepane.getOrigin()[1] - yP;
			if (ev.getDeltaY() > 0) {
				double zoom = RealSpaceMap.this.spacepane.getZoom();
				RealSpaceMap.this.spacepane.setZoom(zoom * 2);
				RealSpaceMap.this.spacepane.setOrigin(xP + dxO*2, yP + dyO*2);
			}
			if (ev.getDeltaY() < 0) {
				double zoom = RealSpaceMap.this.spacepane.getZoom();
				RealSpaceMap.this.spacepane.setZoom(zoom / 2);
				RealSpaceMap.this.spacepane.setOrigin(xP + dxO/2, yP + dyO/2);
			}
			RealSpaceMap.this.model.update();
		}
	}
}
