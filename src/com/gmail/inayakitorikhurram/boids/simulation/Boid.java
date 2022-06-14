package com.gmail.inayakitorikhurram.boids.simulation;

import com.gmail.inayakitorikhurram.boids.simulation.math.MyMath;
import com.gmail.inayakitorikhurram.boids.simulation.math.Position;

import java.awt.*;

public class Boid {

    private BoidSettings bs;
    private Position pos;
    private float[] vel;
    private float mass;
    public Color color;

    public Boid(BoidSettings bs){
        this.bs = bs;
        color = new Color((int)(Math.random() * 0xFFFFFF));
        pos = new Position(MyMath.randomVector(bs.bounds()), bs.bounds());
        vel = MyMath.randomVector(new float[]{-1.0f, 0.5f}, new float[]{1.0f, 3.0f});
    }

    public Boid update(){
        //update velocity


        //update position
        pos.add(vel);
        return this;
    }

    public void draw(Graphics2D g2d){

        g2d.setColor(color);

        g2d.fillOval((int) (pos.get(0) - 10), (int) (pos.get(1) - 10), 20, 20);

    }

}
