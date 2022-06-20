package com.gmail.inayakitorikhurram.boids;


import com.gmail.inayakitorikhurram.boids.simulation.boids.Boid;
import com.gmail.inayakitorikhurram.boids.simulation.BoidSettings;
import com.gmail.inayakitorikhurram.boids.simulation.boids.MouseBoid;
import com.gmail.inayakitorikhurram.boids.simulation.boids.TraceBoid;
import com.gmail.inayakitorikhurram.boids.simulation.math.SphereVector;
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
                new Vector(new float[]{1900, 1000, 900}),
                0.6f,
                1.0f,
                8,
                200,
                new float[]{0.001f,     //cohesion
                        0.5f,       //alignment
                        0.01f,     //separation
                        10f,        //avoidance
                        10f,        //walls
                }
        );

        int[] renderSpace = ToroidalPosition.requiredRenderSpace(bs.bounds());

        WindowSettings ws = new WindowSettings(
                renderSpace[0],
                renderSpace[1],
                new Color(0xD5BFEA),
                16
        );

        display = new SimulationDisplay(ws);

        boids = new ArrayList<>();

        MouseBoid mouseBoid = new MouseBoid(bs);
        boids.add(mouseBoid);
        display.addBoid(mouseBoid);

        for(int i = 2; i < bs.boidCount(); i++){
            Boid boid = new TraceBoid(bs, 15, 1);
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