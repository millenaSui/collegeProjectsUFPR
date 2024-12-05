package Views;

import Models.Player;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Container;

public class PlayerView {
    private JPanel panel;

    public JPanel getPanel() {
        return panel;
    }

    public PlayerView(Player player, JPanel panel) {
        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image img = ImageIO.read(new File(player.getAppearance()));
                    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        this.panel.setOpaque(false); // Set the panel to be transparent
        panel.setPreferredSize(new Dimension(100, 100));
    }

    public void exhibitSelectionCharacterScreen() {
        // Implementação da tela de seleção de personagem
    }

    public void addToBoard(Container board) {
        board.setLayout(null); // Use absolute positioning
        panel.setBounds(board.getWidth() - 190, board.getHeight() - 210, 100, 100); // Initial position
        board.add(panel);
    }
}
