package Models;

import java.util.List;

public class ChanceCard {
    private List<String> appearance;
    private boolean lucky;

    // Constructor
    public ChanceCard(List<String> appearance, boolean lucky) {
        this.appearance = appearance;
        this.lucky = lucky;
    }

    // Getters and Setters
    public List<String> getAppearance() {return appearance;}
    public void setAppearance(List<String> appearance) {this.appearance = appearance;}
    public boolean getLucky() {return lucky;}
    public void setLucky(boolean lucky) {this.lucky = lucky;}
}
