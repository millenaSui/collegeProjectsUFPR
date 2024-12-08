package Models;

public class PrisionField extends Field {
    private String appearance;

    // Constructor
    public PrisionField(String appearance) {
        super("Prision");
        this.appearance = appearance;
    }

    // Getters and Setters
    public String getAppearance() {return appearance;}
    public void setAppearance(String appearance) {this.appearance = appearance;}
}
