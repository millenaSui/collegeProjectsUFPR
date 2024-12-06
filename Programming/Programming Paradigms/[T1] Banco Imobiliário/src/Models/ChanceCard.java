package Models;

public class ChanceCard {
    private String appearance;
    private int positionsToAdvance; // < 0 se for para voltar, > 0 se for para avançar
    private int boardPosition; // -1 se não for para mover para uma posição específica
    private int money;
    private int roundsNotPlay;
    private boolean playAgain;

    // Constructor
    public ChanceCard(String appearance, int positionsToAdvance, int boardPosition, int money, int roundsNotPlay, boolean playAgain) {
        this.appearance = appearance;
        this.positionsToAdvance = positionsToAdvance; // posições que o personagem deve avançar
        this.boardPosition = boardPosition; // posição do tabuleiro que o personagem deve ir
        this.money = money; // dinheiro que o personagem deve receber ou pagar
        this.roundsNotPlay = roundsNotPlay; // rodadas que o jogador não deve jogar
        this.playAgain = false; // se o jogador deve jogar novamente
    }

    // Getters and Setters
    public String getAppearance() {return appearance;}
    public void setAppearance(String appearance) {this.appearance = appearance;}
    public int getPositionsToAdvance() {return positionsToAdvance;}
    public void setPositionsToAdvance(int positionsToAdvance) {this.positionsToAdvance = positionsToAdvance;}
    public int getBoardPosition() {return boardPosition;}
    public void setBoardPosition(int boardPosition) {this.boardPosition = boardPosition;}
    public int getMoney() {return money;}
    public void setMoney(int money) {this.money = money;}
    public int getRoundsNotPlay() {return roundsNotPlay;}
    public void setRoundsNotPlay(int roundsNotPlay) {this.roundsNotPlay = roundsNotPlay;}
    public boolean getPlayAgain() {return playAgain;}
    public void setPlayAgain(boolean playAgain) {this.playAgain = playAgain;}
}
