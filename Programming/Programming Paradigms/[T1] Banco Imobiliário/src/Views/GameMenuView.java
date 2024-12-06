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
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.onMenuOptionSelected(2); // Informa o controlador
            }
        });

        JButton exitButton = new JButton();
        exitButton.setBounds(1097, 746, 283, 188);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(false);
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
