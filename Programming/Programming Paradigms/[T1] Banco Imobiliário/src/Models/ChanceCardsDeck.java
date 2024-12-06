package Models;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class ChanceCardsDeck extends Field {
    private List<ChanceCard> chanceCardsDeck;

    // Constructor
    public ChanceCardsDeck() {
        super(new int[]{1, 5, 11, 15, 20, 24});
        this.chanceCardsDeck = new ArrayList<ChanceCard>();
    }
    
    // Getters and Setters
    public int[] getPosition() {return super.getPosition();}
    public void setPosition(int[] position) {super.setPosition(position);}
    public List<ChanceCard> getChanceCardsDeck() {return chanceCardsDeck;}
    public void setChanceCardsDeck(List<ChanceCard> chanceCardsDeck) {this.chanceCardsDeck = chanceCardsDeck;}

    // Adiciona carta de sorte ou revés ao baralho
    public void addChanceCard(ChanceCard chanceCard) {
        this.chanceCardsDeck.add(chanceCard);
    }
    // Sorteia carta de sorte ou revés
    public ChanceCard chooseChanceCard() {
        Random random = new Random();
        int index = random.nextInt(this.chanceCardsDeck.size());
        return this.chanceCardsDeck.get(index);
    }
}
