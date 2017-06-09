package pl.maciej.main;

import javafx.scene.shape.Rectangle;

public class Collisions {
	public boolean ballTopVsPane(Bounds bounds) {
		if (bounds.getBallTop() < bounds.getPaneTop()) {
			return true;
		}
		return false;
	}

	public boolean ballRightVsPane(Bounds bounds) {
		if (bounds.getBallRight() > bounds.getPaneRight()) {
			return true;
		}
		return false;
	}

	public boolean ballLeftVsPane(Bounds bounds) {
		if (bounds.getBallLeft() < bounds.getPaneLeft()) {
			return true;
		}
		return false;
	}
	
	public boolean ballVsTopOfPalette(Bounds bounds) {
		if (bounds.getBallDown() == bounds.getPaletteTop() && bounds.getBallCenterX() >= bounds.getPaletteLeft() && bounds.getBallCenterX() <= bounds.getPaletteRight()) {
			return true;
		}
		return false;
	}
	
	public boolean ballVsLeftOfPalette(Bounds bounds) {
		if (bounds.getBallDown() >= bounds.getPaletteTop() && bounds.getBallCenterY() <= bounds.getPaletteTop() && bounds.getBallRight() >= bounds.getPaletteLeft() && bounds.getBallCenterX() <= bounds.getPaletteLeft()) {
			return true;
		}
		return false;
	}
	
	public boolean ballVsRightOfPalette(Bounds bounds) {
		if (bounds.getBallDown() >= bounds.getPaletteTop() && bounds.getBallCenterY() <= bounds.getPaletteTop() && bounds.getBallLeft() <= bounds.getPaletteRight() && bounds.ballCenterX >= bounds.getPaletteRight()) {
			return true;
		}
		return false;
	}

	public boolean ballDownVsPane(Bounds bounds) {
		if (bounds.getBallDown() > bounds.getPaneDown()) {
			return true;
		}
		return false;
	}

	public boolean ballTopVsBox(Bounds bounds, Rectangle box) {
		if (bounds.getBallTop() < box.getBoundsInParent().getMaxY()
				&& bounds.getBallDown() > box.getBoundsInParent().getMaxY()
				&& bounds.getBallCenterX() > box.getBoundsInParent().getMinX()
				&& bounds.getBallCenterX() < box.getBoundsInParent().getMaxX()) {
			return true;
		}
		return false;
	}

	public boolean ballRightVsBox(Bounds bounds, Rectangle box) {
		if (bounds.getBallRight() > box.getBoundsInParent().getMinX()
				&& bounds.getBallLeft() < box.getBoundsInParent().getMinX()
				&& bounds.getBallCenterY() < box.getBoundsInParent().getMaxY()
				&& bounds.getBallCenterY() > box.getBoundsInParent().getMinY()) {
			return true;
		}
		return false;
	}

	public boolean ballDownVsBox(Bounds bounds, Rectangle box) {
		if (bounds.getBallDown() > box.getBoundsInParent().getMinY()
				&& bounds.getBallTop() < box.getBoundsInParent().getMinY()
				&& bounds.getBallCenterX() > box.getBoundsInParent().getMinX()
				&& bounds.getBallCenterX() < box.getBoundsInParent().getMaxX()) {
			return true;
		}
		return false;
	}

	public boolean ballLeftVsBox(Bounds bounds, Rectangle box) {
		if (bounds.getBallLeft() < box.getBoundsInParent().getMaxX()
				&& bounds.getBallRight() > box.getBoundsInParent().getMaxX()
				&& bounds.getBallCenterY() < box.getBoundsInParent().getMaxY()
				&& bounds.getBallCenterY() > box.getBoundsInParent().getMinY()) {
			return true;
		}
		return false;
	}
}
