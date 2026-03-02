package com.example.javamazegenerator;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.Vector;

public class Cell{
    public int rowNum;
    public int colNum;
    public int parentSize;
    public int parentRows;
    public int parentCols;
    int x;
    int y;
    Cell[][] parentGrid;
    CellWalls walls;
    boolean visited;
    public Vector<Line> lines = new Vector<Line>();
    Cell(int rowNum, int colNum, Cell[][] parentGrid, int parentSize, int parentRows, int parentCols){
        this.rowNum = rowNum;
        this.colNum = colNum;
        this.parentGrid = parentGrid;
        this.parentSize = parentSize;
        this.parentRows = parentRows;
        this.parentCols = parentCols;
        walls = new CellWalls();
        x = (colNum * parentSize) / parentCols;
        y = (rowNum * parentSize) / parentRows;
        visited = false;
    }

    Cell nextNeighbour(Cell noNeighbour){
        Vector<Cell> neighbours = new Vector<>();
        Cell top = rowNum != 0 ? parentGrid[rowNum - 1][colNum] : noNeighbour;
        Cell right = colNum != parentCols  - 1 ? parentGrid[rowNum][colNum+1] : noNeighbour;
        Cell bottom = rowNum != parentRows - 1 ? parentGrid[rowNum+1][colNum] : noNeighbour;
        Cell left = colNum != 0 ? parentGrid[rowNum][colNum-1] : noNeighbour;

        //append top neighbour
        if(rowNum != 0 && !top.visited) neighbours.add(top);

        //append right neighbour:
        if(colNum != parentCols - 1 && !right.visited) neighbours.add(right);

        //append bottom neighbour:
        if(rowNum != parentRows - 1 && !bottom.visited) neighbours.add(bottom);

        //append left neighbour:
        if(colNum != 0 && !left.visited) neighbours.add(left);

        if(!neighbours.isEmpty()){
            int rand = (int) Math.floor(Math.random() * neighbours.size());
            return neighbours.elementAt(rand);
        }
        return noNeighbour;
    }

    void drawTopWall(AnchorPane root){
        Line line = new Line();
        line.setStroke(Color.WHITE);
        line.setStartX(x); line.setStartY(y);
        line.setEndX((float)(x + parentSize / parentCols)); line.setEndY(y);
        root.getChildren().add(line);
        lines.add(line);
    }

    void drawRightWall(AnchorPane root){
        Line line = new Line();
        line.setStroke(Color.WHITE);
        line.setStartX((float)(x + parentSize / parentCols)); line.setStartY(y);
        line.setEndX((float)(x + parentSize / parentCols)); line.setEndY((float)(y + parentSize / parentRows));
        root.getChildren().add(line);
        lines.add(line);
    }

    void drawBottomWall(AnchorPane root){
        Line line = new Line();
        line.setStroke(Color.WHITE);
        line.setStartX(x); line.setStartY((float)(y + parentSize / parentRows));
        line.setEndX((float)(x + parentSize / parentCols)); line.setEndY((float)(y + parentSize / parentRows));
        root.getChildren().add(line);
        lines.add(line);
    }

    void drawLeftWall(AnchorPane root){
        Line line = new Line();
        line.setStroke(Color.WHITE);
        line.setStartX(x); line.setStartY(y);
        line.setEndX(x); line.setEndY((float)(y + parentSize / parentRows));
        root.getChildren().add(line);
        lines.add(line);
    }

    Rectangle highlightCell(AnchorPane root, int r, int g, int b){
        int x = colNum * parentSize / parentRows + 1;
        int y = rowNum * parentSize / parentRows + 1;
        Rectangle rect = new Rectangle(parentSize / parentCols,  parentSize / parentRows , Color.rgb(r,g,b));
        rect.setX(x); rect.setY(y);
        root.getChildren().add(rect);
        return rect;
    }

    void unhighlightCell(Rectangle rect){
        rect.setFill(Color.TRANSPARENT);
    }

    static void breakWalls(Cell c1, Cell c2){
        int x = c1.colNum - c2.colNum;
        int y = c1.rowNum - c2.rowNum;

        if(x == 1){
            c1.walls.leftWall = false;
            c2.walls.rightWall = false;
        }
        else if(x == -1){
            c1.walls.rightWall = false;
            c2.walls.leftWall = false;
        }

        if(y == 1){
            c1.walls.topWall = false;
            c2.walls.bottomWall = false;
        }
        else if(y == -1){
            c1.walls.bottomWall = false;
            c2.walls.topWall = false;
        }
    }
}

class CellWalls{
    boolean rightWall, leftWall, bottomWall,topWall;
    CellWalls(){
        rightWall = leftWall = bottomWall = topWall = true;
    }
}
