package Models;

public class Property extends Field {
    private int buyValue;
    private int rentValue;
    private String appearance;
    private Player owner;

    // Constructor
    public Property(int buyValue, int rentValue, String appearanceString) {
        super("Property");
        this.buyValue = buyValue;
        this.rentValue = rentValue;
        this.appearance = appearanceString;
        this.owner = null;
    }

    // Getters and Setters
    public int getBuyValue() {return buyValue;}
    public void setBuyValue(int buyValue) {this.buyValue = buyValue;}
    public int getRentValue() {return rentValue;}
    public void setRentValue(int rentValue) {this.rentValue = rentValue;}
    public String getAppearance() {return appearance;}
    public void setAppearance(String appearance) {this.appearance = appearance;}
    public Player getOwner() {return owner;}
    public void setOwner(Player owner) {this.owner = owner;}
}
