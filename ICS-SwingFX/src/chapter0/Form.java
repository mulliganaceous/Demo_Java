package chapter0;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**A sample application which describes a form.
 * @author !MULLIGANACEOUS!
 */
public class Form extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello World Form");
		
		// A GridPane layout
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		// Adding text
		Text title = new Text("Welcome");
		title.setFont(Font.font("monospace", FontWeight.NORMAL, 24));
		grid.add(title, 0, 0);
		
		Label userP = new Label("Username");
		grid.add(userP, 0, 1);
		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);
		
		Label labelP = new Label("Password");
		grid.add(labelP, 0, 2);
		TextField passwordTextField = new TextField();
		grid.add(passwordTextField, 1, 2);
		
		Button button = new Button("Sign in");
		button.setOnAction(new ButtonHandler());
		HBox hbPane = new HBox(10);
		hbPane.setAlignment(Pos.BOTTOM_RIGHT);
		hbPane.getChildren().add(button);
		grid.add(hbPane, 1, 4);
		
		primaryStage.setScene(new Scene(grid, 400, 400));
		primaryStage.show();
	}
	
	/**Event handler for the button
	 */
	private class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			System.out.println(event.getSource() + "clicked!");
		}
	}
}
