package Models;

public class InitialField extends Field {
    private String appearance;

    // Constructor
    public InitialField(String appearance) {
        super("InitialField");
        this.appearance = appearance;
    }

    // Getters and Setters
    public String getAppearance() {return appearance;}
    public void setAppearance(String appearance) {this.appearance = appearance;}
}
