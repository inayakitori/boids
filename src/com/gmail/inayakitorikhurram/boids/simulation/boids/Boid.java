package com.gmail.inayakitorikhurram.boids.simulation.boids;

import com.gmail.inayakitorikhurram.boids.simulation.BoidSettings;
import com.gmail.inayakitorikhurram.boids.simulation.math.*;
import com.gmail.inayakitorikhurram.boids.window.WindowSettings;

import javax.swing.text.Position;
import java.awt.*;
import java.util.ArrayList;

public class Boid {

    protected BoidSettings bs;
    protected Vector pos;
    protected Vector vel;
    protected float viewDistance = 100;
    protected float viewDistanceSquared = viewDistance * viewDistance;
    protected boolean avoidWalls = true;
    protected float mass;
    protected float invMass;
    protected float[] weights; //0 = coherence, 1 = separation, 2 = alignment
    protected Color color;

    public Boid(BoidSettings bs){
        this.bs = bs;
        color = new Color((int)(Math.random() * 0xFFFFFF));
        pos = new Vector(MyMath.randomVector(bs.bounds()));
        vel = MyMath.randomVector(new Vector(new float[]{-1.0f, -1.0f}), new Vector(new float[]{1.0f, 1.0f}));
        mass = MyMath.random(bs.minMass(), bs.maxMass());
        invMass = 1.0f / mass;
        weights = bs.defaultWeights();
    }

    public Boid update(ArrayList<Boid> friends){
        int dims = bs.bounds().dims;
        //update velocity
        ArrayList<Boid> close_friends = new ArrayList<>();
        ArrayList<MouseBoid> mouse_friends = new ArrayList<>();

        //TODO quadtree
        for(int i = 0; i < friends.size(); i++){
            Boid friend = friends.get(i);
            Vector displacement = pos.getDisplacement(friend.pos);
            float distanceSquared = displacement.getMag2();
            if(distanceSquared < viewDistanceSquared && distanceSquared != 0){
                if(friend instanceof MouseBoid){
                    mouse_friends.add((MouseBoid) friend);
                }else {
                    close_friends.add(friend);
                }
            }
        }

        Vector acc = new Vector(dims);
        Vector coherence = new Vector(dims);
        Vector separation = new Vector(dims);
        Vector alignment = new Vector(dims);
        Vector avoidance = new Vector(dims);
        Vector walls = new Vector(dims);

        float smallestDistance2 = Float.POSITIVE_INFINITY;

        //friends
        if (!close_friends.isEmpty()) {
                for (int i = 0; i < close_friends.size(); i++) {

                    //TODO define outside loop
                    Boid friend = close_friends.get(i);
                    Vector displacement = pos.getDisplacement(friend.pos);
                    float distance2 = displacement.getMag2();

                    //coherence p1
                    coherence.add(displacement);

                    //separation p1
                    if (distance2 < smallestDistance2) {
                        smallestDistance2 = distance2;
                        separation = new Vector(displacement);
                    }

                    //alignment p1
                    alignment.add(friend.vel);
                }

                float inverseFriendCount = 1.0f / close_friends.size();

                //coherence p2
                coherence.mult(inverseFriendCount);

                //separation p2
                separation.mult(-1.0f/ smallestDistance2);

                //alignment p2
                alignment.mult(inverseFriendCount);


            }

        //mouse
        for(MouseBoid mouseBoid : mouse_friends){
            Vector displacement = pos.getDisplacement(mouseBoid.pos);
            float distance2 = displacement.getMag2();
            avoidance.add(displacement.mult(-1.0f / distance2));
        }

        if(avoidWalls) {

            float margin = 0.1f;
            //wall avoidance
            for (int d = 0; d < dims; d++) {
                float x = pos.get(d);
                float b = bs.bounds().get(d);
                if (x < margin * b) {
                    float f = margin - x / b;
                    walls.add(d, f);
                } else if (x > (1f - margin) * b) {
                    float f = -margin - (x - b) / b;
                    walls.add(d, f);
                }
            }

        }

        acc     .add(coherence .mult(weights[0]))
                .add(separation.mult(weights[1]))
                .add(alignment .mult(weights[2]))
                .add(avoidance .mult(weights[3]))
                .add(walls     .mult(weights[4]));

        vel.add(acc.mult(invMass));
        vel.setMagnitude(3.0f * invMass);
        pos.add(vel);

        return this;
    }

    public Vector[] getDisplacements(ArrayList<Boid> others){
        Vector[] displacements = new Vector[others.size()];

        for(int i = 0; i < others.size(); i++){
            displacements[i] = pos.getDisplacement(others.get(i).pos);
        }

        return displacements;
    }

    public void draw(Graphics2D g2d, WindowSettings ws){
//        float h = pos.get(2) / bs.bounds().get(2);
        float r = 7.5f * (float)Math.sqrt(mass);

//        color = Color.getHSBColor(1, 0.7f, 0.7f);
        Vector drawPos = pos.positionToRenderPosition();
        g2d.setColor(color);
        g2d.fillOval((int) (drawPos.get(0) - r), (int) (drawPos.get(1) - r), (int) (2.0f * r), (int) (2.0f * r));

    }



}
