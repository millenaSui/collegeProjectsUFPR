package Models;

import java.util.List;
import java.util.ArrayList;

public class Prision extends Field {
    private List<Player> arrestedPlayers;

    // Constructor
    public Prision(String appearance, String type, List<Player> arrestedPlayers) {
        super(appearance, type);
        this.arrestedPlayers = new ArrayList<Player>();
    }

    // Getters and Setters
    public List<Player> getArrestedPlayers() {return arrestedPlayers;}
    public void setArrestedPlayers(List<Player> arrestedPlayers) {this.arrestedPlayers = arrestedPlayers;}
}
