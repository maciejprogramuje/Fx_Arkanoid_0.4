package pl.maciej.main;

import java.util.ArrayList;

public class Levels {
	ArrayList<Level> level;
	
	public Level getLevel(int n) {
		return level.get(n);
	}
	
	public int getNumOfLevels() {
		return level.size();
	}

	public Levels(int numOfBoxesInColumns, int numOfBoxesInRows) {
		level = new ArrayList<>();
		
		boolean[] tempBoxesPlan1 = { 
				true, true,  true,  true, true,  true,  true, true,  true,   true, true,  true,  true,
				true, true,  true,  true, true,  true,  true, true,  true,   true, true,  true,  true,
				false, true,  true,  true, true,  true,  true, true,  true,   true, true,  true,  false,
				false, true,  true,  false, true,  true,  false, true,  true,   false, true,  true,  false, 
				false, true,  true,  false, true,  true,  false, true,  true,   false, true,  true,  false, 
				false, true,  true,  true, true,  true,  true, true,  true,   true, true,  true,  false,
				true, true,  true,  true, true,  true,  true, true,  true,   true, true,  true,  true,
				true,  false, true, false,  true, false, true,  false, true,  false,  true, false, true };

		level.add(new Level(tempBoxesPlan1, numOfBoxesInColumns, numOfBoxesInRows));
		
		boolean[] tempBoxesPlan2 = { 
				true, false, true, false, true, false, true, false, true, false, true, false, true,
				true, true, true, true, true, true, true, true, true, true, true, true, true,
				true, false, true, false, true, false, true, false, true, false, true, false, true,
				true, true, true, true, true, true, true, true, true, true, true, true, true, 
				true, false, true, false, true, false, true, false, true, false, true, false, true, 
				true, true, true, true, true, true, true, true, true, true, true, true, true,
				true, false, true, false, true, false, true, false, true, false, true, false, true,
				true, true, true, true, true, true, true, true, true, true, true, true, true };

		level.add(new Level(tempBoxesPlan2, numOfBoxesInColumns, numOfBoxesInRows));
		
		boolean[] tempBoxesPlan3 = { 
				true, true, true, true, true, true, true, true, true, true, true, true, true,
				true, false, false, true, false, false, true, false, false, true, false, false, true,
				true, true, true, true, true, true, true, true, true, true, true, true, true,
				true, false, false, true, false, false, true, false, false, true, false, false, true, 
				true, true, true, true, true, true, true, true, true, true, true, true, true, 
				true, false, false, true, false, false, true, false, false, true, false, false, true,
				true, true, true, true, true, true, true, true, true, true, true, true, true,
				true, false, false, true, false, false, true, false, false, true, false, false, true };

		level.add(new Level(tempBoxesPlan3, numOfBoxesInColumns, numOfBoxesInRows));
	}
}
