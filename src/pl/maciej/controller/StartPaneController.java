package pl.maciej.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class StartPaneController {
	@FXML
	private Button nieButton;
	@FXML
	private Button takButton;
	@FXML
	private AnchorPane startPane;

	@FXML
	void initialize() {

	}

	public Button getNieButton() {
		return nieButton;
	}

	public Button getTakButton() {
		return takButton;
	}
}