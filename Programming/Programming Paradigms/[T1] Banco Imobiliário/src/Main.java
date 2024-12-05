import Models.GameBoard;
import Views.GameBoardView;
import Views.PlayerView;
import Models.Player;
import Controllers.Round;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        /* Configura e inicializa o tabuleiro */ 
        List<String> boardImagePaths = Arrays.asList(
            "./Content/Board1.png",
            "./Content/Board2.png",
            "./Content/Board3.png"
        );
        GameBoard gameBoard = new GameBoard(28, boardImagePaths);
        new GameBoardView(gameBoard); // Cria a visualização do tabuleiro

        /* Configura e inicializa jogadores */
        List<String> playerNames = Arrays.asList("Player1", "Player2");
        List<String> playerColors = Arrays.asList("Red", "Rainbow");
        // +++ FUNÇÃO PARA SELECIONAR JOGADORES (máximo de 4), solicita nome e cor
        List<String> playerImagePaths = Arrays.asList(
            "./Content/Player/PlayerRed.png",
            "./Content/Player/PlayerRainbow.png",
            "./Content/PlayerBlue.png",
            "./Content/PlayerGreen.png",
            "./Content/PlayerPink.png",
            "./Content/PlayerPurple.png",
            "./Content/PlayerWhite.png",
            "./Content/PlayerRainbow.png"
        );
        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < playerNames.size(); i++) {
            Player player = new Player(playerNames.get(i), playerColors.get(i), playerImagePaths.get(i));
            players.add(player);
            GameBoardView gameBoardView = new GameBoardView(gameBoard);
            gameBoardView.addPlayer(player);
        }

        /* Inicializa rodada */
        //Round round = new Round(gameBoard, players);
    }
}