package gui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class InfoPane extends Pane implements ObservableView {
	private Model model;
	private ObservableView view;
	private TextField intervalField;
	private Button playButton;
	private static double DEFAULTSTEP = 30;

	public InfoPane(Model model, ObservableView view) {
		super();
		this.model = model;
		this.model.setObservableInfoPane(this);
		this.view = view;
		this.setStyle("-fx-background-color: BLACK; -fx-border-color: #33ff99; -fx-border-width: 3");

		this.intervalField = new TextField();
		this.intervalField.setPrefWidth(168);
		this.intervalField.setLayoutX(28);
		this.intervalField.setLayoutY(32);
		this.intervalField.setMinHeight(48);
		this.intervalField.setMaxHeight(48);
		Label intervalLabel = new Label("/s");
		intervalLabel.setLayoutX(200);
		intervalLabel.setLayoutY(32);
		this.getChildren().add(this.intervalField);
		this.getChildren().add(intervalLabel);

		this.playButton = new Button("Step");
		this.playButton.setPrefWidth(200);
		this.playButton.setLayoutX(28);
		this.playButton.setLayoutY(96);
		this.playButton.setPrefHeight(32);
		this.playButton.setOnAction(new StepHandler());
		this.getChildren().add(this.playButton);

		this.intervalField = new TextField(((RealSpacePane) view).getZoom() + "×; "
				+ ((RealSpacePane) view).getOrigin()[0] + "," + ((RealSpacePane) view).getOrigin()[1]);
		this.intervalField.setPrefWidth(200);
		this.intervalField.setLayoutX(28);
		this.intervalField.setLayoutY(160);
		this.intervalField.setMinHeight(48);
		this.intervalField.setMaxHeight(48);
		this.getChildren().add(this.intervalField);
	}

	private class StepHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			try {
				double dt = Double.parseDouble(InfoPane.this.intervalField.getText());
				InfoPane.this.model.step(dt);
			} catch (NumberFormatException e) {
				TimerTask task = new AnimationTask();
				Timer timer = new Timer(true);
				timer.scheduleAtFixedRate(task, 0, (long) (1000f / DEFAULTSTEP));

				InfoPane.this.model.step(1.0 / DEFAULTSTEP);
				System.out.println(InfoPane.this.model.getSpace());
			}
		}
	}

	/**
	 * Perform animation on the canvas
	 * 
	 * @author mulliganaceous
	 */
	private class AnimationTask extends TimerTask {
		@Override
		public void run() {
			System.out.println("t");
			InfoPane.this.model.step(1.0 / DEFAULTSTEP);
		}
	}

	@Override
	public void update() {
		this.intervalField.setText(((RealSpacePane) view).getZoom() + "×; "
				+ String.format("(%.1f,%.1f)",
						((RealSpacePane) view).convertBackX(((RealSpacePane) view).getWidth() / 2),
						((RealSpacePane) view).convertBackY(((RealSpacePane) view).getHeight() / 2)));
	}
}
