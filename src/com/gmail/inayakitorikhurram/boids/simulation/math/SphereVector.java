package com.gmail.inayakitorikhurram.boids.simulation.math;

public class SphereVector extends ToroidalPosition{

    protected int layer = 0;

    public SphereVector(Vector v, Vector bounds, int layer, Vector vel) {
        super(v, bounds, vel);
        this.layer = layer;
    }

    public SphereVector(SphereVector s){
        this((Vector)s, s.bounds, s.layer, s.vel);
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
    public int[] positionToRenderPosition() {
        if(layer == 0){
            return new int[]{(int) v[0], (int) v[1]};
        } else{
            return new int[]{(int) (v[0] + bounds.get(0) + 50f), (int) v[1]};
        }
    }

    @Override
    public Vector renderPositiontoPosition(Vector v) {
        return super.renderPositiontoPosition(v);
    }

    public static int[] requiredRenderSpace(Vector bounds){

        Vector renderSpace = new Vector(bounds).mult(0, 2).add(0, 50);

        return new int[]{(int) renderSpace.v[0], (int) renderSpace.v[1]};
    }

}
