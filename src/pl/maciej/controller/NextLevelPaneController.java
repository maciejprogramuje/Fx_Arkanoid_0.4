package pl.maciej.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class NextLevelPaneController {
	@FXML
	private Button nextLevelButton;

	@FXML
	private AnchorPane nextLevelPane;
	
	@FXML
	void initialize() {
		
	}
	
	public Button getNextLevelButton() {
		return nextLevelButton;
	}
}
