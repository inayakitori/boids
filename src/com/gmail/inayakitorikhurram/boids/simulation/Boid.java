package com.gmail.inayakitorikhurram.boids.simulation;

import com.gmail.inayakitorikhurram.boids.simulation.math.Position;

import java.awt.*;

public class Boid {

    private Position pos;
    private float[] vel;
    private float mass;

    public Boid(float[] bounds){
        pos = Position.randomPos(bounds);
        vel = new float[]{10f, 10f};
    }

    public Boid update(){
        //update velocity


        //update position
        pos.add(vel);
        return this;
    }

    public void draw(Graphics2D g2d){

        g2d.fillOval((int) (pos.get(0) - 10), (int) (pos.get(1) - 10), 20, 20);

    }

}
