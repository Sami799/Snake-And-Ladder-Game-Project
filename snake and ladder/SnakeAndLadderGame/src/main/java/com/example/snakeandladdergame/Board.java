package com.example.snakeandladdergame;

import java.util.ArrayList;
import java.util.HashMap;

class Pair{
    int x, y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Board {
    ArrayList<Pair> positionCoordinates;
    HashMap<Integer, Integer> snakes;
    HashMap<Integer, Integer> ladders;
    ArrayList<Integer> snakeLadderPosition;
    Board(){
        positionCoordinates = new ArrayList<>();
        populatePositionCoordinates();
        populateSnakeLadder();
    }
    private void populateSnakeLadder(){
        snakeLadderPosition = new ArrayList<>();
        for(int i = 0; i<101; i++){
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(4, 25);
        snakeLadderPosition.set(13, 46);
        snakeLadderPosition.set(27, 5);
        snakeLadderPosition.set(33, 49);
        snakeLadderPosition.set(40, 3);
        snakeLadderPosition.set(42, 63);
        snakeLadderPosition.set(43, 18);
        snakeLadderPosition.set(50, 69);
        snakeLadderPosition.set(54, 31);
        snakeLadderPosition.set(62, 81);
        snakeLadderPosition.set(66, 45);
        snakeLadderPosition.set(76, 58);
        snakeLadderPosition.set(74, 92);
        snakeLadderPosition.set(89, 53);
        snakeLadderPosition.set(99, 41);
    }
    public int getNewPosition(int currentPosition){
        if(currentPosition > 0 && currentPosition <= 100){
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }

    void populatePositionCoordinates(){
        positionCoordinates.add(new Pair(0, 0));
        for(int i = 0; i<SnakeAndLadderGame.height; i++){
            for(int j = 0; j<SnakeAndLadderGame.width; j++){
                int xCord = 0;
                if(i % 2 == 0) xCord = j * SnakeAndLadderGame.tileSize + SnakeAndLadderGame.tileSize/2;
                else xCord = SnakeAndLadderGame.tileSize * SnakeAndLadderGame.width - SnakeAndLadderGame.tileSize/2 - j*SnakeAndLadderGame.tileSize;
                int yCord = SnakeAndLadderGame.height * SnakeAndLadderGame.tileSize - (i * SnakeAndLadderGame.tileSize) - SnakeAndLadderGame.tileSize/2;
                positionCoordinates.add(new Pair(xCord, yCord));
            }
        }
    }

    int getXCoordinate(int position){
        if(position >= 1 && position <= 100) return positionCoordinates.get(position).x;
        return -1;
    }

    int getYCoordinate(int position){
        if(position >= 1 && position <= 100) return positionCoordinates.get(position).y;
        return -1;
    }

//    public static void main(String[] args) {
//        Board board = new Board();
//        for(int i = 0; i<board.positionCoordinates.size(); i++){
//            System.out.println(i+" xCor : "+board.positionCoordinates.get(i).x+", ycord : "+board.positionCoordinates.get(i).y);
//        }
//    }
}
