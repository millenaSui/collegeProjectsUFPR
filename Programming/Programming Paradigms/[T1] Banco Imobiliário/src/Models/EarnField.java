package Models;

public class EarnField extends Field {
    private String appearance;

    // Constructor
    public EarnField(String appearance) {
        super("Earn");
        this.appearance = appearance;
    }

    // Getters and Setters
    public String getAppearance() {return appearance;}
    public void setAppearance(String appearance) {this.appearance = appearance;}
}