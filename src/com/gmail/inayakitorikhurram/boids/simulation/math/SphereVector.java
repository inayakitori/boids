package com.gmail.inayakitorikhurram.boids.simulation.math;

public class SphereVector extends ToroidalPosition{

    protected int layer = 0;

    public SphereVector(Vector v, Vector bounds, int layer) {
        super(v, bounds);
        this.layer = layer;
    }

    public SphereVector(SphereVector s){
        this(s.v, s.bounds, s.layer);
    }

}
