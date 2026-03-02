package com.example.javamazegenerator.player;

import com.example.javamazegenerator.Cell;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class RunnablePlayer implements Runnable{
    Scene scene; AnchorPane root; Cell[][] grid;
    double height, width;
    public RunnablePlayer(Scene scene, AnchorPane root, Cell[][] grid, double height, double width){
        this.scene = scene;
        this.root = root;
        this.grid = grid;
        this.height = height;
        this.width = width;
    }
    @Override
    public void run() {
//        Player player = new Player(scene, root, grid, height, width);
//        player.init();
    }
}
