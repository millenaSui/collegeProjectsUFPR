package Models;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dice {
    private List<String> sprites;
    private int value;
    private Random random;
    
    // Constructor
    public Dice() {
        this.random = new Random();
        this.value = 1;
        this.sprites = Arrays.asList(
            "faceDice1.png", 
            "faceDice2.png", 
            "faceDice3.png", 
            "faceDice4.png", 
            "faceDice5.png", 
            "faceDice6.png"
        );
    }

    // Getters and Setter
    public int getValue() {return value;}
    public void setValue(int value) {this.value = value;}
    public String getSprite() {return sprites.get(value - 1);}
    public void setSprite(String sprite) {sprites.add(sprite);}

    // Aleatoriza o valor do dado
    public int rollDice() {
        value = random.nextInt(6) + 1;
        return value;
    }
}
