package com.example.javamazegenerator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    public static int highScore = 0;

    @Override
    public void start(Stage stage) throws IOException {
        HomeScreen mainScreen = new HomeScreen(stage);
        mainScreen.getMainScene();

        //title
        stage.setTitle("Random Maze Generator");
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }
}