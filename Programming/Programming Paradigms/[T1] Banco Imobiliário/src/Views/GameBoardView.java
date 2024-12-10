package Views;

import Models.GameBoard;
import Models.Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameBoardView {

    private JPanel panel;
    private Map<Player, PlayerView> playerViews;
    private Player currentPlayer; // Jogador atual
    private int pendingSteps; // Passos pendentes para o jogador
    private DiceView diceView; // Referência para o DiceView compartilhado

    public JPanel getPanel() {
        return panel;
    }

    public GameBoardView(GameBoard gameBoard, DiceView diceView) {
        this.diceView = diceView; // Recebe o DiceView
        JFrame frame = new JFrame("Monopoly Valley");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

        playerViews = new HashMap<>();

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image img = ImageIO.read(new File(gameBoard.getAppearance()));
                    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);

        setupKeyListener();
    }

    public void addPlayer(Player player) {
        PlayerView playerView = new PlayerView(player, this.getPanel());
        playerView.addToBoard(panel);
        playerViews.put(player, playerView);
        panel.revalidate();
        panel.repaint();
    }

    public void updatePlayerPosition(Player player, int steps) {
        this.currentPlayer = player;
        this.pendingSteps = steps;

        setupKeyListener();
    }

    private void setupKeyListener() {
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (currentPlayer != null) {
                        PlayerView playerView = playerViews.get(currentPlayer);
                        if (playerView != null) {
                            playerView.updatePosition(currentPlayer, pendingSteps);
                            panel.revalidate();
                            panel.repaint();
                            System.out.println(currentPlayer.getName() + " moveu " + pendingSteps + " passos.");
                        }

                        diceView.clearDice(); // Limpa a imagem do dado
                        pendingSteps = 0;


                    }
                }
            }
        });
    }
}
