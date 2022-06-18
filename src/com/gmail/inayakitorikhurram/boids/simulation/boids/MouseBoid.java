package com.gmail.inayakitorikhurram.boids.simulation.boids;

import com.gmail.inayakitorikhurram.boids.simulation.BoidSettings;
import com.gmail.inayakitorikhurram.boids.simulation.math.*;

import java.util.ArrayList;

public class MouseBoid extends Boid{
    public MouseBoid(BoidSettings bs) {
        super(bs);
        mouse = new Vector(new float[]{0f, 0f});
        pos = new Vector(mouse);
    }

    Vector mouse;
    @Override
    public MouseBoid update(ArrayList<Boid> friends) {

        pos.set(0, mouse.get(0));
        pos.set(1, mouse.get(1));

        return this;
    }

    public void setMousePosition(float mouseX, float mouseY){
        mouse = pos.renderPositiontoPosition(new Vector(new float[]{mouseX, mouseY}));
    }

}
