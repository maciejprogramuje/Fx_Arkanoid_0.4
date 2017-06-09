package pl.maciej.controller;

import java.io.File;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import pl.maciej.main.Game;

public class MainPaneController {
	Game game;
	Button takButton, nextButton, nextLevelButton;
	File file;

	@FXML
	private Rectangle palette;
	@FXML
	private Pane pane;
	@FXML
	private Circle ball;
	@FXML
	private VBox mainPane;
	@FXML
	private Label startLabel;
	@FXML
	private HBox bottomPane;
	@FXML
	private Label pointsLabels;
	@FXML
	private StartPaneController startPaneController;
	@FXML
	private TopScorePaneController topScorePaneController;
	@FXML
	private NextLevelPaneController nextLevelPaneController;

	@FXML
	void initialize() {
		ball.setFill(new ImagePattern(new Image("/pl/maciej/resources/ball.png")));
		palette.setFill(new ImagePattern(new Image("/pl/maciej/resources/palette.png")));
		pointsLabels.setText("0");	
		configureButtons();
		configureKeys();
		takButton.getParent().toFront();
		nextButton.getParent().setVisible(false);
		nextLevelButton.getParent().setVisible(false);
	}

	private void startingSettings() {
		ball.setTranslateX(0);
		ball.setTranslateY(0);
		palette.setTranslateX(0);
		palette.setTranslateY(0);
		pointsLabels.setText("0");
		
		game = new Game(palette, pane, ball, startLabel, pointsLabels, topScorePaneController, takButton, nextLevelPaneController);
	
		takButton.getParent().toFront();
		nextButton.getParent().setVisible(false);	
	}

	private void configureButtons() {
		Button nieButton = startPaneController.getNieButton();
		nieButton.setOnAction(x -> Platform.exit());
		
		takButton = startPaneController.getTakButton();
		takButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				takButton.getParent().setVisible(false);
				startLabel.setOpacity(0.2);
				startingSettings(); 
				game.start();
			}
		});

		nextButton = topScorePaneController.getNextButton();
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				game.getTopScores().saveTopScores(); 
				nextButton.getParent().setVisible(false);
				takButton.getParent().setVisible(true);
				takButton.getParent().toFront();
			}
		});
		
		nextLevelButton = nextLevelPaneController.getNextLevelButton(); /////////////
		nextLevelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				nextLevelButton.getParent().setVisible(false);
				startLabel.setOpacity(0.2);
				ball.setTranslateX(0);
				ball.setTranslateY(0);
				palette.setTranslateX(0);
				palette.setTranslateY(0);
				
				game.start();
			}
		});
	}

	private void configureKeys() {
		palette.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					game.setPaletteDirection(-1);
				} else if (event.getCode() == KeyCode.RIGHT) {
					game.setPaletteDirection(1);
				} 
			}	
		});
	}
}