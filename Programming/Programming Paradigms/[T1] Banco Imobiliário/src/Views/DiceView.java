package Views;

import Models.Dice;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DiceView {
    private JPanel panel;
    private boolean showingFinalImage = false; // Flag para indicar o estado atual da imagem final
    private BufferedImage diceImage = null; // Armazena a imagem atual do dado

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

        this.panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(100, 100));
        this.panel.setFocusable(true); // Torna o painel focável
    }

    public void addToBoard(Container board) {
        board.setLayout(new BorderLayout()); // Usa BorderLayout para preencher o espaço
        panel.setPreferredSize(new Dimension(board.getWidth(), board.getHeight())); // Ajusta o tamanho
        board.add(panel, BorderLayout.CENTER); // Adiciona ao centro do tabuleiro
    }

    public void exhibit(Dice dice, int value) {
        Timer timer = new Timer(50, null); // Timer para animação (50 ms de intervalo)
        long startTime = System.currentTimeMillis();

        timer.addActionListener(e -> {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime < 3000) { // Exibe a animação por 3 segundos
                int frameIndex = (int) (elapsedTime / 60) % 6 + 1; // Cicla pelas imagens do dado
                try {
                    diceImage = ImageIO.read(new File("./Content/Dice/Dice" + frameIndex + ".png"));
                    panel.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else { // Finaliza a animação e exibe a imagem final
                timer.stop();
                try {
                    showingFinalImage = true;
                    diceImage = ImageIO.read(new File("./Content/Dice/Dice" + value + ".png"));
                    panel.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        timer.start();
    }

    public void clearDice() {
        // Remove a imagem do dado e limpa o painel
        panel.removeAll();
        diceImage = null;
        panel.revalidate();
        panel.repaint();
        showingFinalImage = false; // Reseta o estado
    }

    public boolean isDiceExhibited() {
        return showingFinalImage;
    }
}
