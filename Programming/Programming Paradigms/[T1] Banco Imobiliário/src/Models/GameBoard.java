package Models;

import java.util.Random;
import java.util.List;

public class GameBoard {
    private static GameBoard instance = null; // Instância única da classe
    private String appearance;
    private List<Player> players;
    private Field[] fields;

    // Constructor private para garantir instância única
    private GameBoard(int numberOfFields, String[] appearanceList, List<Player> players) {
        this.appearance = this.chooseAppearance(appearanceList); // Escolhe a aparência do tabuleiro
        this.players = players; // Inicializa a lista de jogadores
        this.fields = new Field[numberOfFields];
    }

    // Método para obter instância única da classe
    public static GameBoard getInstance(int numberOfFields, String[] appearance, List<Player> players) {
        if (instance == null) {instance = new GameBoard(numberOfFields, appearance, players);}
        return instance;
    }

    // Getters and Setters
    public String getAppearance() {return appearance;}
    public void setAppearance(String appearance) {this.appearance = appearance;}
    public Field getField(int position) {return fields[position];}
    public void setField(int position, Field field) {this.fields[position] = field;}
    public List<Player> getPlayers() {return players;}
    public void setPlayers(List<Player> players) {this.players = players;}

    // Escolhe aparencia do tabuleiro de maneira aleatória
    public String chooseAppearance(String[] appearance) {
        Random random = new Random();
        int index = random.nextInt(appearance.length);
        return appearance[index];
    }

    // Método para mover jogador
    public void movePlayer(Player player, int steps) {
        int currentPosition = player.getPosition();
        int newPosition = (currentPosition + steps) % fields.length;
        player.setPosition(newPosition);
    }
}