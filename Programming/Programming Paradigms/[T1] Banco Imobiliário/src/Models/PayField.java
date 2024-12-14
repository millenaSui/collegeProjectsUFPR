package Models;

public class PayField extends Field {
    private String appearance;

    // Constructor
    public PayField(String appearance) {
        super("Pay");
        this.appearance = appearance;
    }

    // Getters and Setters
    public String getAppearance() {return appearance;}
    public void setAppearance(String appearance) {this.appearance = appearance;}
}
