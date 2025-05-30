package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX application entry point for the Animal Shelter Management system.
 * <p>
 * This class loads the initial UI defined in {@code dashboard.fxml} and starts the GUI.
 */
public class FXMain extends Application {

    /**
     * JavaFX lifecycle method that sets up and displays the primary application window.
     *
     * @param primaryStage the primary stage provided by the JavaFX runtime
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("javaFX/views/dashboard.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Animal Shelter Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Application entry point. Launches the JavaFX runtime.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
