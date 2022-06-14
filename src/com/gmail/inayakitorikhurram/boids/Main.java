package com.gmail.inayakitorikhurram.boids;


import com.gmail.inayakitorikhurram.boids.simulation.Boid;
import com.gmail.inayakitorikhurram.boids.simulation.BoidSettings;
import com.gmail.inayakitorikhurram.boids.window.SimulationDisplay;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static SimulationDisplay display;

    public static void main(String[] args) {

        BoidSettings.BOUNDS = new float[]{1200, 800};

        Boid arnold = new Boid(BoidSettings.BOUNDS);

        display = new SimulationDisplay((int) BoidSettings.BOUNDS[0], (int) BoidSettings.BOUNDS[1]);

        display.addBoid(arnold);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                arnold.update();
            }
        }
        , 100, 100);

    }

}