public class Property extends Field {
    private int buyValue;
    private int rentValue;
    private Player owner;

    // Constructor
    public Property(String appearance, String type, int buyValue, int rentValue) {
        super(appearance, type);
        this.buyValue = buyValue;
        this.rentValue = rentValue;
        this.owner = null;
    }

    // Getters and Setters
    public int getBuyValue() {return buyValue;}
    public void setBuyValue(int buyValue) {this.buyValue = buyValue;}
    public int getRentValue() {return rentValue;}
    public void setRentValue(int rentValue) {this.rentValue = rentValue;}
    public Player getOwner() {return owner;}
    public void setOwner(Player owner) {this.owner = owner;}
}
