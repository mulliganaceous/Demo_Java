package gui;

import space.Space;

public class Model {
	private static Model instance;
	private Space space;
	
	private Model(Space space) {
		this.space = space;
	}
	
	public static Model newInstance(Space space) {
		Model.instance = new Model(space);
		return Model.instance;
	}
	
	public static Model getInstance() {
		return Model.instance;
	}
	
	public Space getSpace() {
		return this.space;
	}
}