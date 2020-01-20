package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Model;
import model.MyModel;
import view_model.ViewModel;

import javax.swing.text.View;
import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.io.IOException;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		Model m = new MyModel(); // Model

		ViewModel vm = new ViewModel(m); // ViewModel
		try {

			FXMLLoader fxl = new FXMLLoader(getClass().getResource("Window.fxml"));
			AnchorPane root = (AnchorPane) fxl.load();
			root.setStyle("-fx-background-image: url(\"/resources/123.jpeg\");"+
					"-fx-background-size: cover;");

			WindowController wc = fxl.getController(); // View
			wc.setViewModel(vm);

			Scene scene = new Scene(root, 600, 720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		launch(args);

	}
}
