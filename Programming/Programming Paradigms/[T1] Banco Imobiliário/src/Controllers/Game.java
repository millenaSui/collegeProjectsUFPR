package Controllers;

// Importing models
import Models.GameBoard;
import Models.Dice;
import Views.GameMenuView;
import Views.MenuCallback;

import Views.GameBoardView;
import Models.Player;
import java.util.Arrays;
import java.util.List;

public class Game implements MenuCallback {
    private GameBoard gameBoard;
    private Dice dice;
    private String[] boardImagePaths;

    // Construtor principal
    public Game() {
        initializeComponents();
    }

    // Inicializa os componentes essenciais do jogo
    private void initializeComponents() {
        this.boardImagePaths = new String[] {
            "./Content/Board1.png",
            "./Content/Board2.png",
            "./Content/Board3.png"
        };
        List<Player> players = Arrays.asList();
        this.gameBoard = GameBoard.getInstance(28, boardImagePaths, players);
        this.dice = new Dice(); // Inicializa os dados
    }

    // Inicializa o jogo
    public void start() {
        GameMenuView menuView = new GameMenuView(this);
        menuView.exhibitMenu();
    }

    // Método de callback para o menu
    @Override
    public void onMenuOptionSelected(int option) {
        if (option == 1) {newGame();} 
        else if (option == 2) {loadGame();}
    }

    private void newGame() {
        System.out.println("Iniciando novo jogo...");
        new GameBoardView(gameBoard); // Cria a visualização do tabuleiro    
        // Adicione lógica adicional de início do jogo
    }

    private void loadGame() {
        System.out.println("Carregando jogo salvo...");
        // Lógica para carregar o jogo salvo
    }
}