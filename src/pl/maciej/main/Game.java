package pl.maciej.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pl.maciej.controller.NextLevelPaneController;
import pl.maciej.controller.TopScorePaneController;

public class Game {
	int dy, dx, numOfBoxesInRows, numOfBoxesInColumns, numOfLevel, numOfLevels, paletteDirection;
	Integer points, maxTopScoreForLevel;

	Pane pane;
	Circle ball;
	Rectangle palette;
	Label startLabel, pointsLabel;
	Button takButton, nextLevelButton;

	ArrayList<Rectangle> boxArr;

	Timeline timelineBall, timelinePalette;

	Bounds bounds;
	Collisions collisions;
	TopScores topScores;
	Levels levels;

	TopScorePaneController topScorePaneController;
	NextLevelPaneController nextLevelPaneController;

	public Game(Rectangle palette, Pane pane, Circle ball, Label startLabel, Label pointsLabel,
			TopScorePaneController topScorePaneController, Button takButton,
			NextLevelPaneController nextLevelPaneController) {
		this.pane = pane;
		this.ball = ball;
		this.palette = palette;
		this.startLabel = startLabel;
		this.pointsLabel = pointsLabel;
		this.topScorePaneController = topScorePaneController;
		this.takButton = takButton;
		this.nextLevelPaneController = nextLevelPaneController;

		points = 0;
		numOfBoxesInRows = 13;
		numOfBoxesInColumns = 8;
		paletteDirection = 0;
		numOfLevel = 0;

		boxArr = new ArrayList<>();
		bounds = new Bounds(palette, pane, ball);
		collisions = new Collisions();
		levels = new Levels(numOfBoxesInColumns, numOfBoxesInRows);
		numOfLevels = levels.getNumOfLevels();

		createOrLoadTopScoreFile();
		configureBallMove();
		configurePaletteMove();
		getBounds().calculatePaneBounds();

		// palette.setLayoutX(2);
		// palette.setWidth(600);
		palette.setWidth(Main.PALETTE_WIDTH);
	}

	public TopScores getTopScores() {
		return topScores;
	}

	public Bounds getBounds() {
		return bounds;
	}

	public void setPaletteDirection(int paletteDirection) {
		this.paletteDirection = paletteDirection;
	}

