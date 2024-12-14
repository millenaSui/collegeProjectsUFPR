package Controllers;

import Models.GameBoard;
import Models.Player;
import Models.Dice;
import Models.Property;
import Models.ChanceCard;
import Models.ChanceCardsDeck;
import Models.InitialField;
import Models.DetentionField;
import Models.HolidayField;
import Models.PrisionField;
import Models.PayField;
import Models.EarnField;

import Views.GameMenuView;
import Views.GameBoardView;
import Views.SpecialFieldsView;
import Views.DiceView;

import Views.MenuCallback;

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
    private PayField payField;
    private EarnField earnField;
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
        earnField = new EarnField("./Content/SpecialFields/Earn.png");
        gameBoard.setField(6, earnField);
        payField = new PayField("./Content/SpecialFields/Pay.png");
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
        DiceView diceView = new DiceView(dice);
        SpecialFieldsView specialFieldsView = new SpecialFieldsView();
        GameBoardView gameBoardView = new GameBoardView(gameBoard);
        for (Player player : gameBoard.getPlayers()) {gameBoardView.addPlayer(player);}
        final int[] i = {0};

        synchronized (this) {
            new Thread(() -> {
                while (true) {

                    if (gameBoard.getPlayers().get(i[0]).getInDetention() > 0) {
                        gameBoard.getPlayers().get(i[0]).setInDetention(gameBoard.getPlayers().get(i[0]).getInDetention() - 1);
                    } else if (gameBoard.getPlayers().get(i[0]).getInJail()) {
                        // inJailView.exhibit(player);
                        if (gameBoard.getPlayers().get(i[0]).getMoney() >= 2000/* && jogador quer pagar a fiança*/) {
                            gameBoard.getPlayers().get(i[0]).setInJail(false);
                        }
                    } else {
                        // Rola o dado e exibe o resultado
                        diceView.addToBoard(gameBoardView.getPanel());
                        int rollDice = dice.rollDice();
                        diceView.exhibit(dice, rollDice);
                        try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
                        diceView.clearDice();

                        // Move o jogador e exibe a nova posição
                        gameBoard.movePlayer(gameBoard.getPlayers().get(i[0]), rollDice);
                        gameBoardView.updatePlayerPosition(gameBoard.getPlayers().get(i[0]), rollDice);
                        
                        // Se o jogador cair em um campo de sorte ou revés, exibe a carta e aplica o efeito
                        if (gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition()).getType().equals("Chance Cards Deck")) {
                            
                            ChanceCard choosen = chanceCardsDeck.chooseChanceCard();
                            specialFieldsView.addToBoard(gameBoardView.getPanel());
                            try {Thread.sleep(2500);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.exhibit(choosen.getAppearance());
                            try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.clearPanel();
                            
                            // Ir para uma posição específica
                            /*if (choosen.getBoardPosition() != -1) {
                                gameBoard.movePlayer(gameBoard.getPlayers().get(i[0]), choosen.getBoardPosition()-gameBoard.getPlayers().get(i[0]).getPosition());
                                gameBoardView.updatePlayerPosition(gameBoard.getPlayers().get(i[0]), choosen.getBoardPosition()-gameBoard.getPlayers().get(i[0]).getPosition());
                            } // Avançar ou retroceder um número específico de posições*/
                            if (choosen.getPositionsToAdvance() != 0) {
                                gameBoard.movePlayer(gameBoard.getPlayers().get(i[0]), choosen.getPositionsToAdvance());
                                gameBoardView.updatePlayerPosition(gameBoard.getPlayers().get(i[0]), choosen.getPositionsToAdvance());
                            } // Pagar ou receber uma quantia específica
                            if (choosen.getMoney() != 0) {
                                gameBoard.getPlayers().get(i[0]).setMoney(gameBoard.getPlayers().get(i[0]).getMoney() + choosen.getMoney());
                            } // Ficar sem jogar por um número específico de rodadas
                            if (choosen.getRoundsNotPlay() > 0) {
                                gameBoard.getPlayers().get(i[0]).setInDetention(choosen.getRoundsNotPlay());
                            } // Jogar novamente
                            if (choosen.getPlayAgain()) {
                                i[0]--;
                            }
                        } 
                        // Se o jogador cair em um campo de propriedade, exibe a propriedade e dá a opção de comprar ou pagar aluguel
                        else if (gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition()).getType().equals("Property")) {
                            System.out.println("Você caiu em uma propriedade!");
                        } 
                        // Se o jogador cair em um campo de pagamento ou recebimento, verifica o tipo e aplica o efeito
                        else if (gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition()).getType().equals("Earn")) {
            
                            specialFieldsView.addToBoard(gameBoardView.getPanel());
                            try {Thread.sleep(2500);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.exhibit("./Content/SpecialFields/Earn.png");
                            try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.clearPanel();
                            gameBoard.getPlayers().get(i[0]).setMoney(gameBoard.getPlayers().get(i[0]).getMoney() + (1000 + (int)(Math.random() * ((3000 - 1000) + 1))));

                        } else if (gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition()).getType().equals("Pay")) {
                            specialFieldsView.addToBoard(gameBoardView.getPanel());
                            try {Thread.sleep(2500);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.exhibit("./Content/SpecialFields/Pay.png");
                            try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.clearPanel();

                            gameBoard.getPlayers().get(i[0]).setMoney(gameBoard.getPlayers().get(i[0]).getMoney() - (500 + (int)(Math.random() * ((1000 - 500) + 1))));
                        }
                        // Se o jogador cair no campo de prisão, não o deixa se mover até que pague a fiança
                        else if (gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition()).getType().equals("Prision")) {
                            specialFieldsView.addToBoard(gameBoardView.getPanel());
                            try {Thread.sleep(2500);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.exhibit("./Content/SpecialFields/Prision.png");
                            try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.clearPanel();
                        } 
                        // Se o jogador cair no campo de detenção, não o deixa se mover por 3 rodadas
                        else if (gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition()).getType().equals("Detention")) {
                            specialFieldsView.addToBoard(gameBoardView.getPanel());
                            try {Thread.sleep(2500);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.exhibit("./Content/SpecialFields/Detention.png");
                            try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.clearPanel();

                            gameBoard.getPlayers().get(i[0]).setInDetention(3);
                        } 
                        // Se o jogador cair no campo de início, recebe um bônus
                        else if (gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition()).getType().equals("InitialField")) {
                            specialFieldsView.addToBoard(gameBoardView.getPanel());
                            try {Thread.sleep(2500);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.exhibit("./Content/SpecialFields/InitField.png");
                            try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.clearPanel();

                            gameBoard.getPlayers().get(i[0]).setMoney(gameBoard.getPlayers().get(i[0]).getMoney() + 1000);
                        } 
                        // Se o jogador cair no campo de feriado, não o deixa se mover por 1 rodada
                        else if (gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition()).getType().equals("Holiday")) {
                            specialFieldsView.addToBoard(gameBoardView.getPanel());
                            try {Thread.sleep(2500);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.exhibit("./Content/SpecialFields/Holiday.png");
                            try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.clearPanel();

                            gameBoard.getPlayers().get(i[0]).setInDetention(1);
                        }
                    }
                    if (i[0] == 3) {i[0] = 0;} else {i[0]++;}
                }
            }).start();
        }
    }

    
    public void loadGame() {
        System.out.println("Carregando jogo salvo...");
        // Lógica para carregar o jogo salvo
    }
}