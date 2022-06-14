package com.gmail.inayakitorikhurram.boids.simulation;

import com.gmail.inayakitorikhurram.boids.simulation.math.MyMath;
import com.gmail.inayakitorikhurram.boids.simulation.math.Position;
import com.gmail.inayakitorikhurram.boids.window.WindowSettings;

import java.awt.*;
import java.util.ArrayList;

public class Boid {

    private BoidSettings bs;



    private Position pos;
    private float[] vel;
    private float mass;
    private float[] weights; //0 = coherence, 1 = separation, 2 = alignment
    public Color color;

    public Boid(BoidSettings bs){
        this.bs = bs;
        color = new Color((int)(Math.random() * 0xFFFFFF));
        pos = new Position(MyMath.randomVector(bs.bounds()), bs.bounds());
        vel = MyMath.randomVector(new float[]{-1.0f, -1.0f}, new float[]{1.0f, 1.0f});
        mass = MyMath.randomVector(new float[]{bs.minMass()}, new float[]{bs.maxMass()})[0];
        weights = bs.defaultWeights();
    }

    public Boid update(ArrayList<Boid> frens){
        //update velocity
        float[] coherence = Position.getAverageDisplacement(pos, frens, true);
        vel = MyMath.sum(vel, MyMath.mult(weights[0], coherence));

        vel = MyMath.mult(0.999f, vel);
        //update position
        pos.add(vel);
        return this;
    }

    public void draw(Graphics2D g2d, WindowSettings ws){
        g2d.setColor(color);

        g2d.fillOval((int) (pos.get(0) - 10), (int) (pos.get(1) - 10), 20, 20);

    }

    public Position getPos() {
        return pos;
    }

}
