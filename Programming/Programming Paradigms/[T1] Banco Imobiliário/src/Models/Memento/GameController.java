package Models.Memento;

import Models.GameBoard;
import Models.Player;

import java.util.ArrayList;
import java.util.List;

// GameController ou outra classe de controle principal
public class GameController {
    private final GameCaretaker caretaker = new GameCaretaker();
    private GameManager gameManager;

    public void saveGame() {
        if (gameManager != null) {
            GameStateMemento memento = gameManager.saveGame();
            caretaker.saveToDisk(memento); // Salva em disco
        } else {
            System.out.println("Nenhum jogo em andamento para salvar.");
        }
    }

    public void loadGame() {
        GameStateMemento savedState = caretaker.loadFromDisk(); // Carrega de disco
        if (savedState != null) {
            if (gameManager == null) {
                // Inicializa o GameManager se não existir
                List<Player> players = new ArrayList<>(); // Crie uma lista vazia para o GameBoard
                GameBoard gameBoard = GameBoard.getInstance(28, new String[]{"Classic", "Modern"}, players);
                gameManager = new GameManager(gameBoard);
            }
            gameManager.loadGame(savedState); // Restaura o estado do jogo
        } else {
            System.out.println("Nenhum jogo salvo encontrado.");
        }
    }
}
