package View;

import javafx.util.Duration;

import java.awt.Point;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class UI extends Application {

	// Buttons and Text Fields
	
	private final int MILLISECOND_DELAY = 15;	// speed of animation
	
	private Scene myScene;						// the container for the GUI
	private boolean paused = false;		
	private Button pauseButton;
	
	CellController controller;
	
	private HBox setupControlButtons(){
		// Make the controls part
		HBox controls = new HBox();
		controls.setAlignment(Pos.BASELINE_CENTER);
		controls.setSpacing(10);

		Button newSetUpButton = new Button("New Set-Up");
		newSetUpButton.setOnAction(value ->  {
			controller.newBoard();
		});
		controls.getChildren().add(newSetUpButton);

		pauseButton = new Button("Pause");
		pauseButton.setOnAction(value ->  {
			pressPause();
		});
		controls.getChildren().add(pauseButton);

		Button stepButton = new Button("Step");
		stepButton.setOnAction(value ->  {
			controller.doOneStep(MILLISECOND_DELAY);
		});
		controls.getChildren().add(stepButton);
		return controls;
	}
	
	
	public void pressPause(){
		this.paused = !this.paused;
		if(this.paused){
			pauseButton.setText("Resume");
		}
		else{
			pauseButton.setText("Pause");
		}
	}
	
	
}