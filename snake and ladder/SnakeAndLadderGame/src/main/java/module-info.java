module com.example.snakeandladdergame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeandladdergame to javafx.fxml;
    exports com.example.snakeandladdergame;
}