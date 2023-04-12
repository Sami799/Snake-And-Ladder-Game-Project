package com.example.snakeandladdergame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    Tile(int tileSize){
        setWidth(tileSize);
        setHeight(tileSize);
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
    }
}
