package Models.Memento;

import java.util.ArrayList;
import java.util.List;

import Models.GameBoard;
import Models.Player;

// Originator - Responsável por criar e restaurar o Memento
public class GameManager {
    private final GameBoard gameBoard;
    private int currentPlayerIndex;

    // Construtor - Inicializa o GameManager com o GameBoard
    public GameManager(GameBoard gameBoard) {
        if (gameBoard == null) {
            throw new IllegalArgumentException("O GameBoard não pode ser nulo.");
        }
        this.gameBoard = gameBoard;
        this.currentPlayerIndex = 0;
    }

    // Cria um memento com o estado atual do jogo
    public GameStateMemento saveGame() {
        List<GameStateMemento.PlayerState> playerStates = new ArrayList<>();
        
        for (Player player : gameBoard.getPlayers()) {
            playerStates.add(new GameStateMemento.PlayerState(
                player.getAppearance(),
                player.getName(),
                player.getMoney(),
                player.getPosition(),
                player.getInJail(),
                player.getInDetention()
            ));
        }
        
        return new GameStateMemento(playerStates, currentPlayerIndex);
    }

    // Restaura o estado do jogo a partir de um memento
    public void loadGame(GameStateMemento memento) {
        if (memento == null) {
            throw new IllegalArgumentException("O memento não pode ser nulo.");
        }

        // Limpa os jogadores do GameBoard antes de carregar o novo estado
        gameBoard.setPlayers(new ArrayList<>());

        for (GameStateMemento.PlayerState state : memento.getPlayersState()) {
            Player player = new Player(state.getName(), state.getAppearance());
            player.setMoney(state.getMoney());
            player.setPosition(state.getPosition());
            player.setInJail(state.isInJail());
            player.setInDetention(state.getDetentionRounds());
            gameBoard.getPlayers().add(player); // Adiciona os jogadores ao GameBoard
        }
        
        this.currentPlayerIndex = memento.getCurrentPlayerIndex();
    }

    // Retorna o índice do jogador atual
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    // Atualiza o índice do jogador atual
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        if (currentPlayerIndex < 0 || currentPlayerIndex >= gameBoard.getPlayers().size()) {
            throw new IndexOutOfBoundsException("Índice do jogador atual inválido.");
        }
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
