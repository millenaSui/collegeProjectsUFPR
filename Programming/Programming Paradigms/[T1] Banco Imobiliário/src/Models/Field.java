package Models;

public abstract class Field {
    private int[] position;

    // Constructor
    public Field(int[] position) {
        this.position = position;
    }

    // Getters and Setters
    public int[] getPosition() {return position;}
    public void setPosition(int[] position) {this.position = position;}
}
