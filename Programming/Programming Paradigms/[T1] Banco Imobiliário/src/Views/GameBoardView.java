package Views;

import Models.GameBoard;
import Models.Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GameBoardView {
    private JPanel panel;

    public JPanel getPanel() {
        return panel;
    }

    public GameBoardView(GameBoard gameBoard) {
        JFrame frame = new JFrame("Game Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

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
    }

    public void addPlayer(Player player) {
        PlayerView playerView = new PlayerView(player, this.getPanel());
        playerView.addToBoard(panel);
        panel.revalidate();
        panel.repaint();
    }
}
