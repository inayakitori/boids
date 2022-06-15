package com.gmail.inayakitorikhurram.boids.window;

import com.gmail.inayakitorikhurram.boids.simulation.Boid;
import com.gmail.inayakitorikhurram.boids.simulation.BoidSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class SimulationDisplay extends Canvas {

    private JFrame frame;
    private WindowSettings ws;
    private BufferStrategy bs;
    Graphics g = null;
    private ArrayList<Boid> boids;
    public SimulationDisplay(WindowSettings ws){
        super();
        this.ws = ws;
        setSize(ws.width(), ws.height());

        setIgnoreRepaint(true);
        JFrame frame = new JFrame("BOIDS BOIDS BOIDS");
        frame.setSize(ws.width(), ws.height());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setIgnoreRepaint(true);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);

        //BufferStrategy

        createBufferStrategy(3);
        bs = getBufferStrategy();

        boids = new ArrayList<>();

        Timer timer = new Timer();
        int i = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
                                      @Override
                                      public void run() {
                                          render(bs.getDrawGraphics());
                                      }
                                  }
                , 0, ws.msPerFrame());
    }

    public void addBoid(Boid boid){
        boids.add(boid);
    }

    public void render(Graphics g){
        try {
            // clear back buffer...
            g = bs.getDrawGraphics();
            g.setColor( Color.BLACK );
            g.fillRect( 0, 0, 639, 479 );


        Graphics2D g2d = (Graphics2D) g;

        g2d.setBackground(ws.backgroundColor());
        g2d.clearRect(0, 0, ws.width(), ws.height());

        for(Boid boid : boids){
            boid.draw(g2d, ws);
        }

            // blit the back buffer to the screen
            if( !bs.contentsLost() )
                bs.show();

            // Let the OS have a little time...
            Thread.yield();
        } finally {
            if( g != null )
                g.dispose();
        }
    }

}
