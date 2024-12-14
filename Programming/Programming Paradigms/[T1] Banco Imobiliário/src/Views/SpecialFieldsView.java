package Views;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpecialFieldsView {
    private JPanel panel;
    private BufferedImage cardImage = null;

    public JPanel getPanel() {return panel;}

    public SpecialFieldsView() {
        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (cardImage != null) {
                    g.drawImage(cardImage, 0, 0, this.getWidth(), this.getHeight(), null);
                }
            }
        };

        this.panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(100, 100));
        this.panel.setFocusable(true);
    }

    public void addToBoard(Container board) {
        board.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(board.getWidth(), board.getHeight()));
        board.add(panel, BorderLayout.CENTER);
    }

    public void exhibit(String appearance) {
        try {
            cardImage = ImageIO.read(new File(appearance));
            panel.repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void clearPanel() {
        panel.removeAll();
        cardImage = null;
        panel.revalidate();
        panel.repaint();
    }
}
