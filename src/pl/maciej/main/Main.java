package pl.maciej.main;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static final double BALL_SPEED = 0.01;
	public static final double PALETTE_SPEED = 0.005;
	public static final double PALETTE_WIDTH = 100;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/pl/maciej/view/MainPane.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("Arkanoid v0.4 - facebook.com/maciejprogramuje");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
