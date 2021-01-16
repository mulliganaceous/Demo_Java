package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import space.Space;

public class SpacePane extends StackPane {
	private Model model;
	private Space space;
	private SpaceMap map;
	
	public SpacePane(Model model) {
		this.model = model;
		this.setStyle("-fx-background-color: #00cc33; -fx-border-color: #33ff99; -fx-border-width: 3");
		this.setMinSize(768, 768);
		
		this.map = new SpaceMap(this.model);
		this.getChildren().add(this.map);
	}
}
