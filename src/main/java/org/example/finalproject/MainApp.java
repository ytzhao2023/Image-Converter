package org.example.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main FXML layout defined in Main.fxml using the FXMLLoader.
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/finalproject/Main.fxml"));

        // Create a Scene object with the loaded layout.
        Scene scene = new Scene(root);

        // Set the title of the primary stage.
        primaryStage.setTitle("Image Convert Tool");

        // Set the scene on the primary stage.
        primaryStage.setScene(scene);

        // Show the primary stage, making the application visible.
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
