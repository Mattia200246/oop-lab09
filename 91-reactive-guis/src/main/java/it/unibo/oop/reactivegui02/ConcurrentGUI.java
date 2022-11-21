package it.unibo.oop.reactivegui02;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Second example of reactive GUI.
 */
public final class ConcurrentGUI extends JFrame {
    JLabel label = new JLabel();
        JButton up = new JButton("up");
        JButton down = new JButton("down");
        JButton stop = new JButton("stop");

        
    public ConcurrentGUI(){
        super();
        final JPanel panel = new JPanel();
        panel.add(up);
        panel.add(down);
        panel.add(stop);
        
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    

}
