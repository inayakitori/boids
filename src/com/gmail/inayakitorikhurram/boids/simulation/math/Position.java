package com.gmail.inayakitorikhurram.boids.simulation.math;

import com.gmail.inayakitorikhurram.boids.simulation.BoidSettings;
import com.gmail.inayakitorikhurram.boids.window.WindowSettings;

import java.awt.*;

public class Position extends Vector{
    public Vector vel;


    public Position(int dims){
        super(dims);
    }

    public Position(Vector v, Vector vel) {
        super(v);
        this.vel = new Vector(vel);
    }

    public Position(Position pos){
        super(pos);
        this.vel = new Vector(pos.vel);
    }

    public float[] getColor(BoidSettings bs, float[] prevColor){
        float h = prevColor[0];
        float s = prevColor[1];
        float b = prevColor[2];

        Vector bounds = bs.bounds();

        if(bounds.dims >= 3){
            h = v[2]/bounds.v[2];
            if(bounds.dims >= 4){
                b = v[3]/bounds.v[3];
                if(bounds.dims >= 5){
                    s = v[4]/bounds.v[4];
                }
            }
        }

        return new float[]{h, s, b};
    }

    public int[] positionToRenderPosition(WindowSettings ws){
        return new int[]{(int) (v[0]/ ws.drawScale()), (int) (v[1] / ws.drawScale())};
    }
    public Vector renderPositiontoPosition(WindowSettings ws, Vector v){
        return new Vector(v).mult(ws.drawScale());
    }

    public static int[] requiredRenderSpace(WindowSettings ws, Vector bounds){

        return new int[]{(int) (bounds.v[0]/ ws.drawScale()), (int) (bounds.v[1] / ws.drawScale())};
    }

}
