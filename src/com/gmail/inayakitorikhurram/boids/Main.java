package com.gmail.inayakitorikhurram.boids;


import com.gmail.inayakitorikhurram.boids.simulation.BoidSettings;
import com.gmail.inayakitorikhurram.boids.window.SimulationDisplay;

public class Main {
    private static SimulationDisplay display;

    public static void main(String[] args) {

        BoidSettings.BOUNDS = new int[]{1200, 800};

        display = new SimulationDisplay(BoidSettings.BOUNDS[0], BoidSettings.BOUNDS[1]);
    }

}