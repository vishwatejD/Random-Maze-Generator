package com.example.javamazegenerator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class HomeScreen  {
    private final GridPane root = new GridPane();
    private Scene mainScene = new Scene(root);
    Stage stage;

    HomeScreen(Stage stage){
        this.stage = stage;
    }

    //Pixelify sans font
    Font h1Font = Font.loadFont(getClass().getResourceAsStream("/Fonts/PixelifySans-VariableFont_wght.ttf"), 60);
    Font labelFont = Font.loadFont(getClass().getResourceAsStream("/Fonts/PixelifySans-VariableFont_wght.ttf"), 16);

    //Main Text
    Label h1 = new Label("RANDOM MAZE GENERATOR");

    //Form
    //Rectangle
    Rectangle form = new Rectangle();
    //Inputs
    //Grid size
    Label gridSizeLabel = new Label("Enter grid size:");
    Spinner<Integer> gridSizeSpinner = new Spinner<Integer>();
    //Grid rows/cols
    Label gridRowsLabel = new Label("Enter number of rows/columns:");
    Spinner<Integer> gridRowsSpinner = new Spinner<Integer>();

    //Generate button
    public Button genBtn = new Button("Generate Maze");

    //Scene setup
    void setup(){
        //root
        root.setBackground(new Background(new BackgroundFill(Color.rgb(20, 20, 40), CornerRadii.EMPTY, Insets.EMPTY)));
        //Main text
        h1.setFont(h1Font);
        h1.setTextFill(Color.rgb(245, 245, 245));
        h1.setPadding(new Insets(50));

        //Form
        //Rectangle
        form.setWidth(500);
        form.setHeight(300);
        form.setFill(Color.rgb(40, 40, 80));
        form.setArcHeight(30);
        form.setArcWidth(30);

        //Grid size label
        gridSizeLabel.setPadding(new Insets(20));
        gridSizeLabel.setFont(labelFont);
        gridSizeLabel.setTextFill(Color.WHITE);
        //Grid size input
        SpinnerValueFactory<Integer> gridValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(400, 750);
        gridValueFactory.setValue(400);
        gridSizeSpinner.setValueFactory(gridValueFactory);
        gridSizeSpinner.setEditable(true);
        //Grid rows/cols label
        gridRowsLabel.setPadding(new Insets(70, 0, 20, 0));
        gridRowsLabel.setFont(labelFont);
        gridRowsLabel.setTextFill(Color.WHITE);
        //Grid rows/cols spinner
        SpinnerValueFactory<Integer> gridRowsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 30);
        gridRowsValueFactory.setValue(5);
        gridRowsSpinner.setValueFactory(gridRowsValueFactory);

        //Submit button
        genBtn.setPadding(new Insets(20));
        genBtn.setStyle("");
        genBtn.setFont(labelFont);

        root.add(h1, 0, 0, 1, 1);
        root.add(form, 0, 2, 1, 10);
        root.add(gridSizeLabel, 0, 2, 1, 1);
        root.add(gridSizeSpinner, 0, 3, 1, 1);
        root.add(gridRowsLabel, 0, 4, 1, 1);
        root.add(gridRowsSpinner, 0, 5, 1, 1);
        root.add(genBtn, 0, 20, 1, 10);
//        root.getChildren().add(gridRowsLabel);
//        root.getChildren().add(gridRowsSpinner);
        root.setVgap(5);
        root.setAlignment(Pos.TOP_CENTER);
        GridPane.setHalignment(h1, HPos.CENTER);
        GridPane.setHalignment(form, HPos.CENTER);
        GridPane.setHalignment(gridSizeLabel, HPos.CENTER);
        GridPane.setHalignment(gridSizeSpinner, HPos.CENTER);
        GridPane.setHalignment(gridRowsLabel, HPos.CENTER);
        GridPane.setHalignment(gridRowsSpinner, HPos.CENTER);
        GridPane.setHalignment(genBtn, HPos.CENTER);
    }

    EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            int size = gridSizeSpinner.getValue();
            int rows = gridRowsSpinner.getValue();
            MazeScreen ms = new MazeScreen(size, rows, rows, stage, mainScene);
            ms.mazeSetup();
            ms.setMazeScene();
        }
    };

    void buttonSetup(){
        genBtn.setOnAction(eventHandler);
    }

    public Scene getMainScene() {
        buttonSetup();
        setup();
        stage.setScene(mainScene);
        stage.setMaximized(true);
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }
}
