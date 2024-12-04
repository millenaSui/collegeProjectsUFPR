package Models;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private String name;
    private int money;
    private int position;
    private List<Property> properties;
    private boolean isBankrupt;
    private boolean inJail;
    private boolean inDetention;

    // Constructor
    public Player(String nome) {
        this.name = nome;
        this.money = 1000;
        this.position = 0;
        this.properties = new ArrayList<>();
        this.isBankrupt = false;
        this.inJail = false;
        this.inDetention = false;
    }

    // Getters and Setters
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getMoney() {return money;}
    public void setMoney(int money) {this.money = money;}
    public int getPosition() {return position;}
    public void setPosition(int position) {this.position = position;}
    public List<Property> getProperties() {return properties;}
    public void setProperties(List<Property> properties) {this.properties = properties;}
    public boolean getIsBankrupt() {return isBankrupt;}
    public void setIsBankrupt(boolean isBankrupt) {this.isBankrupt = isBankrupt;}
    public boolean getInJail() {return inJail;}
    public void setInJail(boolean inJail) {this.inJail = inJail;}
    public boolean getInDetention() {return inDetention;}
    public void setInDetention(boolean inDetention) {this.inDetention = inDetention;}

    // Move o jogador
    public void movePlayer() {
    
    }

}
