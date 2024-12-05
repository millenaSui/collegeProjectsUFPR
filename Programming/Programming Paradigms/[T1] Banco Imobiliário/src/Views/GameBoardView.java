package Views;

import Models.GameBoard;
import Models.Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class GameBoardView {
    private JPanel panel;
    public JPanel getPanel() {return panel;}

    public GameBoardView(GameBoard gameBoard) {
        JFrame frame = new JFrame("Game Board");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

        // Seleciona uma imagem aleatória da lista de appearance
        Random random = new Random();
        int randomIndex = random.nextInt(gameBoard.getAppearance().size());
        String randomImagePath = gameBoard.getAppearance().get(randomIndex);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image img = ImageIO.read(new File(randomImagePath));
                    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        frame.add(panel);
        frame.setVisible(true);
    }
    
    public void addPlayer(Player player) {
        PlayerView playerView = new PlayerView(player, this.getPanel());
        playerView.addToBoard(panel);
        panel.revalidate();
        panel.repaint();
    }
}