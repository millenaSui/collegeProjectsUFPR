package Models;

public class PayOrEarnField extends Field {
    private String appearance;
    private String type;

    // Constructor
    public PayOrEarnField(String appearance, String type) {
        super("PayOrEarn");
        this.appearance = appearance;
        this.type = type;
    }

    // Getters and Setters
    public String getAppearance() {return appearance;}
    public void setAppearance(String appearance) {this.appearance = appearance;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
}
