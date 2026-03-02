package com.example.javamazegenerator.player;

import com.example.javamazegenerator.Cell;
import com.example.javamazegenerator.Maze;
import com.example.javamazegenerator.MazeScreen;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Player {
    Scene scene;
    AnchorPane root;
    public Rectangle player = new Rectangle(100,100, Color.RED);
    boolean upPressed = false;
    boolean downPressed = false;
    boolean leftPressed = false;
    boolean rightPressed = false;
    double increment;
    Cell[][] grid;
    Maze maze;
    MazeScreen ms;
    double height, width;
    public Player(Scene scene, AnchorPane root, Cell[][] grid, Maze maze, MazeScreen ms, double height, double width){
        this.scene = scene;
        this.root = root;
        this.grid = grid;
        this.height = height;
        this.width = width;
        this.maze = maze;
        this.ms = ms;
        increment = (double) (grid[0][0].parentSize / grid[0][0].parentCols) * 0.1;
        player.setHeight(height); player.setLayoutX(1);
        player.setWidth(width); player.setLayoutY(1);
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(upPressed) player.setLayoutY(player.getLayoutY() - increment);
            if(downPressed) player.setLayoutY(player.getLayoutY() + increment);
            if(leftPressed) player.setLayoutX(player.getLayoutX() - increment);
            if(rightPressed) player.setLayoutX(player.getLayoutX() + increment);
        }
    };

    AnimationTimer collisionDetectionTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            for(Cell[] row : grid){
                for(Cell c : row){
                    for(Line line : c.lines){
                        handleCollision(line, player);
                    }
                }
            }
        }
    };

    void handleCollision(Line boundary, Rectangle player){
        int collisionRebound = 1;
        if(boundary.getBoundsInParent().intersects(player.getBoundsInParent())){
            if(upPressed) player.setLayoutY(player.getLayoutY() + increment*collisionRebound);
            if(rightPressed) player.setLayoutX(player.getLayoutX() - increment*collisionRebound);
            if(downPressed) player.setLayoutY(player.getLayoutY() - increment*collisionRebound);
            if(leftPressed) player.setLayoutX(player.getLayoutX() + increment*collisionRebound);
            if(player.getLayoutX() >= (maze.goal.colNum * ((double) maze.goal.parentSize / maze.goal.parentRows)) + 1 &&
                player.getLayoutY() >= (maze.goal.rowNum * ((double) maze.goal.parentSize / maze.goal.parentRows)) + 1){
                ms.winSetup();
                this.player.setFill(Color.TRANSPARENT);
            }
        }
    }

    public void init(){
        root.getChildren().add(player);
        timer.start();
        collisionDetectionTimer.start();
        playerMovementEventHandler();
    }

    void playerMovementEventHandler(){
        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.W){
                upPressed = true;
            }
            if(event.getCode() == KeyCode.A){
                leftPressed = true;
            }
            if(event.getCode() == KeyCode.S){
                downPressed = true;
            }
            if(event.getCode() == KeyCode.D){
                rightPressed = true;
            }
        });
        scene.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.W){
                upPressed = false;
            }
            if(event.getCode() == KeyCode.A){
                leftPressed = false;
            }
            if(event.getCode() == KeyCode.S){
                downPressed = false;
            }
            if(event.getCode() == KeyCode.D){
                rightPressed = false;
            }
        });
    }
}
