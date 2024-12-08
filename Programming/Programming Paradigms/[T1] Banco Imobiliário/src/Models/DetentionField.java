package Models;

public class DetentionField extends Field {
    private String appearance;

    // Constructor
    public DetentionField(String appearance) {
        super("Detention");
        this.appearance = appearance;
    }

    // Getters and Setters
    public String getAppearance() {return appearance;}
    public void setAppearance(String appearance) {this.appearance = appearance;}
    
}
