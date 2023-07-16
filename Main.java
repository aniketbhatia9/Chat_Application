package com.example.server_side;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("Server.fxml"));
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root, 600,378));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
