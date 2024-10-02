import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameFlowLayout extends JFrame implements ActionListener {
    /*
    Constructs a new JanelaTosca window with 
    predefined components and layout.
    
    This constructor initializes the following components:
    
    - Two JButtons: B1 ("Botao 1") and B2 ("Botao 2")
    - Two JLabels: L1 ("Label 1") and L2 ("Label 2")
    - Two JTextFields: T1 ("TextField 1") and T2 ("TextField 2")
    
    The components are added to the content 
    pane using a FlowLayout. The window is 
    positioned at (200, 200) on the screen 
    and has a size of 200x150 pixels.
    The addListeners method is called to 
    attach event listeners to the components.
    */
    
    private final JButton B1, B2;
    private final JLabel L1, L2;
    private final JTextField T1, T2;

    public FrameFlowLayout() {
        B1 = new JButton("Botao 1");
        B2 = new JButton("Botao 2");
        L1 = new JLabel("Label 1");
        L2 = new JLabel("Label 2");
        T1 = new JTextField("TextField 1");
        T2 = new JTextField("TextField 2");
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().add(L1);
        this.getContentPane().add(T1);
        this.getContentPane().add(L2);
        this.getContentPane().add(T2);
        this.getContentPane().add(B1);
        this.getContentPane().add(B2);
        this.setLocation(200, 200);
        this.setSize(200, 150);
        addListeners();
    }

    private void addListeners() {
        B1.addActionListener(this);
        B2.addActionListener(this);
    }

    @Override
    // This method is called whenever an action 
    // event is fired by a component.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == B1)
            System.out.println("voce pressionou B1" + T1.getText());
        if (e.getSource() == B2)
            System.out.println("voce pressionou B2" + T2.getText());
    }
    
    // This is the main method that creates a new
    public static void main(String args[]) {
        JFrame janela = new FrameFlowLayout();
        janela.show();
        // This is a simple way to close the window 
        // when the user clicks the close button.
        WindowListener x = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        janela.addWindowListener(x);
    }
}