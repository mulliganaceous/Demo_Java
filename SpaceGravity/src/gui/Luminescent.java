package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public interface Luminescent {
	Color GITD = Color.rgb(51,255,153);
	Color GITD2 = Color.rgb(0,204,51);
	BackgroundFill TEXTFIELDFILL = 
			new BackgroundFill(Luminescent.GITD2, CornerRadii.EMPTY, Insets.EMPTY);
	BackgroundFill BUTTONFILL = 
			new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
}
