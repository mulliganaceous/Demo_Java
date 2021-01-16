package space;

import java.util.Iterator;

import body.Body;
import datastructure.BodyIndexedList;
import exception.DimensionException;

public abstract class Space {
	public final int DIMENSION;
	private BodyIndexedList bodies;
	
	public Space(int dimension) {
		this.DIMENSION = dimension;
		this.bodies = new BodyIndexedList(new int[] {2,12,24,64});
	}
	
	public abstract void step(double dt);
	
	protected BodyIndexedList getBodies() {
		return this.bodies;
	}
	
	public Iterator<Body> getBodiesIterator() {
		return this.bodies.iterator();
	}
	
	public int addBody(Body body, Vector x, Vector v) {
		if (x.DIMENSION != DIMENSION || v.DIMENSION != DIMENSION)
			throw new DimensionException("Wrong dimension upon giving body initial position and velocity.");
		body.setLocation(this, x);
		body.setVelocity(v);
		body.resetAcceleration();
		this.getBodies().addBody(body);
		return 0;
	}
}
