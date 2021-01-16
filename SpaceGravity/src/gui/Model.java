package gui;

import space.Space;

public class Model {
	private static Model instance;
	private Space space;
	
	// Observed
	private ObservableView observableSpacePane;
	private ObservableView observableInfoPane;
	
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
	
	public void setObservableSpacePane(RealSpacePane spacepane) {
		this.observableSpacePane = spacepane;
	}
	
	public void setObservableInfoPane(RealSpacePane spacepane) {
		this.observableInfoPane = spacepane;
	}
	
	public int step(double dt) {
		this.space.step(dt);
		return this.update();
	}
	
	public int update() {
		if (this.observableSpacePane != null)
			this.observableSpacePane.update();
		if (this.observableInfoPane != null)
			this.observableInfoPane.update();
		return 0;
	}
}