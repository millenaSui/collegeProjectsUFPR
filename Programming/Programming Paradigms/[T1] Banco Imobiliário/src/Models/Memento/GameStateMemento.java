package Models.Memento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Memento - Armazena o estado do jogo
public class GameStateMemento implements Serializable {
    private static final long serialVersionUID = 1L; // Recomendado para serialização

    private final List<PlayerState> playersState;
    private final int currentPlayerIndex;

    public GameStateMemento(List<PlayerState> playersState, int currentPlayerIndex) {
        this.playersState = new ArrayList<>(playersState);
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public List<PlayerState> getPlayersState() {
        return playersState;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    // Classe estática interna para salvar o estado de cada jogador
    public static class PlayerState implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String appearance;
        private final String name;
        private final int money;
        private final int position;
        private final boolean inJail;
        private final int detentionRounds;

        public PlayerState(String appearance, String name, int money, int position, boolean inJail, int detentionRounds) {
            this.appearance = appearance;
            this.name = name;
            this.money = money;
            this.position = position;
            this.inJail = inJail;
            this.detentionRounds = detentionRounds;
        }

        public String getAppearance() {
            return appearance;
        }

        public String getName() {
            return name;
        }

        public int getMoney() {
            return money;
        }

        public int getPosition() {
            return position;
        }

        public boolean isInJail() {
            return inJail;
        }

        public int getDetentionRounds() {
            return detentionRounds;
        }
    }
}
