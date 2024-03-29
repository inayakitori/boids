package com.gmail.inayakitorikhurram.boids.simulation.math.position;

import com.gmail.inayakitorikhurram.boids.window.WindowSettings;

public class SpherePosition extends ToroidalPosition{

    protected int layer = 0;

    public SpherePosition( Vector v, Vector bounds, boolean[] isToroidal, int layer, Vector vel) {
        super(v, bounds, isToroidal, vel);
        this.layer = layer;
    }

    public SpherePosition(SpherePosition s){
        this((Vector)s, s.bounds, s.isToroidal, s.layer, s.vel);
    }

    @Override
    public ToroidalPosition add(int i, float dx) {
        v[i] += dx;
        float b = bounds.v[i];
        if(v[i] > b){//gone off positive side
            layer = 1 - layer;
            v[i] = 2*b - v[i];
            vel.mult(i, -1);
        } else if(v[i] < 0){
            layer = 1 - layer;
            v[i] *= -1;
            vel.mult(i, -1);
        }
        return this;
    }

    @Override
    public Vector getDisplacement(ToroidalPosition other) {
        return super.getDisplacement(other);
    }

    @Override
    public SpherePosition clone() {
        return new SpherePosition(this);
    }

    @Override
    public int[] positionToRenderPosition(WindowSettings ws) {
        if(layer == 0){
            return new int[]{(int) v[0], (int) v[1]};
        } else{
            return new int[]{(int) (v[0] + bounds.get(0) + 50f), (int) v[1]};
        }
    }

    @Override
    public Vector renderPositiontoPosition(WindowSettings ws, Vector v) {

        return super.renderPositiontoPosition(ws, v);
    }

    public static int[] requiredRenderSpace(WindowSettings ws, Vector bounds){

        Vector renderSpace = new Vector(bounds).mult(0, 2).add(0, 50);

        return new int[]{(int) renderSpace.v[0], (int) renderSpace.v[1]};
    }

}
