package Views; 

import Controllers.Round;

public class GameView {
    private Round round;

    public GameView(Round round) {
        this.round = round;
    }

    public void exhibitGame() {
        System.out.println("Iniciando o jogo...\n");
        while (true) {
            round.initRound();
            // Adiciona uma pausa para simular o tempo entre as rodadas
            try {
                Thread.sleep(1000); // 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
