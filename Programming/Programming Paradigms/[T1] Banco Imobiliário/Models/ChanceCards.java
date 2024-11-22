import java.util.List;
import java.util.ArrayList;

public class ChanceCards extends Field {
    List<ChanceCard> chanceCardsDeck = new ArrayList<ChanceCard>();

    // Constructor
    public ChanceCards(String appearance, String type, List<ChanceCard> chanceCardsDeck) {
        super(appearance, type);
        this.chanceCardsDeck = new ArrayList<ChanceCard>();
    }

    // Getters and Setters
    public List<ChanceCard> getChanceCardsDeck() {return chanceCardsDeck;}
    public void setChanceCardsDeck(List<ChanceCard> chanceCardsDeck) {this.chanceCardsDeck = chanceCardsDeck;}
}
