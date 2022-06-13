package com.gmail.inayakitorikhurram.boids.simulation;

import com.gmail.inayakitorikhurram.boids.simulation.math.Position;

import java.awt.*;

public class Boid {

    private Position pos;
    private int[] vel;
    private float mass;

    public Boid(int[] bounds){
        pos = Position.randomPos(bounds);
        vel = new int[]{0, 0};
    }

    public void draw(Graphics g){

    }

}
