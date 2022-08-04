package com.gmail.inayakitorikhurram.boids;


import com.gmail.inayakitorikhurram.boids.simulation.math.position.*;
import com.gmail.inayakitorikhurram.boids.window.*;
import com.gmail.inayakitorikhurram.boids.simulation.*;
import com.gmail.inayakitorikhurram.boids.simulation.boids.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static SimulationDisplay display;
    public static ArrayList<Boid> boids;
    public static void main(String[] args) {


        BoidSettings bs = new BoidSettings(
                new Vector(new float[]{3440, 1440}),
                new boolean[]{true, true},
                0.5f,
                1.0f,
                2.0f,
                3,
                400,
                new float[]{
                        0.001f,     //cohesion
                        1.0f,       //separation
                        0.01f,     //alignment
                        3f,        //avoidance
                        5,        //walls
                }
        );


        WindowSettings ws = new WindowSettings(
                new Color(0xD5BFEA),
                6,
                5.0f,
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
            Boid boid = new TraceBoid(bs, 5, 1);
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