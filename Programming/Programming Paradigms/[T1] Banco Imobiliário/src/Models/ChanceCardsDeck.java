package Models;

import Models.ChanceCard;

import java.util.List;
import java.util.ArrayList;

public class ChanceCardsDeck extends Field {
    private List<ChanceCard> chanceCardsDeck;

    // Constructor
    public ChanceCardsDeck(String appearance, String type) {
        super(appearance, type);
        this.chanceCardsDeck = new ArrayList<ChanceCard>();
    }
    
    // Getters and Setters
    public List<ChanceCard> getChanceCardsDeck() {return chanceCardsDeck;}
    public void setChanceCardsDeck(List<ChanceCard> chanceCardsDeck) {this.chanceCardsDeck = chanceCardsDeck;}
}
        
