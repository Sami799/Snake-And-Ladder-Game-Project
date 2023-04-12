package com.example.snakeandladdergame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeAndLadderGame extends Application {
    public static final int tileSize = 40;
    public static final int height = 10;
    public static final int width = 10;
    private static Dice dice = new Dice();
    private Player player1, player2;
    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;
    final int buttonLine = height*tileSize + 50;
    final int infoLine = buttonLine - 30;
    public static void main(String[] args) {
        launch(args);
    }
    public Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(height*tileSize, width*tileSize+100);
        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }
        Image img = new Image("C:\\Users\\HP\\OneDrive\\Desktop\\projects\\snake and ladder\\SnakeAndLadderGame\\src\\main\\snake-and-ladder-board-pic-3.jpg");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("roll dice");
        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(30);
        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(310);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(175);

        Button playerOneButton = new Button("player 1");
        Button playerTwoButton = new Button("player 2");
        Button startButton = new Button("START");

        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(30);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(310);
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(175);

        player1 = new Player("Ajay", tileSize, Color.BLACK);
        player2 = new Player("Brock", tileSize-15, Color.WHITE);

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : "+diceValue);
                        player1.move(diceValue);
                        if(player1.isWinner()){
                            diceLabel.setText("winner is "+player1.name);
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("your turn "+player1.name);

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                        }
                        else{
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn "+player2.getName());
                        }
                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : "+diceValue);
                        player2.move(diceValue);
                        if(player2.isWinner()){
                            diceLabel.setText("winner is "+player2.name);
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("your turn "+player1.name);

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted = false;
                        }
                        else{
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn "+player1.getName());

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                        }
                    }
                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                startButton.setDisable(true);
                diceLabel.setText("Game Started");
                playerOneTurn = true;
                playerOneLabel.setText("Your Turn "+player1.getName());
                playerOneButton.setDisable(false);
                player1.startingPoint();
                playerTwoTurn = false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                player2.startingPoint();
            }
        });

        root.getChildren().addAll(board, playerOneButton, playerTwoButton, startButton, playerOneLabel, playerTwoLabel, diceLabel, player1.getCoin(), player2.getCoin());
        return root;
    }
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());
        stage.setScene(scene);
        stage.setTitle("Snake And Ladder Game!");
        stage.show();
    }
}