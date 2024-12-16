package Controllers;

import Models.ChanceCard;
import Models.ChanceCardsDeck;
import Models.DetentionField;
import Models.Dice;
import Models.EarnField;
import Models.GameBoard;
import Models.HolidayField;
import Models.InitialField;
import Models.Memento.GameCaretaker;
import Models.Memento.GameManager;
import Models.Memento.GameStateMemento;
import Models.PayField;
import Models.Player;
import Models.PrisionField;
import Models.Property;
import Views.DiceView;
import Views.GameBoardView;
import Views.GameMenuView;
import Views.MenuCallback;
import Views.SpecialFieldsView;
import java.util.ArrayList;
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
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card1.png", 2, 0, 0));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card2.png", 1, 0, 0));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card3.png", 0, 1000, 0));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card4.png", 0, 0, 1));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card5.png", 3, 0, 0));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card6.png", -2, 0, 0));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card7.png", -2, 0, 0));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card8.png", 0, -500, 1));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card9.png", 0, 0, 1));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card10.png", 0, 0, 2));
        chanceCardsDeck.addChanceCard(new ChanceCard("./Content/ChanceCards/Card11.png", -3, 0, 0));

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
        mercadoJoja = new Property(10000, 1000, "./Content/Properties/Morris.png");
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
        else if (option == 3) {saveGame();}
    }

    private final GameCaretaker caretaker = new GameCaretaker(); // Para armazenar os mementos
    private GameManager gameManager; // Gerenciador do estado do jogo

    private void newGame() {
        DiceView diceView = new DiceView(dice);
        SpecialFieldsView specialFieldsView = new SpecialFieldsView();
        GameBoardView gameBoardView = new GameBoardView(gameBoard);
        for (Player player : gameBoard.getPlayers()) {gameBoardView.addPlayer(player);}
        final int[] i = {0};
        gameManager = new GameManager(gameBoard);
        roundManager(diceView, specialFieldsView, gameBoardView, i);
    }

    public void loadGame() {
        GameStateMemento savedState = caretaker.loadFromDisk(); // Carrega o memento salvo do disco
        if (savedState != null) {
            System.out.println("Jogo carregado com sucesso!");
    
            
            // Inicializa o GameManager com o estado carregado
            if (gameManager == null) {
                List<Player> players = new ArrayList<>(); // Lista vazia, será restaurada
                GameBoard gameBoard = GameBoard.getInstance(28, new String[]{"Classic", "Modern"}, players);
                gameManager = new GameManager(gameBoard);
            }
            gameManager.loadGame(savedState); // Restaura o estado dos jogadores no GameManager
    
            // Configura os elementos da interface e do tabuleiro
            DiceView diceView = new DiceView(dice);
            SpecialFieldsView specialFieldsView = new SpecialFieldsView();
            GameBoardView gameBoardView = new GameBoardView(gameManager.getGameBoard());
            for (Player player : gameManager.getGameBoard().getPlayers()) {
                gameBoardView.addPlayer(player);
                gameBoardView.updatePlayerPosition(player, player.getPosition());
            }
    
            // Variável para rastrear o índice do jogador atual
            final int[] i = {gameManager.getCurrentPlayerIndex()}; // Começa com o jogador restaurado
    
            roundManager(diceView, specialFieldsView, gameBoardView, i);
        }
    }


    public void saveGame() {
        if (gameManager != null) {
            GameStateMemento memento = gameManager.saveGame();
            caretaker.saveToDisk(memento); // Grava o memento no disco
        } else {
            System.out.println("Nenhum jogo em andamento para salvar.");
        }
    }

    private void roundManager(DiceView diceView, SpecialFieldsView specialFieldsView, GameBoardView gameBoardView, int[] i) {
        synchronized (this) {
            new Thread(() -> {
                while (true) {
                    gameBoardView.displayPlayerInfo(gameBoard.getPlayers().get(i[0]));
                    saveGame(); // Salvar automaticamente ao fim de cada turno

                    // Se o jogador falir, indica que ele perdeu e o remove do jogo
                    if (gameBoard.getPlayers().get(i[0]).getMoney() <= 0) {
                        gameBoard.getPlayers().remove(i[0]);
                        gameBoardView.removePlayer(gameBoard.getPlayers().get(i[0]));
                        System.out.print("Jogador " + gameBoard.getPlayers().get(i[0]).getName() + " faliu!");

                        // Se restar apenas um jogador, ele vence
                        if (gameBoard.getPlayers().size() == 1) {
                            System.out.print("Jogador " + gameBoard.getPlayers().get(0).getName() + " venceu!");
                            break;
                        }
                    
                    // Se o jogador estiver na detenção, não o deixa se mover até que o tempo acabe
                    } else if (gameBoard.getPlayers().get(i[0]).getInDetention() > 0 && gameBoard.getPlayers().get(i[0]).getInJail() == false) {
                        gameBoard.getPlayers().get(i[0]).setInDetention(gameBoard.getPlayers().get(i[0]).getInDetention() - 1);
                    
                    } // Se o jogador estiver na prisão, não o deixa se mover até que pague a fiança ou fique 5 rodadas 
                    else if (gameBoard.getPlayers().get(i[0]).getInJail() && gameBoard.getPlayers().get(i[0]).getInDetention() != 0) {
                        specialFieldsView.addToBoard(gameBoardView.getPanel());
                        try {Thread.sleep(2500);} catch (InterruptedException e) {e.printStackTrace();}

                        // Configura o listener antes de criar os botões
                        specialFieldsView.setButtonClickListener(button -> {
                            if (button.equals("YES") && gameBoard.getPlayers().get(i[0]).getMoney() > 2000) {
                                gameBoard.getPlayers().get(i[0]).setInJail(false);
                                gameBoard.getPlayers().get(i[0]).setMoney(gameBoard.getPlayers().get(i[0]).getMoney() - 2000);
                                gameBoard.getPlayers().get(i[0]).setInDetention(0);
                                specialFieldsView.clearPanel();
                            } else {
                                specialFieldsView.clearPanel();
                            }
                        });

                        gameBoard.getPlayers().get(i[0]).setInJail(true);
                        gameBoard.getPlayers().get(i[0]).setInDetention(gameBoard.getPlayers().get(i[0]).getInDetention() - 1);
                        specialFieldsView.createPrisionButtons();
                        specialFieldsView.exhibit("./Content/SpecialFields/Prision.png");
                        try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
                        specialFieldsView.clearPanel();
                    
                    } else { // Jogador não está em detenção ou prisão, faz uma jogada normal 
                    
                        // Rola o dado e exibe o resultado
                        diceView.addToBoard(gameBoardView.getPanel());
                        int rollDice = dice.rollDice();
                        diceView.exhibit(dice, rollDice);
                        try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
                        diceView.clearDice();

                        // Move o jogador e exibe a nova posição
                        gameBoardView.updatePlayerPosition(gameBoard.getPlayers().get(i[0]), rollDice);
                        gameBoard.movePlayer(gameBoard.getPlayers().get(i[0]), rollDice);
                        
                        // Se o jogador cair em um campo de sorte ou revés, exibe a carta e aplica o efeito
                        if (gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition()).getType().equals("Chance Cards Deck")) {
                            
                            ChanceCard choosen = chanceCardsDeck.chooseChanceCard();
                            specialFieldsView.addToBoard(gameBoardView.getPanel());
                            try {Thread.sleep(2500);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.exhibit(choosen.getAppearance());
                            try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.clearPanel();
                            
                            // Avançar ou retroceder um número específico de posições*/
                            if (choosen.getPositionsToAdvance() != 0) {
                                gameBoardView.updatePlayerPosition(gameBoard.getPlayers().get(i[0]), choosen.getPositionsToAdvance());
                                gameBoard.movePlayer(gameBoard.getPlayers().get(i[0]), choosen.getPositionsToAdvance());
                            } // Pagar ou receber uma quantia específica
                            if (choosen.getMoney() != 0) {
                                gameBoard.getPlayers().get(i[0]).setMoney(gameBoard.getPlayers().get(i[0]).getMoney() + choosen.getMoney());
                            } // Ficar sem jogar por um número específico de rodadas
                            if (choosen.getRoundsNotPlay() > 0) {
                                gameBoard.getPlayers().get(i[0]).setInDetention(choosen.getRoundsNotPlay());
                            }
                        } 
                        // Se o jogador cair em um campo de propriedade, exibe a propriedade e dá a opção de comprar ou pagar aluguel
                        else if (gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition()).getType().equals("Property")) {
                            specialFieldsView.addToBoard(gameBoardView.getPanel());
                            try {Thread.sleep(2500);} catch (InterruptedException e) {e.printStackTrace();}

                            // Configura o listener antes de criar os botões
                            specialFieldsView.setButtonClickListener(button -> { 
                                if (button.equals("BUY")) { // Se o jogador escolher comprar a propriedade
                                    
                                    // Se a propriedade não tiver dono
                                    if (((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getOwner() == null) {
                                        
                                        // Se o jogador tiver o dinheiro da compra, compra a propriedade
                                        if (gameBoard.getPlayers().get(i[0]).getMoney() > ((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getBuyValue()) { 
                                            ((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).setOwner(gameBoard.getPlayers().get(i[0]));
                                            gameBoard.getPlayers().get(i[0]).setMoney(gameBoard.getPlayers().get(i[0]).getMoney() - ((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getBuyValue());
                                            specialFieldsView.clearPanel();
                                        // Se ele não tiver o dinheiro da compra não faz nada
                                        } else { specialFieldsView.clearPanel();}

                                    // Se a propriedade tiver dono e o jogador não o for, desconta o aluguel
                                    } else if (((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getOwner() != gameBoard.getPlayers().get(i[0])) { 
                                        gameBoard.getPlayers().get(i[0]).setMoney(gameBoard.getPlayers().get(i[0]).getMoney() - ((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getRentValue());
                                        ((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getOwner().setMoney(((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getOwner().getMoney() + ((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getRentValue());
                                        specialFieldsView.clearPanel();
                                    
                                    // Se a propriedade tiver dono e o jogador for o dono, não faz nada
                                    } else {specialFieldsView.clearPanel();}
                                
                                } else if (button.equals("RENT")) { // Se o jogador escolher pagar o aluguel
                                    
                                    // Se a propriedade tiver dono e o jogador não o for, desconta o aluguel
                                    if (((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getOwner() != null) {
    
                                        if (((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getOwner() != gameBoard.getPlayers().get(i[0])) {
                                            gameBoard.getPlayers().get(i[0]).setMoney(gameBoard.getPlayers().get(i[0]).getMoney() - ((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getRentValue());
                                            ((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getOwner().setMoney(((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getOwner().getMoney() + ((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getRentValue());
                                            specialFieldsView.clearPanel();
                                        }
                                    } else {specialFieldsView.clearPanel();}
                                }
                            });                                
                            specialFieldsView.createPropertyButtons();
                            specialFieldsView.exhibit(((Property) gameBoard.getField(gameBoard.getPlayers().get(i[0]).getPosition())).getAppearance());
                            try {Thread.sleep(10000);} catch (InterruptedException e) {e.printStackTrace();}
                            specialFieldsView.clearPanel();
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
 
                            // Configura o listener antes de criar os botões
                            specialFieldsView.setButtonClickListener(button -> {
                                if (button.equals("YES") && gameBoard.getPlayers().get(i[0]).getMoney() > 2000) {
                                    gameBoard.getPlayers().get(i[0]).setInJail(false);
                                    gameBoard.getPlayers().get(i[0]).setMoney(gameBoard.getPlayers().get(i[0]).getMoney() - 2000);
                                    gameBoard.getPlayers().get(i[0]).setInDetention(0);
                                    specialFieldsView.clearPanel();
                                } else {
                                    specialFieldsView.clearPanel();
                                }
                            });

                            gameBoard.getPlayers().get(i[0]).setInJail(true);
                            gameBoard.getPlayers().get(i[0]).setInDetention(5);

                            specialFieldsView.createPrisionButtons();
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
                    gameBoardView.clearPlayerInfo();
                }
            }).start();
        }
    }
}