package com.example.javamazegenerator;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static com.example.javamazegenerator.HelloApplication.highScore;

public class MazeScreen {
    Stage stage;
    Maze maze;
    GridPane root = new GridPane();
    AnchorPane mazeRoot = new AnchorPane();
    Scene mainScene;
    Scene mazeScene = new Scene(root); // Increased width to accommodate the high score label
    int size, rows, cols;
    int numOfWins = 0;
    int time = 0;
    int score = 0;
    Thread timerThread;

    Font h1Font = Font.loadFont(getClass().getResourceAsStream("/Fonts/PixelifySans-VariableFont_wght.ttf"), 60);
    Font labelFont = Font.loadFont(getClass().getResourceAsStream("/Fonts/PixelifySans-VariableFont_wght.ttf"), 16);

    Label timerLabel = new Label("Time: 00:00");
    Label scoreLabel = new Label("Score: 0");
    Label highScoreLabel = new Label("High Score: " + highScore); // New label for high score

    MazeScreen(int size, int rows, int cols, Stage stage, Scene mainScene) {
        this.stage = stage;
        this.size = size;
        this.rows = rows;
        this.cols = cols;
        this.mainScene = mainScene;
        maze = new Maze(size, rows, cols, this);
    }

    void mazeSetup() {
        mazeRoot.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 80), CornerRadii.EMPTY, Insets.EMPTY)));
        root.setBackground(new Background(new BackgroundFill(Color.rgb(20, 20, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        root.add(mazeRoot, 1, 1, 1, 1); // Adjusted grid position
        GridPane.setHalignment(mazeRoot, HPos.CENTER);
        root.setAlignment(Pos.CENTER);
        root.setVgap(23);

        // Set up labels
        timerLabel.setFont(Font.font(h1Font.getFamily(), FontWeight.LIGHT, 25));
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setPadding(new Insets(10));
        timerLabel.setAlignment(Pos.CENTER); // Center-align the text within the label
        timerLabel.setBackground(new Background(new BackgroundFill(Color.rgb(3, 3, 31), new CornerRadii(10), Insets.EMPTY)));
        timerLabel.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 5, 0.5, 0, 0);");

        scoreLabel.setFont(Font.font(h1Font.getFamily(), FontWeight.THIN, 20));
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setPadding(new Insets(10)); // Added padding for better spacing

        highScoreLabel.setFont(Font.font(h1Font.getFamily(), FontWeight.THIN, 20)); // Set font for high score label
        highScoreLabel.setTextFill(Color.WHITE); // Set text color for high score label
        highScoreLabel.setPadding(new Insets(10)); // Added padding for better spacing
        highScoreLabel.setBackground(new Background(new BackgroundFill(Color.rgb(3, 3, 31), new CornerRadii(10), Insets.EMPTY))); // Optional background for distinction

        // Add labels to the grid
        root.add(timerLabel, 1, 2);
        root.add(scoreLabel, 0, 3);
        root.add(highScoreLabel, 2, 3); // Place the high score label to the right of the score label

        GridPane.setHalignment(timerLabel, HPos.CENTER);
        GridPane.setHalignment(scoreLabel, HPos.CENTER);
        GridPane.setHalignment(highScoreLabel, HPos.CENTER); // Align high score label to the right

        maze.setup();
        maze.draw(mazeScene, mazeRoot);

        startTimer();
    }

    public void winSetup() {
        if (numOfWins == 0) {
            score = calculateScore();
            scoreLabel.setText("Score: " + score);

            // Update high score if the current score is greater than the stored high score
            if (score > highScore) {
                highScore = score;
                highScoreLabel.setText("High Score: " + highScore); // Update high score label
            }

            numOfWins++; // Increment wins

            Label winLabel = new Label("YOU WON");
            winLabel.setFont(h1Font);
            winLabel.setTextFill(Color.WHITE);
            Button resetBtn = new Button("Restart?");
            resetBtn.setPadding(new Insets(20));
            resetBtn.setFont(labelFont);
            resetBtn.setOnAction(actionEvent -> {
                stage.setScene(mainScene);
                time = 0;
                timerLabel.setText("Time: 00:00");
                scoreLabel.setText("Score: 0");
                // No change to numOfWins and highScore, keep them as is
            });

            root.setVgap(10);
            root.add(winLabel, 1, 0, 1, 1);
            root.add(resetBtn, 1, 10, 1, 1);
            GridPane.setHalignment(resetBtn, HPos.CENTER);
            GridPane.setHalignment(winLabel, HPos.CENTER);

            // Stop the timer when the maze is solved
            stopTimer();
            System.out.println("Final Score: " + score);
        }
    }

    int calculateScore() {
        return Math.max(1, (rows * cols * 100) / Math.max(1, time));
    }

    void startTimer() {
        timerThread = new Thread(new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(1000); // Wait 1 second
                        time++;
                        int minutes = time / 60;
                        int seconds = time % 60;
                        String timeString = String.format("Time: %02d:%02d", minutes, seconds);
                        // Update the label in JavaFX Application Thread
                        javafx.application.Platform.runLater(new Runnable() {
                            public void run() {
                                timerLabel.setText(timeString);
                            }
                        });
                    } catch (InterruptedException e) {
                        System.out.println("Timer interrupted.");
                        // Exit the thread if interrupted
                        return;
                    }
                }
            }
        });
        timerThread.start();
    }

    void stopTimer() {
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
            try {
                timerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void setMazeScene() {
        stage.setScene(mazeScene);
    }
}