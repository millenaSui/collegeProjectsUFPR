package Views;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Models.ChanceCard;

public class ChanceCardView {
    private JPanel panel;
    private BufferedImage cardImage = null;

    public ChanceCardView() {
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

    public JPanel getPanel() {
        return panel;
    }

    public void exhibit(ChanceCard chanceCard) {
        try {
            cardImage = ImageIO.read(new File(chanceCard.getAppearance()));
            panel.repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    clearCard();
                }
            }
        });
    }

    public void addToBoard(Container board) {
        board.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(board.getWidth(), board.getHeight()));
        board.add(panel, BorderLayout.CENTER);
    }

    public void clearCard() {
        panel.removeAll();
        cardImage = null;
        panel.revalidate();
        panel.repaint();
    }
}
