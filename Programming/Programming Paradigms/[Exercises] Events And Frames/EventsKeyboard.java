import java.awt.event.*;
import javax.swing.*;

/*
The EventsKeyboard class demonstrates handling keyboard 
events in a JFrame. It implements KeyListener to respond 
to various keyboard events. The text area in the window 
displays messages indicating the key event and its details.

Keyboard events handled:
    - keyPressed - Invoked when a key has been pressed.
    - keyReleased - Invoked when a key has been released.
    - keyTyped - Invoked when a key has been typed (pressed and released).
*/

public class EventsKeyboard extends JFrame implements KeyListener {
    private String line1 = "", line2 = "";
    private String line3 = "";
    private final JTextArea textArea;
    
    public EventsKeyboard(){
            super( "Demonstrating Keystroke Events" );
            textArea = new JTextArea( 10, 15 );
            textArea.setText( "Press any key on the keyboard..." );
            textArea.setEnabled( false );
            // allow frame to process Key events
            getContentPane().add( textArea );
            SwingUtilities.invokeLater(this::initializeKeyListener);
            setSize( 350, 100 );
            setVisible(true);
    }

    private void initializeKeyListener() {
            addKeyListener(this);
    }
    
    @Override
    public void keyPressed( KeyEvent e ){
            line1 = "Key pressed: " + KeyEvent.getKeyText( e.getKeyCode() );
            setLines2and3( e );
    }
    
    @Override
    public void keyReleased( KeyEvent e ){
            line1 = "Key released: " + KeyEvent.getKeyText( e.getKeyCode() );
            setLines2and3( e );
    }
    
    @Override
    public void keyTyped( KeyEvent e ){
            line1 = "Key typed: " + e.getKeyChar();
            setLines2and3( e );
    }

    private void setLines2and3( KeyEvent e ){
            line2 = "This key is " + ( e.isActionKey() ? "" : "not " ) + "an action key";
            String temp = KeyEvent.getModifiersExText( e.getModifiersEx() );
            line3 = "Modifier keys pressed: " + ( temp.equals( "" ) ? "none" : temp );
            textArea.setText(
            line1 + "\n" + line2 + "\n" + line3 + "\n" );
    }

    // Main method to test the EventsKeyboard class
    public static void main( String args[] ){
            EventsKeyboard app = new EventsKeyboard();
            app.addWindowListener(
                    new WindowAdapter() {
                            @Override
                            public void windowClosing( WindowEvent e ){
                                    System.exit( 0 );
                            }
                    }
            );
    }
}