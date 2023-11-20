package com.example.ca2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;

public class RetroManager extends Application {
    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Dashboard.fxml"));
        Scene dashboardScene = new Scene(root, Color.LIGHTSKYBLUE);
        scene = dashboardScene;

        stage.getIcons().add(new Image(RetroManager.class.getResourceAsStream("/JoyConIcon.jpg")));

        stage.setTitle("RetroVideoGameManagementSystem");
        stage.setWidth(1100);
        stage.setHeight(1000);
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}