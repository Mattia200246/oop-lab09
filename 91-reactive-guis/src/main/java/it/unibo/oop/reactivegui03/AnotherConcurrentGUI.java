package it.unibo.oop.reactivegui03;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Third experiment with reactive gui.
 */
public final class AnotherConcurrentGUI extends JFrame {
    JLabel label = new JLabel();
    JButton up = new JButton("Up");
    JButton down = new JButton("Down");
    JButton stop = new JButton("Stop");
    public AnotherConcurrentGUI(){
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
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(panel);
        this.setVisible(true);

        Agent agent = new Agent();
        new Thread(agent).start();

        stop.addActionListener( (e)-> {agent.stop(); up.setEnabled(false); down.setEnabled(false);});
        down.addActionListener( (e)-> agent.down());
        up.addActionListener( (e)-> agent.up());

        
    }

    private class Agent implements Runnable{
        private int counter = 0;
        private volatile boolean option; //0 represent inc ; 1 dec
        private volatile boolean stop;

        @Override
        public void run() {
            while(!stop){
                try {
                    label.setText(Integer.toString(this.counter));
                        if(!option){
                            counter++;
                        }else{
                            counter--;
                        }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            
        }

        public void up() {
            this.option = false;
        }
        private void stop() {
            this.stop = true;
            
        }

        private void down() {
            this.option = true;
        }
        
    }

}
