package space;

import body.Body;
import datastructure.BodyIndexedList.BodyIndexedIterator;

public class Realspace extends Space {
	private double G = 1;
	
	public Realspace(int dimension) {
		super(dimension);
	}
	
	public void setG(double G) {
		this.G = G;
	}
	
	public Vector weightFrom(Vector x1, Body x2) {
		Vector forceFromBody = Vector.zeroVector(DIMENSION);
		Vector distance = Vector.diff(x2.getPosition(), x1);
		if (Vector.norm2(distance) >= 1E-12) {
			double magnitude = G*1*x2.getMass()/Vector.norm2(distance);
			forceFromBody = Vector.mult(magnitude, Vector.unit(distance));
		}
		return forceFromBody;
	}
	
	public Vector weightAt(Vector x1) {
		BodyIndexedIterator iter0 = this.getBodies().iterator();
		Vector weight = Vector.zeroVector(DIMENSION);
		while (iter0.hasNext()) {
			weight.addBy(weightFrom(x1, iter0.next()));
		}
		return weight;
	}
	
	public void step(double dt) {
		BodyIndexedIterator iter0 = this.getBodies().iterator();
		Body examined;
		while (iter0.hasNext()) {
			examined = iter0.next();
			// Update acceleration, then velocity
			examined.addVelocityByAcceleration(dt);
			examined.resetAcceleration();
			examined.addPositionByVelocity(dt);
		}
		
		// Determine forces of all the bodies. This is a quadratic algorithm.
		BodyIndexedIterator iter1 = this.getBodies().iterator();
		BodyIndexedIterator iter2;
		Body otherbody;	
		while (iter1.hasNext()) {
			examined = iter1.next();
			Vector forceFromBody = Vector.zeroVector(DIMENSION);
			iter2 = iter1.clone();
			// Check all other bodies
			while (iter2.hasNext()) {
				otherbody = iter2.next();
				Vector distance = Vector.diff(otherbody.getPosition(),examined.getPosition());
				
				if (Vector.norm2(distance) <= 0.000001)
					forceFromBody = Vector.zeroVector(DIMENSION);
				else {
					double magnitude = 
							G*examined.getMass()*otherbody.getMass()/
							Vector.norm2(distance);
					forceFromBody = Vector.mult(magnitude, Vector.unit(distance));
				}
				// The weight force contributed is mutual. Determine acceleration.
				examined.addAccelerationBy(Vector.mult(1d/examined.getMass(),forceFromBody));
				otherbody.addAccelerationBy(Vector.mult(-1d/otherbody.getMass(), forceFromBody));
			}	
		}
	}
	
	public String toString() {
		BodyIndexedIterator iter = this.getBodies().iterator();
		StringBuilder sb = new StringBuilder();
		sb.append("This is a " + DIMENSION + " dimensional space with " + this.getBodies().getSize() + " bodies.\n");
		while (iter.hasNext()) {
			sb.append(iter.next());
		}
		return sb.toString();
	}
}
