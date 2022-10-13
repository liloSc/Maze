module com.example.maze {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.maze to javafx.fxml;
    exports com.example.maze;
   // exports com.example.maze.Controller;
    //opens com.example.maze.Controller to javafx.fxml;
}