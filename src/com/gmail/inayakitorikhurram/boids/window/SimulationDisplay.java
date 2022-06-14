package com.gmail.inayakitorikhurram.boids.window;

import com.gmail.inayakitorikhurram.boids.simulation.Boid;
import com.gmail.inayakitorikhurram.boids.simulation.BoidSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class SimulationDisplay extends JFrame {

    private final int WIDTH;
    private final int HEIGHT;

    private ArrayList<Boid> boids;
    public SimulationDisplay(int width, int height){
        super("BOIDS BOIDS BOIDS");
        WIDTH = width;
        HEIGHT = height;

        boids = new ArrayList<>();

        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }
        , 0, BoidSettings.MILLISECONDS_PER_FRAME);
    }

    public void addBoid(Boid boid){
        boids.add(boid);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(new Color(0xD5FFFF));

        g2d.setBackground(new Color(0xD5BFEA));
        g2d.clearRect(0, 0, WIDTH, HEIGHT);

        for(Boid boid : boids){
            boid.draw(g2d);
        }
    }

}
