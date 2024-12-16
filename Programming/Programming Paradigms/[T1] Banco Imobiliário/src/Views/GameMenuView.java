package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenuView {
    private MenuCallback callback;

    public GameMenuView(MenuCallback callback) {
        this.callback = callback;
    }

    public void exhibitMenu() {
        JFrame frame = new JFrame("Monopoly Valley");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080); // Dimensões da tela
        
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("./Content/InitScreen.png");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        JButton startButton = new JButton();
        startButton.setBounds(479, 746, 283, 188);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            callback.onMenuOptionSelected(1); // Informa o controlador
            }
        });

        JButton loadButton = new JButton();
        loadButton.setBounds(788, 746, 283, 188);
        loadButton.setContentAreaFilled(false);
        loadButton.setBorderPainted(false);
        loadButton.setFocusPainted(false);
        loadButton.setOpaque(false);
        loadButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.onMenuOptionSelected(2); // Informa o controlador
            }
        });

        JButton saveButton = new JButton();
        saveButton.setBounds(1780, 855, 80, 80); // Ajusta o tamanho e posição do botão
        saveButton.setContentAreaFilled(false); // Remove a área de conteúdo do botão
        saveButton.setOpaque(false); // Torna o botão opaco para que a cor de fundo seja visível
        saveButton.setBorderPainted(false); // Remove a borda do botão
        saveButton.setFocusPainted(false); // Remove o foco pintado do botão
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            callback.onMenuOptionSelected(3); // Informa o controlador
            }
        });

        panel.add(saveButton);

        JButton exitButton = new JButton();
        exitButton.setBounds(1097, 746, 283, 188);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(false);
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(startButton);
        panel.add(loadButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
