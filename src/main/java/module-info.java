module com.example.javamazegenerator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.javamazegenerator to javafx.fxml;
    exports com.example.javamazegenerator;
    exports com.example.javamazegenerator.player;
    opens com.example.javamazegenerator.player to javafx.fxml;
}