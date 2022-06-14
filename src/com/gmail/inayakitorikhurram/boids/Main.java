package com.gmail.inayakitorikhurram.boids;


import com.gmail.inayakitorikhurram.boids.simulation.Boid;
import com.gmail.inayakitorikhurram.boids.simulation.BoidSettings;
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
                new float[]{1200, 800},
                0.3f,
                1.0f,
                8
        );

        WindowSettings ws = new WindowSettings(
                (int)bs.bounds()[0],
                (int)bs.bounds()[1],
                new Color(0xD5BFEA),
                16
        );

        display = new SimulationDisplay(ws);

        boids = new ArrayList<>();

        for(int i = 0; i < 100; i++){
            Boid boid = new Boid(bs);
            boids.add(boid);
            display.addBoid(boid);
        }


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for(Boid boid : boids){
                    boid.update();
                }
            }
        }
        , 0, bs.msPerTick());

    }

}