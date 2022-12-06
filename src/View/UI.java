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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	private final int EXTRA_VERTICAL = 100; 	// GUI area allowance when making the scene height
	private final int EXTRA_HORIZONTAL = 150; 	// GUI area allowance when making the scene width
	private final int BLOCK_SIZE = 5;     		// size of each cell in pixels
	
	CellController controller;
	Scanner userInputScanner = new Scanner(System.in);
	
	private Rectangle[][] mirrorCell;
	
	private Color[] color  = new Color[] {
			Color.BLACK,			// filled cell color
			Color.WHITE				// empty cell color
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
			controller.doOneStep(MILLISECOND_DELAY);
		});
		controls.getChildren().add(stepButton);
		
		Button saveButton = new Button("Save");
		saveButton.setOnAction(value ->  {
			controller.saveBoard();
		});
		controls.getChildren().add(saveButton);
		
		Button loadButton = new Button("Load");
		loadButton.setOnAction(value ->  {
			controller.loadBoard();
		});
		controls.getChildren().add(loadButton);
		
		Label rowLabel = new Label("Row:");
		TextField rowField = new TextField ();
		HBox rowBox = new HBox();
		rowBox.getChildren().addAll(rowLabel, rowField);
		rowBox.setSpacing(5);
		controls.getChildren().add(rowBox);
		
		Label columnLabel = new Label("Columns:");
		TextField columnField = new TextField ();
		HBox columnBox = new HBox();
		columnBox.getChildren().addAll(columnLabel, columnField);
		columnBox.setSpacing(15);
		controls.getChildren().add(columnBox);
		
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
	
	// Gets the desired size of board
	public void userBoardInput(Scanner userInput) {
		System.out.println("Choose the number of rows: ");
		NUM_ROWS = Integer.parseInt(userInput.nextLine());
		System.out.println("Choose the number of columns: ");
		NUM_COLUMNS = Integer.parseInt(userInput.nextLine());
	}

	// Start of JavaFX Application
	public void start(Stage stage) {
		userBoardInput(userInputScanner);
		controller = new CellController(NUM_ROWS, NUM_COLUMNS, this);
		
		// Initializing the GUI
		myScene = setupScene();
		stage.setScene(myScene);
		stage.setTitle("Cell Sim");
		stage.show();
		
		// Makes the animation happen.  Will call "step" method repeatedly.
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(MILLISECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	// Create the scene - Controls and Life areas
	private Scene setupScene () {
		// Make three containers
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
				rect.setFill(color[controller.getCellState(i,j)]);
				mirrorCell[i][j] = rect;
				drawing.getChildren().add(rect);
			}	
		}
		return drawing;
	}
	
	// Updates board visuals
	public void redraw(){
		for(int i = 0; i< mirrorCell.length; i++){
			for(int j =0; j < mirrorCell[i].length; j++){
				mirrorCell[i][j].setFill(color[controller.getCellState(i,j)]);
			}
		}
	}
	
	// Does a step in the search only if not paused.
	public void step(double elapsedTime){
		if(!paused) {
			controller.doOneStep(elapsedTime);
		}
	}
}