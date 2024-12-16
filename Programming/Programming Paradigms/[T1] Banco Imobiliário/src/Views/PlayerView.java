package Views;

import Models.Player;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PlayerView {
    private JPanel panel;
    private static int playerCount = 0; // Static counter to keep track of the number of players
    public static int widthPlayerMoviment = 145;
    public static int heightPlayerMoviment = 115;

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

    public void addToBoard(Container board) {
        board.setLayout(null); // Use absolute positioning
        int boardWidth = board.getWidth();
        int boardHeight = board.getHeight();

        int playersPerRow = 2; // Number of players per row
        int playerSize = 90; // Size of each player panel
        int spacing = 60; // Espaçamento adicional entre os jogadores
        int xOffset = boardWidth - ((playerCount % playersPerRow) + 1) * (playerSize - spacing); // Calculate x position
        int yOffset = boardHeight - ((playerCount / playersPerRow) + 1) * (playerSize - spacing); // Calculate y position

        // Adjust positions to form a square pattern
        xOffset -= (playerCount / playersPerRow) * playerSize / 2;
        yOffset += (playerCount % playersPerRow) * playerSize / 2;

        // Move characters 10 pixels up
        yOffset -= 210;
        xOffset -= 160;

        panel.setBounds(xOffset, yOffset, playerSize, playerSize); // Set position based on player count
        board.add(panel);
        playerCount++; // Increment player count
    }

    public void updatePosition(Player player, int steps) {
        int currentX = panel.getX();
        int currentY = panel.getY();
        int newX = currentX;
        int newY = currentY;
        
        int newPosition = player.getPosition() + steps;
        
        if (player.getPosition() < 10 && newPosition < 10) { // CERTO
            if (steps >= 0) {
                newX = currentX - (steps * widthPlayerMoviment);
            } else if (steps < 0 && newPosition >= 0) {
                newX = currentX - (steps * widthPlayerMoviment);
            } else if (steps < 0 && newPosition < 0) {
                newX = currentX + (player.getPosition() * widthPlayerMoviment);
                newY = currentY + (newPosition * heightPlayerMoviment);
            }   
        } else if (player.getPosition() < 15 && newPosition < 15) { // CERTO
            if (player.getPosition() < 9 && steps >= 0) {
                newX = currentX + ((player.getPosition() - 9) * widthPlayerMoviment);
                newY = currentY - ((newPosition - 9) * heightPlayerMoviment);
            } else if (player.getPosition() >= 9 && steps >= 0) {
                newY = currentY - (steps * heightPlayerMoviment);
            } else if (steps < 0 && newPosition >= 9) {
                newY = currentY - (steps * heightPlayerMoviment);
            } else if (steps < 0 && newPosition < 9) {
                newY = currentY + ((player.getPosition() - 9) * heightPlayerMoviment);
                newX = currentX - ((newPosition - 9) * widthPlayerMoviment);
            }
        } else if (player.getPosition() < 24 && newPosition < 24) { // CERTO
            if (player.getPosition() < 14 && steps >= 0) {
                newX = currentX + ((newPosition - 14) * widthPlayerMoviment);
                newY = currentY + ((player.getPosition() - 14) * heightPlayerMoviment);
            } else if (player.getPosition() >= 14 && steps >= 0) {
                newX = currentX + (steps * widthPlayerMoviment);
            } else if (steps < 0 && newPosition >= 14) {
                newX = currentX + (steps * widthPlayerMoviment);
            } else if (steps < 0 && newPosition < 14) {
                newY = currentY - ((newPosition - 14) * heightPlayerMoviment);
                newX = currentX - ((player.getPosition() - 14) * widthPlayerMoviment);
            }
        } else if (player.getPosition() < 29 && newPosition < 29) {
            if (steps >= 0 && player.getPosition() < 24) {
                newX = currentX - ((player.getPosition() - 23) * widthPlayerMoviment);
                newY = currentY + ((newPosition - 23) * heightPlayerMoviment);
            } else if (steps >= 0 && player.getPosition() >= 23) {
                newY = currentY + (steps * heightPlayerMoviment);
            } else if (steps < 0 && newPosition >= 23) {
                newY = currentY + (steps * heightPlayerMoviment);
            } else if (steps < 0 && newPosition < 23) {
                newY = currentY + ((player.getPosition() - 23) * heightPlayerMoviment);
                newX = currentX + ((newPosition - 23) * widthPlayerMoviment);
            }
        } else if (player.getPosition() < 29 && newPosition >= 29) {
            if (steps >= 0) {
                newX = currentX - ((newPosition - 28) * widthPlayerMoviment);
                newY = currentY - ((player.getPosition() - 28) * heightPlayerMoviment);
            }
        }
        
        panel.setBounds(newX, newY, panel.getWidth(), panel.getHeight());
    }

    public void removeFromBoard(Container board) {
        board.remove(panel);    
    }
}
