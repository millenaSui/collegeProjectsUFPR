package Views;

import Models.Dice;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DiceView {
    private JPanel panel;
    private boolean showingFinalImage = false; // Flag to track the final image state
    private BufferedImage diceImage = null; // BufferedImage to hold the current dice image

    public JPanel getPanel() {
        return panel;
    }

    public boolean getShowingFinalImage() {
        return showingFinalImage;
    }

    public DiceView(Dice dice) {
        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (diceImage != null) {
                    g.drawImage(diceImage, 0, 0, this.getWidth(), this.getHeight(), null);
                }
            }
        };

        this.panel.setOpaque(false); // Ensure the panel itself is transparent
        panel.setPreferredSize(new Dimension(100, 100));

        // Add KeyListener to handle the Enter key press
        this.panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Check if the key is Enter
                    panel.removeAll(); // Clear all components from the panel
                    diceImage = null; // Clear the dice image
                    panel.revalidate();
                    panel.repaint();
                    showingFinalImage = false; // Reset the flag
                }
            }
        });

        this.panel.setFocusable(true); // Make sure the panel can receive key events
    }

    public void addToBoard(Container board) {
        board.setLayout(new BorderLayout()); // Use BorderLayout to fill the entire board
        panel.setPreferredSize(new Dimension(board.getWidth(), board.getHeight())); // Set panel size to board size
        board.add(panel, BorderLayout.CENTER); // Add panel to the center of the board
    }

    public void exhibit(Dice dice, int value) {
        Timer timer = new Timer(50, null); // Timer interval set to 50 milliseconds for smoother transitions
        long startTime = System.currentTimeMillis();

        // Animation: Cycle through dice images for 3 seconds
        timer.addActionListener(e -> {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime < 3000) { // Run for 3 seconds
                int frameIndex = (int) (elapsedTime / 60) % 6 + 1; // Cycle through 1 to 6 images
                try {
                    diceImage = ImageIO.read(new File("./Content/Dice/Dice" + frameIndex + ".png"));
                    panel.repaint(); // Trigger a repaint to show the updated image
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else { // Stop the timer and display the final image
                timer.stop();
                showingFinalImage = true; // Set the flag to indicate the final image is being displayed
                try {
                    diceImage = ImageIO.read(new File("./Content/Dice/Dice" + value + "Enter.png"));
                    panel.repaint(); // Trigger a repaint to show the final image
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        timer.start();
    }
}
