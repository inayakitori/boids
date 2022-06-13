package com.gmail.inayakitorikhurram.boids.window;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;


public class SimulationDisplay extends JFrame {

    private final int WIDTH;
    private final int HEIGHT;
    public SimulationDisplay(int width, int height){
        super("BOIDS BOIDS BOIDS");
        WIDTH = width;
        HEIGHT = height;

        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setBackground(new Color(0xD5BFEA));
        g2d.clearRect(0, 0, WIDTH, HEIGHT);

    }

}
