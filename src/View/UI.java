package View;

import javafx.util.Duration;

import java.awt.Point;
import java.util.Scanner;

import application.MazeController;
import controller.CellController;
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
	private int NUM_ROWS;
	private int NUM_COLUMNS;
	
	CellController controller;
	Scanner userInputScanner = new Scanner(System.in);
	
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
			controller.doOneStep();
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
	
	public void userBoardInput(Scanner userInput) {
		System.out.println("Choose the number of rows: ");
		NUM_ROWS = Integer.parseInt(userInput.nextLine());
		System.out.println("Choose the number of rows: ");
		NUM_COLUMNS = Integer.parseInt(userInput.nextLine());
	}

	public int getNUM_ROWS() {
		return NUM_ROWS;
	}

	public int getNUM_COLUMNS() {
		return NUM_COLUMNS;
	}

	// Start of JavaFX Application
	public void start(Stage stage) {
		controller = new CellController(NUM_ROWS, NUM_COLUMNS, this);
		// Initializing the gui
		myScene = setupScene();
		stage.setScene(myScene);
		stage.setTitle("Cell Sim");
		stage.show();

		// Makes the animation happen.  Will call "step" method repeatedly.
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> controller.step(MILLISECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	
}