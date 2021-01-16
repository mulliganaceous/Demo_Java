package body;

import space.Location;
import space.Space;
import space.Vector;

public class Body {
	private String name;
	private double mass;
	private double size;
	private Location loc; // Location
	private Vector v; // Velocity
	private Vector a; // Acceleration
	public Body(String name, double mass, double size) {
		this.name = name;
		this.mass = mass;
		this.size = size;
		this.loc = null;
		this.v = null;
		this.a = null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getMass() {
		return this.mass;
	}
	
	public double getSize() {
		return this.size;
	}
	
	public Location getLocation() {
		return this.loc;
	}
	
	public Vector getPosition() {
		return this.loc.getPosition();
	}
	
	public Vector getVelocity() {
		return this.v;
	}
	
	public Vector getAcceleration() {
		return this.a;
	}
	
	public void setLocation(Space space, Vector x) {
		this.loc = new Location(space, x);
	}
	
	public void setPosition(Vector x) {
		this.loc.setPosition(x);
	}
	
	public void addPositionByVelocity(double dt) {
		this.loc.addPositionBy(Vector.mult(dt,this.v));
	}
	
	public void setVelocity(Vector v) {
		this.v = Vector.zeroVector(this.loc.getSpace().DIMENSION);
		this.v.addBy(v);
	}
	
	public void addVelocityByAcceleration(double dt) {
		this.v.addBy(Vector.mult(dt,this.a));
	}
	
	public void resetAcceleration() {
		this.a = Vector.zeroVector(this.loc.getSpace().DIMENSION);
	}
	
	public void addAccelerationBy(Vector a) {
		this.a.addBy(a);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.name + " ").append('(').append(this.mass + " kg").append(')').append("\n");
		sb.append("x : " + this.getPosition() + "\n");
		sb.append("x': " + this.v + "\n");
		sb.append("x\": " + this.a + "\n");
		return sb.toString();
	}
}
