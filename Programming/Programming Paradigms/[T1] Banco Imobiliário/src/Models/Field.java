package Models;

public abstract class Field {
    private String appearance;
    private String type;

    // Constructor
    public Field(String appearance, String type) {
        this.appearance = appearance;
        this.type = type;
    }

    // Getters and Setters
    public String getAppearance() {return appearance;}
    public void setAppearance(String appearance) {this.appearance = appearance;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
}
