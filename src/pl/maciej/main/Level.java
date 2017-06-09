package pl.maciej.main;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Level {
	ArrayList<Rectangle> boxesArr;
			
	public ArrayList<Rectangle> getBoxesArr() {
		return boxesArr;
	}

	public Level(boolean[] boxesPlan, int numOfBoxesInColumns, int numOfBoxesInRows) {
		boxesArr = new ArrayList<>();

		for (int i = 0; i < numOfBoxesInColumns; i++) {
			for (int j = 0; j < numOfBoxesInRows; j++) {
				Rectangle b1 = new Rectangle(25 + 25 * j + 20 * j, 15 + 10 * i + 10 * i, 25, 10);
				//b1.getStyleClass().add("boxStyle");
				boxesArr.add(b1);
			}
		}	
	
		for (int i = 0; i < boxesPlan.length; i++) {
			if (!boxesPlan[i]) {
				boxesArr.get(i).setFill(Color.GRAY);
			} else {
				boxesArr.get(i).setFill(Color.LIGHTGRAY);
			}
		}
	}
}
