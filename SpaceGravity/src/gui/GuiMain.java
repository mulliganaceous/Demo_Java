package gui;

import body.Body;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import space.Realspace;
import space.Vector;

public class GuiMain extends Application {
	public static int INITIALMODE = 24;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Model model = Model.newInstance(new Realspace(2));
		this.addInitialBodies(model);
		
		HBox root = new HBox();
		Scene scene = new Scene(root, 1024, 768);
		scene.getStylesheets().add("luminescent.ccs");

		RealSpacePane spacepane = new RealSpacePane(model);
		spacepane.relocate(0, 0);
		root.getChildren().add(spacepane);

		InfoPane infopane = new InfoPane(model, spacepane);
		infopane.setPrefSize(256, 768);
		infopane.relocate(768, 0);
		root.getChildren().add(infopane);

		primaryStage.setTitle("SpacePane");
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);

		primaryStage.sizeToScene();
		primaryStage.show();
		primaryStage.setMinHeight(primaryStage.getHeight());
		primaryStage.setMinWidth(primaryStage.getWidth());

		model.update();
		System.out.println(model.getSpace());
	}

	public void addInitialBodies(Model model) {
		if (INITIALMODE == 0) {
			model.getSpace().addBody(new Body("Alpha", 1, 0), new Vector(0,0), new Vector(0,0));
			model.getSpace().addBody(new Body("Beta ", 1, 0), new Vector(2,0), new Vector(0,0));
			model.getSpace().addBody(new Body("Gamma", 1, 0), new Vector(0,2), new Vector(0,0));
		}
		else {
			for (int k = 0; k < INITIALMODE; k++) {
				double m = (Math.random() + 0.01)*4;
				double x = Math.random()*96 - 48;
				double y = Math.random()*96 - 48;
				double dx = Math.random()*0.5-0.25;
				double dy = Math.random()*0.5-0.25;
				model.getSpace().addBody(new Body("#" + k, m, Math.pow(m, 1.0/2)), new Vector(x,y), new Vector(dx,dy));
			}
		}
	}
}
