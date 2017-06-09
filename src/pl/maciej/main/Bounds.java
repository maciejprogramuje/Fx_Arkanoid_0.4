package pl.maciej.main;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Bounds {
	Rectangle palette;
	Pane pane;
	Circle ball;
	double ballLeft, ballRight, ballCenterX, ballCenterY, ballTop, ballDown, paletteLeft, paletteRight, paletteTop, paletteDown,
			paneLeft, paneRight, paneTop, paneDown;

	public Bounds(Rectangle palette, Pane pane, Circle ball) {
		this.palette = palette;
		this.pane = pane;
		this.ball = ball;
	}

	public void calculatePaneBounds() {
		paneLeft = 0;
		paneRight = pane.getWidth();
		paneTop = 0;
		paneDown = pane.getHeight();
	}

	public void calculatePaletteBounds() {
		paletteLeft = palette.getBoundsInParent().getMinX();
		paletteRight = palette.getBoundsInParent().getMaxX();
		paletteTop = palette.getBoundsInParent().getMinY();
		paletteDown = palette.getBoundsInParent().getMaxY();
	}

	public void calculateBallBounds() {
		ballLeft = ball.getBoundsInParent().getMinX();
		ballRight = ball.getBoundsInParent().getMaxX();
		ballTop = ball.getBoundsInParent().getMinY();
		ballDown = ball.getBoundsInParent().getMaxY();
		ballCenterX = ballLeft + ball.getRadius() / 2;
		ballCenterY = ballTop + ball.getRadius() / 2;
	}

	public double getBallLeft() {
		return ballLeft;
	}

	public double getBallRight() {
		return ballRight;
	}

	public double getBallCenterX() {
		return ballCenterX;
	}

	public double getBallCenterY() {
		return ballCenterY;
	}

	public double getBallTop() {
		return ballTop;
	}

	public double getBallDown() {
		return ballDown;
	}

	public double getPaletteLeft() {
		return paletteLeft;
	}

	public double getPaletteRight() {
		return paletteRight;
	}

	public double getPaletteTop() {
		return paletteTop;
	}
	
	public double getPaletteDown() {
		return paletteDown;
	}

	public double getPaneLeft() {
		return paneLeft;
	}

	public double getPaneRight() {
		return paneRight;
	}

	public double getPaneTop() {
		return paneTop;
	}

	public double getPaneDown() {
		return paneDown;
	}
}