	private void createOrLoadTopScoreFile() {
		File file = new File("topScoreFile.obj");
		if (!file.exists()) {
			try {
				file.createNewFile();
				topScores = new TopScores(topScorePaneController.getNameLabelArr(),
						topScorePaneController.getNameTextField(), topScorePaneController.getScoreLabel());
				BufferedWriter bw = new BufferedWriter(new FileWriter("topScoreFile.obj"));
				for (int i = 0; i < topScorePaneController.getNameLabelArr().size(); i++) {
					bw.write(topScorePaneController.getScoreLabel().get(i).getText() + " "
							+ topScorePaneController.getNameLabelArr().get(i).getText() + "\n");
				}
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String[] tmp = new String[15];
			try {
				BufferedReader br = new BufferedReader(new FileReader("topScoreFile.obj"));

				for (int i = 0; i < topScorePaneController.getNameLabelArr().size(); i++) {
					try {
						tmp = br.readLine().split(" ");
						topScorePaneController.getScoreLabel().get(i).setText(tmp[0]);
						topScorePaneController.getNameLabelArr().get(i).setText(tmp[1]);
						topScorePaneController.getNameTextField().get(i).setText(tmp[1]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					topScores = new TopScores(topScorePaneController.getNameLabelArr(),
							topScorePaneController.getNameTextField(), topScorePaneController.getScoreLabel());
				}
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private void configureBallMove() {
		timelineBall = new Timeline();
		timelineBall.setCycleCount(Timeline.INDEFINITE);
		Duration duration = Duration.seconds(Main.BALL_SPEED);
		EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bounds.calculateBallBounds();
				collisionsCheck();
				ballMoving();
			}
		};
		KeyFrame keyFrame = new KeyFrame(duration, onFinished);
		timelineBall.getKeyFrames().add(keyFrame);
	}

	private void configurePaletteMove() {
		timelinePalette = new Timeline();
		timelinePalette.setCycleCount(Timeline.INDEFINITE);
		Duration duration = Duration.seconds(Main.PALETTE_SPEED);
		EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (palette.getBoundsInParent().getMinX() > 0
						&& palette.getBoundsInParent().getMaxX() < bounds.paneRight) {
					palette.setTranslateX(palette.getTranslateX() + paletteDirection);
				} else {
					paletteDirection *= -1;
					palette.setTranslateX(palette.getTranslateX() + paletteDirection);
				}
				bounds.calculatePaletteBounds();
			}
		};
		KeyFrame keyFrame = new KeyFrame(duration, onFinished);
		timelinePalette.getKeyFrames().add(keyFrame);
	}

	private void collisionsCheck() {
		for (int i = 0; i < boxArr.size(); i++) {
			if (!boxArr.get(i).getFill().equals(Color.GRAY)) {
				if (collisions.ballTopVsBox(bounds, boxArr.get(i)) || collisions.ballDownVsBox(bounds, boxArr.get(i))) {
					dy *= -1;
					collisionResult(boxArr.get(i));
				} else if (collisions.ballRightVsBox(bounds, boxArr.get(i))
						|| collisions.ballLeftVsBox(bounds, boxArr.get(i))) {
					dx *= -1;
					collisionResult(boxArr.get(i));
				}
			}
		}
	}

	private void collisionResult(Rectangle r) {
		r.setFill(Color.GRAY);
		points++;
		pointsLabel.setText(points.toString());

		if (points == maxTopScoreForLevel) {
			numOfLevel++;
			if (numOfLevel == numOfLevels) {
				endGameFault();
			} else {
				nextLevel();
			}
		}
	}

	private void ballMoving() {
		if (collisions.ballVsTopOfPalette(bounds)) {
			ball.setLayoutX(ball.getLayoutX() + paletteDirection * 2);
			ball.setTranslateY(0);
			dy *= -1;
			//System.out.println("srodek");
		} else if (collisions.ballVsLeftOfPalette(bounds) || collisions.ballVsRightOfPalette(bounds)) {
			if((paletteDirection == 1 && dx == -1) || (paletteDirection == -1 && dx == 1)) {
				dy *= -1;
				dx *= -1;
				paletteDirection *= -1;
				//System.out.println("zderzenie");
			}
		} else if (collisions.ballTopVsPane(bounds)) {
			dy *= -1;
		} else if (collisions.ballRightVsPane(bounds) || collisions.ballLeftVsPane(bounds)) {
			dx *= -1;
		} else if (collisions.ballDownVsPane(bounds)) {
			endGameFault();
		}
		ball.setTranslateX(ball.getTranslateX() + dx);
		ball.setTranslateY(ball.getTranslateY() + dy);
	}

	public void start() {
		dx = -1;
		dy = -1;
		configureBoxes();
		ball.toFront();
		timelineBall.play();
		timelinePalette.play();
	}

	private void nextLevel() {
		nextLevelPaneController.getNextLevelButton().getParent().setVisible(true);
		nextLevelPaneController.getNextLevelButton().getParent().toFront();

		startLabel.setOpacity(1.0);
		timelineBall.stop();
		timelinePalette.stop();
	}

	private void endGameFault() {
		topScores.checkTopScores(points);

		topScorePaneController.getNextButton().getParent().setVisible(true);
		topScorePaneController.getNextButton().getParent().toFront();

		startLabel.setOpacity(1.0);
		timelineBall.stop();
		timelinePalette.stop();
	}

	private void configureBoxes() {
		pane.getChildren().addAll(boxArr = levels.getLevel(numOfLevel).getBoxesArr());
		maxTopScoreForLevel = points;
		for (int i = 0; i < numOfBoxesInColumns * numOfBoxesInRows; i++) {
			if (!boxArr.get(i).getFill().equals(Color.GRAY)) {
				maxTopScoreForLevel++;
			}
		}
	}
}
