package View;

import javafx.util.Duration;

import java.awt.Point;
import java.util.Scanner;

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
	private final int EXTRA_VERTICAL = 100; 	// GUI area allowance when making the scene width
	private final int EXTRA_HORIZONTAL = 150; 	// GUI area allowance when making the scene width
	private final int BLOCK_SIZE = 12;     		// size of each cell in pixels
	
	CellController controller;
	Scanner userInputScanner = new Scanner(System.in);
	
	private Rectangle[][] mirrorCell;
	
	private Color[] color  = new Color[] {
			Color.rgb(200,0,0),		// wall color
			Color.rgb(128,128,255),	// path color
			Color.WHITE,			// empty cell color
			Color.rgb(200,200,200)	// visited cell color
	}; 
	
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

	// Start of JavaFX Application
	public void start(Stage stage) {
		controller = new CellController(NUM_ROWS, NUM_COLUMNS, this);
		// Initializing the gui
		myScene = setupScene();
		stage.setScene(myScene);
		stage.setTitle("Cell Sim");
		stage.show();

		// Makes the animation happen.  Will call "step" method repeatedly.
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> controller.step());
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	// Create the scene - Controls and Cell areas
	private Scene setupScene () {
		// Make three container
		Group cellDrawing = setupGame();
		HBox controls = setupControlButtons();

		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		root.getChildren().addAll(cellDrawing,controls);

		Scene scene = new Scene(root, NUM_COLUMNS*BLOCK_SIZE+ EXTRA_HORIZONTAL, 
				NUM_ROWS*BLOCK_SIZE + EXTRA_VERTICAL, Color.ANTIQUEWHITE);

		return scene;
	}

	private Group setupGame(){
		Group drawing = new Group();
		mirrorCell = new Rectangle[NUM_ROWS][NUM_COLUMNS];
		for(int i = 0; i< NUM_ROWS; i++){
			for(int j =0; j < NUM_COLUMNS; j++){
				Rectangle rect = new Rectangle(j*BLOCK_SIZE, i*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
//				rect.setFill(color[controller.getCellState(new Point(i,j))]);
				mirrorCell[i][j] = rect;
				drawing.getChildren().add(rect);
			}	
		}
		return drawing;
	}
	
//	public static void main(String[] args) {
//		launch(args);
//	}
}