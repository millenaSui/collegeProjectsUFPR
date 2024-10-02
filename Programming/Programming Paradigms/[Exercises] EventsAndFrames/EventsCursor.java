import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
The EventsCursor class demonstrates handling mouse 
events in a JFrame. It implements MouseListener 
and MouseMotionListener to respond to various 
mouse events. The status bar at the bottom of 
the window displays messages indicating the 
mouse event and its coordinates.

Mouse events handled:
  - mouseClicked - Invoked when the mouse button has 
    been clicked (pressed and released) on a component.
  - mousePressed - Invoked when a mouse button has been 
    pressed on a component.
  - mouseReleased - Invoked when a mouse button has been 
    released on a component.
  - mouseEntered - Invoked when the mouse enters a component.
  - mouseExited - Invoked when the mouse exits a component.
  - mouseDragged - Invoked when a mouse button is pressed 
    on a component and then dragged.
  - mouseMoved - Invoked when the mouse cursor has been 
    moved onto a component but no buttons have been pushed.
*/
public class EventsCursor extends JFrame implements MouseListener, MouseMotionListener {
    private final JLabel statusBar;
    public EventsCursor() {
        super( "Demonstrating Mouse Events" );
        statusBar = new JLabel();
        getContentPane().add( statusBar, BorderLayout.SOUTH );
        initializeListeners();
        setSize( 275, 100 );
        setVisible(true);
    }

    private void initializeListeners() {
        // application listens to its own mouse events
        addMouseListener( this );
        addMouseMotionListener( this );
    }

    // MouseListener event handlers
    @Override
    public void mouseClicked( MouseEvent e ) { 
        statusBar.setText( "Clicked at [" + e.getX() + ", " + e.getY() + "]" ); 
    }
    @Override
    public void mousePressed( MouseEvent e ) { 
        statusBar.setText( "Pressed at [" + e.getX() + ", " + e.getY() + "]" ); 
    }
    @Override
    public void mouseReleased( MouseEvent e ) { 
        statusBar.setText( "Released at [" + e.getX() + ", " + e.getY() + "]" ); 
    }
    @Override
    public void mouseEntered( MouseEvent e) { 
        statusBar.setText( "Mouse in window" ); 
    }
    @Override
    public void mouseExited( MouseEvent e ) { 
        statusBar.setText( "Mouse outside window" ); 
    }
    // MouseMotionListener event handlers
    @Override
    public void mouseDragged( MouseEvent e ) { 
        statusBar.setText( "Dragged at [" + e.getX() + ", " + e.getY() + "]" ); }
    @Override
    public void mouseMoved( MouseEvent e ) { 
        statusBar.setText( "Moved at [" + e.getX() + ", " + e.getY() + "]" ); }
    
    // main method
    public static void main( String args[] ) {
        EventsCursor app = new EventsCursor();
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