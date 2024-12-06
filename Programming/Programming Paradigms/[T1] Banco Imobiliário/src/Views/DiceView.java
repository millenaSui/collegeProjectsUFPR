package Views;

import Models.Dice;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DiceView {
    private JPanel panel;

    public JPanel getPanel() {
        return panel;
    }

    public DiceView(Dice dice) {
        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image img = ImageIO.read(new File("./Content/Dice/Dice" + dice.getValue() + ".png"));
                    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        this.panel.setOpaque(false); // Set the panel to be transparent
        panel.setPreferredSize(new Dimension(100, 100));
    }

    public void addToBoard(Container board) {
        board.setLayout(new BorderLayout()); // Use BorderLayout to fill the entire board
        panel.setPreferredSize(new Dimension(board.getWidth(), board.getHeight())); // Set panel size to board size
        board.add(panel, BorderLayout.CENTER); // Add panel to the center of the board
    }

    // Método para exibir a animação de rolagem do dado e o resultado
    public void exhibit(Dice dice, int value) {
        long startTime = System.currentTimeMillis();
        int frameIndex = 0;
        while (System.currentTimeMillis() - startTime < 1000) {
            ImageIcon icon = new ImageIcon("./Content/Dice/Dice" + (frameIndex % 6 + 1) + ".png");
            JLabel label = new JLabel(icon);
            panel.removeAll();
            panel.add(label);
            panel.revalidate();
            panel.repaint();
            frameIndex++;
            try {
                Thread.sleep(100); // Simula a animação de rolagem
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        ImageIcon finalIcon = new ImageIcon("./Content/Dice/Dice" + value + ".png");
        JLabel finalLabel = new JLabel(finalIcon);
        panel.removeAll();
        panel.add(finalLabel);
        panel.revalidate();
        panel.repaint();
    }
}
