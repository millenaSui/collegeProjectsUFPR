package Controllers;

import Models.GameBoard;
import Models.Dice;
import Models.ChanceCardsDeck;
import Models.ChanceCard;
import Models.Player;
import Models.Property;
import Models.InitialField;
import Models.DetentionField;
import Models.HolidayField;
import Models.PrisionField;
import Models.PayOrEarnField;

import Views.GameMenuView;
import Views.MenuCallback;
import Views.GameBoardView;
import Views.DiceView;

import java.util.Arrays;
import java.util.List;

public class Game implements MenuCallback {
    // Componentes
    private GameBoard gameBoard;
    private String[] boardImagePaths;
    private Dice dice;
    // Propriedades
    private Property museuGunther;
    private Property armazemPierre;
    private Property mercadoJoja;
    private Property atelieEmily;
    private Property cassinoSenhorQI;
    private Property oficinaClint;
    private Property ranchoMarnie;
    private Property laboratorioDemetrius;
    private Property clinicaHarvey;
    private Property torreMago;
    private Property saloonGus;
    private Property peixariaWilly;
    private Property oasisSandy;
    private Property guildaMarlon;
    private Property escritorioProfessorCaracol;
    private Property carpintariaRobin;
    // Campos especiais
    private InitialField initialField;
    private DetentionField detentionField;
    private HolidayField holidayField;
    private PrisionField prisionField;
    private PayOrEarnField earnField;
    private PayOrEarnField payField;
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

        // Lista de jogadores
        List<Player> players = Arrays.asList(
            new Player("Player 1", "./Content/Player/PlayerBlue.png"),
            new Player("Player 2", "./Content/Player/PlayerRainbow.png"),
            new Player("Player 3", "./Content/Player/PlayerRed.png"),
            new Player("Player 4", "./Content/Player/PlayerYellow.png")
        );

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
        gameBoard.setField(1, chanceCardsDeck);
        gameBoard.setField(5, chanceCardsDeck);
        gameBoard.setField(11, chanceCardsDeck);
        gameBoard.setField(15, chanceCardsDeck);
        gameBoard.setField(20, chanceCardsDeck);
        gameBoard.setField(24, chanceCardsDeck);
        
        // Inicializa os campos de propriedade
        museuGunther = new Property(5000, 500, "./Content/Properties/Gunther.png");
        gameBoard.setField(2, museuGunther);
        armazemPierre = new Property(2000, 200, "./Content/Properties/Pierre.png");
        gameBoard.setField(3, armazemPierre);
        mercadoJoja = new Property(10000, 1000, "./Content/Properties/Joja.png");
        gameBoard.setField(4, mercadoJoja);
        atelieEmily = new Property(1000, 100, "./Content/Properties/Emily.png");
        gameBoard.setField(7, atelieEmily);
        cassinoSenhorQI = new Property(10000, 1000, "./Content/Properties/SenhorQI.png");
        gameBoard.setField(8, cassinoSenhorQI);
        oficinaClint = new Property(2000, 200, "./Content/Properties/Clint.png");
        gameBoard.setField(10, oficinaClint);
        ranchoMarnie = new Property(5000, 500, "./Content/Properties/Marnie.png");
        gameBoard.setField(12, ranchoMarnie);
        laboratorioDemetrius = new Property(1000, 100, "./Content/Properties/Demetrius.png");
        gameBoard.setField(13, laboratorioDemetrius);
        clinicaHarvey = new Property(5000, 500, "./Content/Properties/Harvey.png");
        gameBoard.setField(16, clinicaHarvey);
        torreMago = new Property(10000, 1000, "./Content/Properties/Mago.png");
        gameBoard.setField(17, torreMago);
        saloonGus = new Property(2000, 200, "./Content/Properties/Gus.png");
        gameBoard.setField(18, saloonGus);
        peixariaWilly = new Property(1000, 100, "./Content/Properties/Willy.png");
        gameBoard.setField(19, peixariaWilly);
        oasisSandy = new Property(5000, 500, "./Content/Properties/Sandy.png");
        gameBoard.setField(22, oasisSandy);
        guildaMarlon = new Property(1000, 100, "./Content/Properties/Marlon.png");
        gameBoard.setField(25, guildaMarlon);
        escritorioProfessorCaracol = new Property(2000, 200, "./Content/Properties/ProfessorCaracol.png");
        gameBoard.setField(26, escritorioProfessorCaracol);
        carpintariaRobin = new Property(10000, 1000, "./Content/Properties/Robin.png");
        gameBoard.setField(27, carpintariaRobin);

