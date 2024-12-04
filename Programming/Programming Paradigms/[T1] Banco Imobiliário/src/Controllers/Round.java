package Controllers;

// Importing libraries


// Importing models
import Models.GameBoard;
import Models.Player;
import Models.Dice;
import Models.Field;

public class Round {
    private GameBoard gameBoard;
    private Player[] players;
    private Dice dice;
    private int currentPlayer;

    // Constructor
    public Round(GameBoard gameBoard, Player[] players) {
        this.gameBoard = gameBoard;
        this.players = players;
        this.dice = new Dice(); // Assuming you need to pass an empty list of sprites
        this.currentPlayer = 0;
    }

    // Getters and Setters
    public GameBoard getGameBoard() {return gameBoard;}
    public void setGameBoard(GameBoard gameBoard) {this.gameBoard = gameBoard;}
    public Player[] getPlayers() {return players;}
    public void setPlayers(Player[] players) {this.players = players;}
    public Dice getDice() {return dice;}
    public void setDice(Dice dice) {this.dice = dice;}
    public int getCurrentPlayer() {return currentPlayer;}
    public void setCurrentPlayer(int currentPlayer) {this.currentPlayer = currentPlayer;}

    // Inicia um novo round
    public void initRound() {
    }
}
