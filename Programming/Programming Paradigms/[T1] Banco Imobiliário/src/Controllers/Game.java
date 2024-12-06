package Controllers;

// Importing models
import Models.GameBoard;
import Models.Dice;
import Views.GameMenuView;
import Views.MenuCallback;
import Models.ChanceCardsDeck;
import Models.ChanceCard;

import Views.GameBoardView;
import Views.DiceView;
import Models.Player;
import java.util.Arrays;
import java.util.List;

public class Game implements MenuCallback {
    private GameBoard gameBoard;
    private Dice dice;
    private String[] boardImagePaths;
    private ChanceCardsDeck chanceCardsDeck;

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
        
        // Inicializa o baralho de cartas de sorte ou revés
        chanceCardsDeck = new ChanceCardsDeck();
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card1.png", 2, -1, 0, 0, false));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card2.png", 0, -1, 0, 0, true));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card3.png", 0, -1, 0, 0, true));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card4.png", 1, -1, 0, 0, false));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card5.png", 0, 6, 1000, 0, false));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card6.png", 0, 14, 0, 1, false));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card7.png", 3, -1, 0, 0, false));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card8.png", -2, -1, 0, 0, false));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card9.png", -2, -1, 0, 0, false));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card10.png", 0, -1, -500, 1, false));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card11.png", 0, -1, 0, 1, false));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card12.png", 0, -1, 0, 2, false));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card13.png", -3, -1, 0, 0, false));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card14.png", 0, 0, 0, 0, false));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card13.png", -3, -1, 0, 0, false));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card14.png", 0, 0, 0, 0, false));
        // Adiciona o baralho de cartas de sorte ou revés ao tabuleiro
        for (int i = 0; i < chanceCardsDeck.getPosition().length; i++) {
            gameBoard.setField(i, chanceCardsDeck);
        }
        
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
        // characterSelectView.exhibit();
        // PLAYERSLIST = PEGA POR CALLBACK OS JOGADORES SELECIONADOS COMO INT
        // APPEARANCESLIST = characterSelect.selectAppearance(PASSA OS INT PRA CÁ, DEPENDENDO DO INT, ESCOLHE A IMAGEM DO JOGADOR)
        // atualiza o tabuleiro com os jogadores selecionados
        // WHILE I < APPEARANCESLIST.SIZE()
        // gameBoard.addPlayer(new Player("Player " + i, APPEARANCESLIST[i]));
        gameBoard.setPlayers(Arrays.asList(new Player("Player 1", "./Content/Player/PlayerBlue.png"), new Player("Player 2", "./Content/Player/PlayerRainbow.png"), new Player("Player 3", "./Content/Player/PlayerRed.png"), new Player("Player 4", "./Content/Player/PlayerYellow.png")));
        GameBoardView gameBoardView = new GameBoardView(gameBoard); // Cria a visualização do tabuleiro
        for (int i = 0; i < gameBoard.getPlayers().size(); i++) {
            gameBoardView.addPlayer(gameBoard.getPlayers().get(i));
        }
        DiceView diceView = new DiceView(dice);
        diceView.addToBoard(gameBoardView.getPanel());
        diceView.exhibit(dice, dice.getValue());
        // Adicione lógica adicional de início do jogo
    }

    private void loadGame() {
        System.out.println("Carregando jogo salvo...");
        // Lógica para carregar o jogo salvo
    }
}