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
    private float viewDistance = 100;
    private float viewDistanceSquared = viewDistance * viewDistance;
    private float mass;
    private float[] weights; //0 = global, 1 = coherence, 2 = separation, 3 = alignment
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
        float[] acc = new float[bs.bounds().length];
        ArrayList<Boid> close_frens = new ArrayList<>();

        //TODO quadtree
        for(int i = 0; i < frens.size(); i++){
            Boid fren = frens.get(i);
            float[] displacement = Position.getDisplacement(pos, fren.pos);
            float distanceSquared = MyMath.getMag2(displacement);
            if(distanceSquared < viewDistanceSquared && distanceSquared != 0){
                close_frens.add(fren);
            }
        }

        float[][] displacements = getDisplacements(close_frens);
        float[] distancesSquared = new float[close_frens.size()];

        if(!close_frens.isEmpty()) {

            //coherence
            float[] coherence = MyMath.average(displacements);
            coherence = MyMath.mult(weights[1], coherence);
            acc = coherence;

            //separation TODO fix
            float shortestDistanceSquared = Float.POSITIVE_INFINITY;
            int closestBoidIndex = -1;
            for (int i = 0; i < close_frens.size(); i++) {
                if (distancesSquared[i] < shortestDistanceSquared) {
                    shortestDistanceSquared = distancesSquared[i];
                    closestBoidIndex = i;
                }
            }

            if (closestBoidIndex != -1) {
                float[] smallestDisplacement = frens.get(closestBoidIndex).pos.get();

                float[] separation = MyMath.mult(-1.0f * weights[2], smallestDisplacement);
                acc = MyMath.sum(acc, separation);
            }

            //alignment

            float[] alignment = new float[pos.dims];
            for (Boid fren : close_frens) {
                alignment = MyMath.sum(alignment, fren.vel);
            }

            alignment = MyMath.mult(weights[3] / close_frens.size(), alignment);

            acc = MyMath.sum(acc, alignment);

            //acc = MyMath.clamp(acc, 1);

            //alignment


            acc = MyMath.mult(weights[0], acc);

            vel = MyMath.sum(vel, acc);
        }

        vel = MyMath.mult(0.999f, vel);
        vel = MyMath.setMagnitude(vel, 0.1f);
        //update position
        pos.add(vel);
        return this;
    }

    public float[][] getDisplacements(ArrayList<Boid> others){
        float[][] displacements = new float[others.size()][bs.bounds().length];

        for(int i = 0; i < others.size(); i++){
            displacements[i] = Position.getDisplacement(pos, others.get(i).pos);
        }

        return displacements;
    }

    public void draw(Graphics2D g2d, WindowSettings ws){
        g2d.setColor(color);

        g2d.fillOval((int) (pos.get(0) - 10), (int) (pos.get(1) - 10), 20, 20);

    }

    public Position getPos() {
        return pos;
    }

}
