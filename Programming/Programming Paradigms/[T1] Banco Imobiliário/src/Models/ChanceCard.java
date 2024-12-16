package Models;

public class ChanceCard {
    private String appearance;
    private int positionsToAdvance; // < 0 se for para voltar, > 0 se for para avançar
    private int money;
    private int roundsNotPlay;

    // Constructor
    public ChanceCard(String appearance, int positionsToAdvance, int money, int roundsNotPlay) {
        this.appearance = appearance;
        this.positionsToAdvance = positionsToAdvance; // posições que o personagem deve avançar
        this.money = money; // dinheiro que o personagem deve receber ou pagar
        this.roundsNotPlay = roundsNotPlay; // rodadas que o jogador não deve jogar
    }

    // Getters and Setters
    public String getAppearance() {return appearance;}
    public void setAppearance(String appearance) {this.appearance = appearance;}
    public int getPositionsToAdvance() {return positionsToAdvance;}
    public void setPositionsToAdvance(int positionsToAdvance) {this.positionsToAdvance = positionsToAdvance;}
    public int getMoney() {return money;}
    public void setMoney(int money) {this.money = money;}
    public int getRoundsNotPlay() {return roundsNotPlay;}
    public void setRoundsNotPlay(int roundsNotPlay) {this.roundsNotPlay = roundsNotPlay;}
}
