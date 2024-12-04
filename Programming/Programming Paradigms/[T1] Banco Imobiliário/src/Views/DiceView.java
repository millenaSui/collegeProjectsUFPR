package Views;

import Models.Dice;

public class DiceView {
    // Método para exibir a animação de rolagem do dado e o resultado
    public void rollDiceAnimation(Dice dice, int value) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 1000) {
            System.out.println("Rolling: " + dice.getSprite());
            try {
                Thread.sleep(100); // Simula a animação de rolagem
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(value + " casas!" + dice.getSprite());
    }
}
