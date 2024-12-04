package Models;

public class GameBoard {
    private Field[] fields;

    // Constructor
    public GameBoard(int numberOfFields) {
        this.fields = new Field[numberOfFields];
    }

    // Getters and Setters
    public Field getField(int position) {return fields[position];}
    public void setField(int position, Field field) {this.fields[position] = field;}
}
