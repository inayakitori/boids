package com.gmail.inayakitorikhurram.boids;


import com.gmail.inayakitorikhurram.boids.simulation.boids.Boid;
import com.gmail.inayakitorikhurram.boids.simulation.BoidSettings;
import com.gmail.inayakitorikhurram.boids.simulation.boids.MouseBoid;
import com.gmail.inayakitorikhurram.boids.simulation.boids.TraceBoid;
import com.gmail.inayakitorikhurram.boids.simulation.math.ToroidalPosition;
import com.gmail.inayakitorikhurram.boids.simulation.math.Vector;
import com.gmail.inayakitorikhurram.boids.window.SimulationDisplay;
import com.gmail.inayakitorikhurram.boids.window.WindowSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static SimulationDisplay display;
    public static ArrayList<Boid> boids;
    public static void main(String[] args) {


        BoidSettings bs = new BoidSettings(
                new Vector(900, 3),
                0.6f,
                1.0f,
                3.0f,
                8,
                5,
                new float[]{0.001f,     //cohesion
                        0.5f,       //alignment
                        0.01f,     //separation
                        30f,        //avoidance
                        10f,        //walls
                }
        );


        WindowSettings ws = new WindowSettings(
                new Color(0xD5BFEA),
                16,
                7.5f,
                1.0f
        );


        int[] renderSpace = ToroidalPosition.requiredRenderSpace(ws, bs.bounds());

        display = new SimulationDisplay(ws, renderSpace[0], renderSpace[1]);

        boids = new ArrayList<>();

//        TraceBoid traceBoid = new TraceBoid(bs, 50, 1);
//        boids.add(traceBoid);
//        display.addBoid(traceBoid);

        MouseBoid mouseBoid = new MouseBoid(bs);
        boids.add(mouseBoid);
        display.addBoid(mouseBoid);

        for(int i = 1; i < bs.boidCount(); i++){
            Boid boid = new TraceBoid(bs, 10, 1);
            boids.add(boid);
            display.addBoid(boid);
        }


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for(int i = 0; i < boids.size(); i++){
                    boids.get(i).update(boids);
                }
            }
        }
        , 0, bs.msPerTick());

    }

}