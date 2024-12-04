public class Main {
    public static void main (String [] args) {
        GameBoard gameBoard = new GameBoard(28); // 28 campos
        Player[] players = {new Player("Player 1"), new Player("Player 2"), new Player("Player 3")};

        // Inicializa o jogo
        Round round = new Round(gameBoard, players);
        Game game = new Game(round);

        game.exhibitGame();
    }
}