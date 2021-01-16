package gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class InfoPane extends Pane {
	private Model model;
	private TextField intervalField;
	private Button playButton;
	
	public InfoPane(Model model) {
		super();
		this.model = model;
		this.setStyle("-fx-background-color: BLACK; -fx-border-color: #33ff99; -fx-border-width: 3");
		
		this.intervalField = new TextField();
		this.intervalField.setPrefWidth(6);
		this.intervalField.setLayoutX(16);
		this.intervalField.setLayoutY(16);
		this.getChildren().add(this.intervalField);
		
		this.playButton = new Button("Lapse");
		this.intervalField.setPrefWidth(6);
		this.getChildren().add(this.playButton);
	}
}
