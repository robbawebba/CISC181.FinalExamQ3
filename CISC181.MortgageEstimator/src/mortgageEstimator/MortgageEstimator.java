package mortgageEstimator;

import java.io.IOException;

import mortgageEstimator.view.EstimatorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MortgageEstimator extends Application {

	private Stage primaryStage;
	private AnchorPane estimatorScreen;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Mortgage Estimator");
		showEstimatorScreen();	
	}

	public void showEstimatorScreen() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MortgageEstimator.class.getResource("view/EstimatorScreen.fxml"));
			estimatorScreen = (AnchorPane) loader.load();
			
			Scene estimatorScene = new Scene(estimatorScreen);
			primaryStage.setScene(estimatorScene);
			
			EstimatorController controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}

	public static void main(String[] args) {
		launch(args);
	}
}
