package space;

import body.Body;
import datastructure.BodyIndexedList.BodyIndexedIterator;

public class Realspace extends Space {
	private double G = 2;
	
	public Realspace(int dimension) {
		super(dimension);
	}
	
	public void setG(double G) {
		this.G = G;
	}
	
	public void step(double dt) {
		BodyIndexedIterator iter0 = this.getBodies().iterator();
		Body examined;
		while (iter0.hasNext()) {
			examined = iter0.next();
			examined.addPositionByVelocity(dt);
			// Update velocity, then acceleration
			examined.addVelocityByAcceleration(dt);
			examined.resetAcceleration();
		}
		
		// Determine accelerations of all the bodies. This is a quadratic algorithm.
		BodyIndexedIterator iter1 = this.getBodies().iterator();
		BodyIndexedIterator iter2;
		Body otherbody;	
		while (iter1.hasNext()) {
			examined = iter1.next();
			Vector aFromOtherBody = Vector.zeroVector(DIMENSION);
			iter2 = iter1.clone();
			// Check all other bodies
			while (iter2.hasNext()) {
				otherbody = iter2.next();
				Vector distance = Vector.diff(otherbody.getPosition(),examined.getPosition());
				
				if (Vector.norm2(distance) <= 0.000001)
					aFromOtherBody = Vector.zeroVector(DIMENSION);
				else {
					double magnitude = 
							G*examined.getMass()*otherbody.getMass()/
							Vector.norm2(distance);
					aFromOtherBody = Vector.mult(magnitude, Vector.unit(distance));
				}
				// The acceleration force contributed is mutual
				examined.addAccelerationBy(aFromOtherBody);
				otherbody.addAccelerationBy(Vector.mult(-1, aFromOtherBody));
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
