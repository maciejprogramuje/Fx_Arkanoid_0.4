package pl.maciej.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TopScores {
	ArrayList<Label> nameLabelArr;
	ArrayList<TextField> nameTextFieldArr;
	ArrayList<Label> scoreLabelArr;
	
	public TopScores(ArrayList<Label> nameLabelArr, ArrayList<TextField> nameTextFieldArr,
			ArrayList<Label> scoreLabelArr) {
		this.nameLabelArr = nameLabelArr;
		this.nameTextFieldArr = nameTextFieldArr;
		this.scoreLabelArr = scoreLabelArr;
	}

	public void saveTopScores() {          
		for (int i = 0; i < nameLabelArr.size(); i++) {
	
			if(!nameTextFieldArr.get(i).getText().equals("")) {
				nameLabelArr.get(i).setText(nameTextFieldArr.get(i).getText());
				nameTextFieldArr.get(i).setText(nameTextFieldArr.get(i).getText());
			} else {
				nameLabelArr.get(i).setText("-anonim-");
				nameTextFieldArr.get(i).setText("-anonim-");
			}
						
			nameTextFieldArr.get(i).setVisible(false);
			nameLabelArr.get(i).setVisible(true);
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("topScoreFile.obj"));
			for(int i = 0; i < nameLabelArr.size(); i++) {
				bw.write(scoreLabelArr.get(i).getText() + " " + nameLabelArr.get(i).getText() + "\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	public void checkTopScores(Integer points) {
		int j = 0;
		
		for(int i = 0; i < nameLabelArr.size(); i++) {
			if(points <= Integer.valueOf(scoreLabelArr.get(j).getText())) {
				j++;
			}
		} 

		if(j < nameLabelArr.size()) {
			changeScoresAndNamesPosition(j, points);
		}	
	}

	private void changeScoresAndNamesPosition(int num, Integer points) {
		for (int i = 4 - num; i > num; i--) {
			scoreLabelArr.get(i).setText(scoreLabelArr.get(i - 1).getText());
			nameLabelArr.get(i).setText(nameLabelArr.get(i - 1).getText());
			nameTextFieldArr.get(i).setText(nameLabelArr.get(i - 1).getText());
		}

		scoreLabelArr.get(num).setText(points.toString());
		nameLabelArr.get(num).setVisible(false);
		nameTextFieldArr.get(num).setVisible(true);
		nameTextFieldArr.get(num).clear();
		Platform.runLater(() -> nameTextFieldArr.get(num).requestFocus());
	}
	
}
