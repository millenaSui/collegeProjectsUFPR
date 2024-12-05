package Models;

import java.util.List;

public class GameBoard {
    private List<String> appearance;
    private Field[] fields;

    // Constructor
    public GameBoard(int numberOfFields, List<String> appearance) {
        this.fields = new Field[numberOfFields];
        this.appearance = appearance; // Atribui a lista passada como parâmetro
    }

    // Getters and Setters
    public Field getField(int position) {return fields[position];}
    public void setField(int position, Field field) {this.fields[position] = field;}
    public List<String> getAppearance() {return appearance;}
    public void setAppearance(List<String> appearance) {this.appearance = appearance;}
}