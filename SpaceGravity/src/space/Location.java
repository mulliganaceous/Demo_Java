package space;

public class Location {
	private Space space;
	private Vector x;
	public Location(Space space, Vector x) {
		this.space = space;
		this.x = x;
	}
	
	public Space getSpace() {
		return this.space;
	}
	
	public Vector getPosition() {
		return this.x;
	}
	
	public void setPosition(Vector x) {
		this.x = x;
	}

	public void addPositionBy(Vector v) {
		this.x.addBy(v);
	}
}
