package Views;

import Models.GameBoard;
import Models.Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;

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
        frame.setSize(1920, 1080);

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
                System.out.println(currentPlayer.getName() + " moveu " + pendingSteps + " passos.");
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
            customFont = customFont.deriveFont(18f); // Definir o tamanho da fonte
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a fonte personalizada. Usando fonte padrão.");
            customFont = new Font("Arial", Font.BOLD, 24); // Fonte fallback
        }
    
        // Criar os labels e configurar com a fonte personalizada
        JLabel nameLabel = new JLabel(player.getName(), SwingConstants.CENTER);
        nameLabel.setFont(customFont);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(0, 10, panel.getWidth(), 30);
    
        JLabel moneyLabel = new JLabel("Money: $" + player.getMoney(), SwingConstants.CENTER);
        moneyLabel.setFont(customFont);
        moneyLabel.setForeground(Color.WHITE);
        moneyLabel.setBounds(0, panel.getHeight() - 50, panel.getWidth(), 30);
    
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
}