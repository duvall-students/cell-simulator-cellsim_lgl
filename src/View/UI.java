package View;

import javafx.util.Duration;

import java.awt.Point;
import java.util.Scanner;

import controller.CellController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
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
	private boolean paused = true;
	private Button pauseButton;
	private int NUM_ROWS = 100;					// Default Value 100
	private int NUM_COLUMNS = 150;				// Default Value 100
	private final int EXTRA_VERTICAL = 100; 	// GUI area allowance when making the scene width
	private final int EXTRA_HORIZONTAL = 150; 	// GUI area allowance when making the scene width
	private final int BLOCK_SIZE = 5;     		// size of each cell in pixels
	
	CellController controller;
	Scanner userInputScanner = new Scanner(System.in);
	
	private Rectangle[][] mirrorCell;
	
	private TextField columnField = new TextField ();
	private TextField rowField = new TextField ();
	
	private Color[] color  = new Color[] {
			Color.BLACK,			// filled cell color
			Color.WHITE,			// empty cell color
	}; 
	
	private HBox setupControlButtons(){
		// Make the controls part
		HBox controls = new HBox();
		controls.setAlignment(Pos.BASELINE_CENTER);
		controls.setSpacing(10);

		Button newSetUpButton = new Button("New Set-Up");
		newSetUpButton.setOnAction(value ->  {
			this.paused = false;
			pressPause();
			controller.newBoard();
		});
		controls.getChildren().add(newSetUpButton);

		pauseButton = new Button("Play");
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
		
		return controls;
	}
	
	private HBox setupTextFields() {
		HBox fields = new HBox();
		
		fields.setAlignment(Pos.BASELINE_CENTER);
		fields.setSpacing(10);
		
		
		HBox rowBox = new HBox();
		rowField.setPromptText("Enter number of rows (Default 100)");
		rowField.setPrefColumnCount(20);
		rowBox.getChildren().addAll(rowField);
		rowBox.setSpacing(15);
		fields.getChildren().add(rowBox);
		
		HBox columnBox = new HBox();
		columnField.setPromptText("Enter number of columns (Default 150)");
		columnField.setPrefColumnCount(20);
		columnBox.getChildren().addAll(columnField);
		columnBox.setSpacing(15);
		fields.getChildren().add(columnBox);
		
		return fields;
	}
	
	public void pressPause(){
		this.paused = !this.paused;
		if(this.paused){
			pauseButton.setText("Play");
		}
		else{
			pauseButton.setText("Pause");
		}
	}
	
	public void userBoardInput(Scanner userInput) {
		// Commented out until functionality for reseting scene is fixed
//		if(rowField.getText() != null && !rowField.getText().isEmpty()
//				&& columnField.getText() != null && !columnField.getText().isEmpty()) {
//			NUM_ROWS = Integer.parseInt(rowField.getText());
//			NUM_COLUMNS = Integer.parseInt(columnField.getText());
//		} else {
//			System.out.println("do nothing");
//		}
		System.out.println("Choose the number of rows: ");
		NUM_ROWS = Integer.parseInt(userInput.nextLine());
		System.out.println("Choose the number of columns: ");
		NUM_COLUMNS = Integer.parseInt(userInput.nextLine());
	}

	// Start of JavaFX Application
	public void start(Stage stage) {
		
		userBoardInput(userInputScanner);
		controller = new CellController(NUM_ROWS, NUM_COLUMNS, this);
		
		// Initializing the gui
		myScene = setupScene();
		stage.setScene(myScene);
		stage.setTitle("Cell Simulator");
		stage.show();
		
		// Makes the animation happen.  Will call "step" method repeatedly.
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(MILLISECOND_DELAY));
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
		HBox fields = setupTextFields();

		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		root.getChildren().addAll(cellDrawing,controls,fields);

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
	
	public void redraw(){
		for(int i = 0; i< mirrorCell.length; i++){
			for(int j =0; j < mirrorCell[i].length; j++){
				mirrorCell[i][j].setFill(color[controller.getCellState(i,j)]);
			}
		}
	}
	
	/*
	 * Does a step in the search only if not paused.
	 */
	public void step(double elapsedTime){
		if(!paused) {
			controller.doOneStep(elapsedTime);
		}
	}
}