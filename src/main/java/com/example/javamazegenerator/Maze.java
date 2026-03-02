package com.example.javamazegenerator;


import com.example.javamazegenerator.player.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.Stack;

import static com.example.javamazegenerator.Cell.breakWalls;

public class Maze {
    int size, rows, cols;
    Cell[][] grid;
    Cell current;
    public Cell goal;
    Stack<Cell> stack = new Stack<>();
    MazeScreen ms;
    Maze(int size, int rows, int cols, MazeScreen ms){
        this.size  = size;
        this.rows = rows;
        this.cols = cols;
        this.ms = ms;
        grid = new Cell[rows][cols];
    }

    void setup(){
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                grid[r][c] = new Cell(r, c, grid, size, rows, cols);
            }
        }
        current = grid[0][0];
        goal = grid[rows-1][cols-1];
        current.visited = true;
    }

    void drawGrid(AnchorPane root){
        for(Cell[] r : grid){
            for(Cell c : r){
                if(c.walls.topWall) c.drawTopWall(root);
                if(c.walls.bottomWall) c.drawBottomWall(root);
                if(c.walls.rightWall) c.drawRightWall(root);
                if(c.walls.leftWall) c.drawLeftWall(root);
            }
        }
    }

    void draw(Scene scene, AnchorPane root){
        TimeMethod timer = new TimeMethod(current, goal, grid, scene, root, stack, this, ms);
        timer.start();
    }

    private static class TimeMethod extends AnimationTimer{
        Cell current, goal; Cell[][] grid; Scene scene; AnchorPane root; Stack<Cell> stack; Maze maze; MazeScreen ms;
        TimeMethod(Cell current, Cell goal, Cell[][] grid, Scene scene, AnchorPane root, Stack<Cell> stack, Maze maze, MazeScreen ms){
            this.current = current;
            this.grid = grid;
            this.scene = scene;
            this.root = root;
            this.stack = stack;
            this.maze = maze;
            this.goal = goal;
            this.ms = ms;
        }

        @Override
        public void handle(long l) {
            handlee();
        }

        private void handlee() {
            Cell noNeighbour = new Cell(1, 1, grid, 1, 1, 1);
            int playerSize = (int) ((int) maze.rows * ((current.parentSize * 0.5) / (maze.rows * maze.cols)));
            Player player = new Player(scene, root, grid, maze, ms, playerSize, playerSize);
            Cell next = current.nextNeighbour(noNeighbour);
            if(!next.equals(noNeighbour)){
                next.visited = true;
                stack.push(current);
                breakWalls(current, next);
                current = next;
                current.lines.clear();
            }
            else if(!stack.empty()){
                current = stack.pop();
                current.lines.clear();
            }
            if(stack.empty()){
                root.getChildren().clear();
                maze.drawGrid(root);
                Rectangle rect = current.highlightCell(root, 150, 50, 255);
                goal.highlightCell(root, 50, 200, 50);
                next.unhighlightCell(rect);
                player.init();
                this.stop();
                return;
            }
            root.getChildren().clear();
            maze.drawGrid(root);
            current.highlightCell(root, 150, 50, 255);
            goal.highlightCell(root, 50, 200, 50);
        }
    }
}
