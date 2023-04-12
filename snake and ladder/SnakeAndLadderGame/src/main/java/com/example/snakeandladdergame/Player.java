package com.example.snakeandladdergame;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    String name;
    int currPos;
    Circle coin;
    static Board board = new Board();
    public Player(String name, int tileSize, Color coinColor){
        this.name = name;
        this.currPos = 0;
        coin = new Circle((double) tileSize /2);
        coin.setFill(coinColor);
        move(1);
    }

    void move(int steps){
        if(currPos + steps <= 100) {
            this.currPos += steps;
            TranslateTransition secondMove = null, firstMove = translateAnimation(steps);
            int newPosition = board.getNewPosition(currPos);
            if(newPosition != currPos && newPosition != -1){
                currPos = newPosition;
                secondMove = translateAnimation(6);
            }
            if(secondMove == null) firstMove.play();
            else{
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove, new PauseTransition(Duration.millis(1000)), secondMove);
                sequentialTransition.play();
            }
        }
    }
    boolean isWinner(){
        if(currPos == 100) return true;
        return false;
    }
    private TranslateTransition translateAnimation(int diceValue){
        System.out.println(this.currPos);
        TranslateTransition animate = new TranslateTransition(Duration.millis(200*diceValue), coin);
        animate.setToX(board.getXCoordinate(this.currPos));
        animate.setToY(board.getYCoordinate(this.currPos));
        animate.setAutoReverse(false);
        return animate;
    }

    public void startingPoint(){
        currPos = 1;
        move(0);
    }

    public int getCurrPos() {
        return currPos;
    }

    public Circle getCoin() {
        return coin;
    }

    public String getName() {
        return name;
    }

}
