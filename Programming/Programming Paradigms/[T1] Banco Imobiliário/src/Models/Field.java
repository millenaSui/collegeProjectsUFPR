package Models;

public abstract class Field {
    private String type;

    // Constructor
    public Field(String type) {
        this.type = type;
    }

    // Getters and Setters
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
}
