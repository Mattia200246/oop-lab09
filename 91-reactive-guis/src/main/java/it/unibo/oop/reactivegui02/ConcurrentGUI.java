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
        panel.add(label);
        label.setText("0");
        panel.add(up);
        panel.add(down);
        panel.add(stop);
        
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)dimension.getWidth();
        int height = (int)dimension.getHeight();

        this.setSize(width/2, height/2);
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Agent agent = new Agent();
        new Thread(agent).start();
    }

    private class Agent implements Runnable{
        int counter = 0;
        @Override
        public void run() {
            while(true){
                try {

                label.setText(Integer.toString(this.counter));
                counter++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }
    

}
