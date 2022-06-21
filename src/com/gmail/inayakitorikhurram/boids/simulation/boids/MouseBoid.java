package com.gmail.inayakitorikhurram.boids.simulation.boids;

import com.gmail.inayakitorikhurram.boids.simulation.BoidSettings;
import com.gmail.inayakitorikhurram.boids.simulation.math.*;
import com.gmail.inayakitorikhurram.boids.window.WindowSettings;

import java.util.ArrayList;

public class MouseBoid extends Boid{
    private float mouseRange = 300;
    private float mouseRange2 = mouseRange * mouseRange;
    public MouseBoid(BoidSettings bs) {
        super(bs);
        mouse = new Vector(bs.bounds().dims);
        pos = new ToroidalPosition(mouse, bs.bounds(), new Vector(bs.bounds().dims));

        colorHSB[0] = 0.5f;
        colorHSB[1] = 0.5f;
        colorHSB[2] = 0.5f;

        colorHSB = pos.getColor(bs, colorHSB);

    }

    Vector mouse;
    @Override
    public MouseBoid update(ArrayList<Boid> friends) {

        pos.set(0, mouse.get(0));
        pos.set(1, mouse.get(1));

        colorHSB = pos.getColor(bs, colorHSB);
        return this;
    }

    public void setMousePosition(WindowSettings ws,float mouseX, float mouseY){
        mouse = pos.renderPositiontoPosition(ws, new Vector(new float[]{mouseX, mouseY}));
    }

    public float getRange2(){
        return mouseRange2;
    }

}
