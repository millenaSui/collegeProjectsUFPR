import java.util.List;
import java.util.Random;

public class Dice {
    private List<String> sprites;
    private int value;
    private Random random;

    // Constructor
    public Dice(List<String> sprites) {
        this.sprites = sprites;
        this.random = new Random();
        this.value = 1;
    }

    // Getters and Setters
    public int getValor() {return value;}
    public void setValor(int value) {this.value = value;}
    public String getSprite() {return sprites.get(value - 1);}
    public void setSprite(String sprite) {sprites.add(sprite);}

    // Randomize the dice value
    public void rollDice() {
        value = random.nextInt(6) + 1;
    }
}
