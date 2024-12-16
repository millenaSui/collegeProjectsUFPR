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
    private ButtonClickListener buttonClickListener; // Listener externo


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

    public void setButtonClickListener(ButtonClickListener listener) {
        this.buttonClickListener = listener;
    }

    public void createPrisionButtons() {
        panel.setLayout(null);

        JButton buttonYes = new JButton();
        buttonYes.setSize(150, 50);
        buttonYes.setContentAreaFilled(false);
        buttonYes.setBorderPainted(false);
        buttonYes.setFocusPainted(false);
        buttonYes.setOpaque(false);
        buttonYes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonYes.addActionListener(e -> {
            if (buttonClickListener != null) {
                buttonClickListener.onButtonClicked("YES"); // Chama o listener para o botão "Yes"
            }
        });

        JButton buttonNo = new JButton();
        buttonNo.setSize(150, 50);
        buttonNo.setContentAreaFilled(false);
        buttonNo.setBorderPainted(false);
        buttonNo.setFocusPainted(false);
        buttonNo.setOpaque(false);
        buttonNo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonNo.addActionListener(e -> {
            if (buttonClickListener != null) {
                buttonClickListener.onButtonClicked("NO"); // Chama o listener para o botão "No"
            }
        });

        int panelWidth = panel.getWidth();
        int panelHeight = panel.getHeight();
        int buttonWidth = buttonYes.getWidth();
        int buttonHeight = buttonYes.getHeight();
        int spacing = 40;

        buttonYes.setLocation((panelWidth - 2 * buttonWidth - spacing) / 2 - 10, (panelHeight - buttonHeight) / 2 + 100);
        buttonNo.setLocation((panelWidth + spacing) / 2 + 70, (panelHeight - buttonHeight) / 2 + 100);

        panel.add(buttonYes);
        panel.add(buttonNo);

        panel.repaint();
    }

    public void createPropertyButtons() {
        panel.setLayout(null);

        JButton buttonBuy = new JButton();
        buttonBuy.setSize(205, 70);
        buttonBuy.setContentAreaFilled(false);
        buttonBuy.setBorderPainted(false);
        buttonBuy.setFocusPainted(false);
        buttonBuy.setOpaque(false);
        buttonBuy.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonBuy.addActionListener(e -> {
            if (buttonClickListener != null) {
                buttonClickListener.onButtonClicked("BUY"); // Chama o listener para o botão "Buy"
            }
        });

        JButton buttonAuction = new JButton();
        buttonAuction.setSize(205, 70);
        buttonAuction.setContentAreaFilled(false);
        buttonAuction.setBorderPainted(false);
        buttonAuction.setFocusPainted(false);
        buttonAuction.setOpaque(false);
        buttonAuction.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonAuction.addActionListener(e -> {
            if (buttonClickListener != null) {
                buttonClickListener.onButtonClicked("RENT"); // Chama o listener para o botão "Auction"
            }
        });

        int panelWidth = panel.getWidth();
        int panelHeight = panel.getHeight();
        int buttonWidth = buttonBuy.getWidth();
        int buttonHeight = buttonBuy.getHeight();
        int spacing = -12;

        buttonBuy.setLocation((panelWidth - buttonWidth) / 2 + 325, (panelHeight - 2 * buttonHeight - spacing) / 2 + 30);
        buttonAuction.setLocation((panelWidth - buttonWidth) / 2 + 325, (panelHeight - buttonHeight) / 2 + 40 + buttonHeight + spacing);

        panel.add(buttonBuy);
        panel.add(buttonAuction);

        panel.repaint();

    }

    public void clearPanel() {
        panel.removeAll();
        cardImage = null;
        panel.revalidate();
        panel.repaint();
    }
}
