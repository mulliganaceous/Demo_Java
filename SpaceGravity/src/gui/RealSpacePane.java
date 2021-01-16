package gui;

import javafx.scene.layout.StackPane;

public class RealSpacePane extends StackPane implements ObservableView {
	private Model model;
	private RealSpaceMap map;
	
	private double zoom = 4;
	private double[] originCanvas = new double[] {384, 384};
	
	public RealSpacePane(Model model) {
		this.model = model;
		this.model.setObservableSpacePane(this);
		
		this.setStyle("-fx-background-color: #00cc33; -fx-border-color: #33ff99; -fx-border-width: 3");
		this.setMinSize(768, 768);
		
		this.map = new RealSpaceMap(this, this.model);
		this.getChildren().add(this.map);
	}
	
	public double[] convertCoord(double x, double y) {
		return new double[] {convertX(x), convertY(y)};
	}
	
	public double convertX(double x) {
		return originCanvas[0] + zoom*x;
	}
	
	public double convertY(double y) {
		return originCanvas[1] - zoom*y;
	}
	
	public double convertBackX(double xc) {
		return (double)(xc - originCanvas[0])/zoom;
	}
	
	public double convertBackY(double yc) {
		return -(double)(yc - originCanvas[0])/zoom;
	}
	
	public double getZoom() {
		return this.zoom;
	}
	
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}
	
	public double[] getOrigin() {
		return this.originCanvas;
	}
	
	public void setOrigin(double x, double y) {
		this.originCanvas = new double[] {x, y};
	}
	
	public void addOriginBy(double dx, double dy) {
		this.originCanvas[0] += dx;
		this.originCanvas[1] += dy;
	}

	@Override
	public void update() {
		this.map.guiUpdate();
	}
}