        // Inicializa os campos especiais
        initialField = new InitialField("./Content/SpecialFields/InitField.png");
        gameBoard.setField(0, initialField);
        earnField = new PayOrEarnField("./Content/SpecialFields/Earn.png", "earn");
        gameBoard.setField(6, earnField);
        payField = new PayOrEarnField("./Content/SpecialFields/Pay.png", "pay");
        gameBoard.setField(21, payField);
        prisionField = new PrisionField("./Content/SpecialFields/Prision.png");
        gameBoard.setField(9, prisionField);
        holidayField = new HolidayField("./Content/SpecialFields/Holiday.png");
        gameBoard.setField(14, holidayField);
        detentionField = new DetentionField("./Content/SpecialFields/Detention.png");
        gameBoard.setField(23, detentionField);
    }

    // Inicializa o jogo
    public void start() {
        GameMenuView menuView = new GameMenuView(this);
        menuView.exhibitMenu();
    }

    @Override // Callback para o menu de opções
    public void onMenuOptionSelected(int option) {
        if (option == 1) {newGame();} 
        else if (option == 2) {loadGame();}
    }

    private void newGame() {
        System.out.println("Iniciando novo jogo...");

        DiceView diceView = new DiceView(dice);
        // Inicializa a visão do tabuleiro com os jogadores
        GameBoardView gameBoardView = new GameBoardView(gameBoard, diceView);
        for (Player player : gameBoard.getPlayers()) {gameBoardView.addPlayer(player);}
        diceView.addToBoard(gameBoardView.getPanel());

        // Inicia o turno de cada jogador de forma controlada
        startPlayerTurn(gameBoardView, diceView);
    }

    private void loadGame() {
        System.out.println("Carregando jogo salvo...");
        // Lógica para carregar o jogo salvo
    }

    private void startPlayerTurn(GameBoardView gameBoardView, DiceView diceView) {
        // Começa com o Player 4
        int rollDice = dice.rollDice();
        diceView.exhibit(dice, rollDice);
        gameBoard.movePlayer(gameBoard.getPlayers().get(0), rollDice);
        gameBoardView.updatePlayerPosition(gameBoard.getPlayers().get(0), rollDice);

        if (gameBoard.getField(gameBoard.getPlayers().get(0).getPosition()).getType() == "Chance Cards Deck") {
            ChanceCard choosen = chanceCardsDeck.chooseChanceCard();
            System.out.println(choosen.getAppearance());
            return;
        } else if (gameBoard.getField(gameBoard.getPlayers().get(0).getPosition()).getType() == "Property") {
            System.out.println("Você caiu em uma propriedade!");
            return;
        } else if (gameBoard.getField(gameBoard.getPlayers().get(0).getPosition()).getType() == "Pague ou receba") {
            System.out.println("Você caiu em um pague ou receba!");
            return;
        } else if (gameBoard.getField(gameBoard.getPlayers().get(0).getPosition()).getType() == "Prisão") {
            System.out.println("Você caiu na prisão!");
            return;
        } else if (gameBoard.getField(gameBoard.getPlayers().get(0).getPosition()).getType() == "Detenção") {
            System.out.println("Você caiu em um deck de cartas de sorte ou revés!");
            return;
        } else if (gameBoard.getField(gameBoard.getPlayers().get(0).getPosition()).getType() == "Início") {
            System.out.println("Você caiu no início!");
            return;
        } else if (gameBoard.getField(gameBoard.getPlayers().get(0).getPosition()).getType() == "Feriado") {
            System.out.println("Você caiu em um feriado!");
            return;
        }
    }


}