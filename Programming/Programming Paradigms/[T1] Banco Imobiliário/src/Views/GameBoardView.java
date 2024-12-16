package Views;

import Models.GameBoard;
import Models.Player;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameBoardView {

    private JPanel panel;
    private Map<Player, PlayerView> playerViews;
    private Player currentPlayer; // Jogador atual
    private int pendingSteps; // Passos pendentes para o jogador

    public JPanel getPanel() {
        return panel;
    }

    public GameBoardView(GameBoard gameBoard) {
        JFrame frame = new JFrame("Monopoly Valley");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());
        int width = Math.min(1920, screenSize.width - screenInsets.left - screenInsets.right);
        int height = Math.min(1080, screenSize.height - screenInsets.top - screenInsets.bottom);
        frame.setSize(width, height);
        frame.setResizable(false);

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

        if (currentPlayer != null) {
            PlayerView playerView = playerViews.get(currentPlayer);
            if (playerView != null) {
                playerView.updatePosition(currentPlayer, pendingSteps);
                panel.revalidate();
                panel.repaint();
            }
            pendingSteps = 0;
        }
    };

    public void displayPlayerInfo(Player player) {
        // Caminho para o arquivo da fonte
        String fontPath = "./Content/Font/PressStart2P-Regular.ttf";
        Font customFont = null;
    
        try {
            // Carregar a fonte personalizada
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            customFont = customFont.deriveFont(16f); // Definir o tamanho da fonte
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a fonte personalizada. Usando fonte padrão.");
            customFont = new Font("Arial", Font.BOLD, 24); // Fonte fallback
        }
    
        // Criar os labels e configurar com a fonte personalizada
        JLabel nameLabel = new JLabel(player.getName(), SwingConstants.CENTER);
        nameLabel.setFont(customFont);
        nameLabel.setForeground(new Color(36, 24, 24));
        nameLabel.setBounds(0, 50, panel.getWidth(), 30);
    
        JLabel moneyLabel = new JLabel("Money: $" + player.getMoney(), SwingConstants.CENTER);
        moneyLabel.setFont(customFont);
        moneyLabel.setForeground(new Color(36, 24, 24));
        moneyLabel.setBounds(0, panel.getHeight() - 65, panel.getWidth(), 30);
    
        // Configurar o painel
        panel.setLayout(null);
        panel.add(nameLabel);
        panel.add(moneyLabel);
        panel.revalidate();
        panel.repaint();
    }

    public void clearPlayerInfo() {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                panel.remove(component);
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    public void removePlayer(Player player) {
        PlayerView playerView = playerViews.remove(player);
        if (playerView != null) {
            playerView.removeFromBoard(panel);
            panel.revalidate();
            panel.repaint();
        }
    }
}