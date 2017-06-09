package pl.maciej.controller;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TopScorePaneController {
	ArrayList<Label> nameLabelArr;
	ArrayList<TextField> nameTextField;
	ArrayList<Label> scoreLabel;
	
	@FXML
	private VBox topScorePane;
	@FXML
	private Label p1nameLabel;
	@FXML
	private Label p2nameLabel;
	@FXML
	private Label p3nameLabel;
	@FXML
	private Label p4nameLabel;
	@FXML
	private Label p5nameLabel;
	@FXML
	private TextField p1nameTextField;
	@FXML
	private TextField p2nameTextField;
	@FXML
	private TextField p3nameTextField;
	@FXML
	private TextField p4nameTextField;
	@FXML
	private TextField p5nameTextField;
	@FXML
	private Label p1scoreLabel;
	@FXML
	private Label p2scoreLabel;
	@FXML
	private Label p3scoreLabel;
	@FXML
	private Label p4scoreLabel;
	@FXML
	private Label p5scoreLabel;
	@FXML
	private Button nextButton;

	@FXML
	void initialize() {
		nameLabelArr = new ArrayList<>();
		nameLabelArr.add(p1nameLabel);
		nameLabelArr.add(p2nameLabel);
		nameLabelArr.add(p3nameLabel);
		nameLabelArr.add(p4nameLabel);
		nameLabelArr.add(p5nameLabel);
		
		nameTextField = new ArrayList<>();
		nameTextField.add(p1nameTextField);
		nameTextField.add(p2nameTextField);
		nameTextField.add(p3nameTextField);
		nameTextField.add(p4nameTextField);
		nameTextField.add(p5nameTextField);
		
		scoreLabel = new ArrayList<>();
		scoreLabel.add(p1scoreLabel);
		scoreLabel.add(p2scoreLabel);
		scoreLabel.add(p3scoreLabel);
		scoreLabel.add(p4scoreLabel);
		scoreLabel.add(p5scoreLabel);
	}
	
	public ArrayList<Label> getNameLabelArr() {
		return nameLabelArr;
	}

	public ArrayList<TextField> getNameTextField() {
		return nameTextField;
	}

	public ArrayList<Label> getScoreLabel() {
		return scoreLabel;
	}

	public Button getNextButton() {
		return nextButton;
	}
	
	public VBox getTopScorePane() {
		return topScorePane;
	}
}
