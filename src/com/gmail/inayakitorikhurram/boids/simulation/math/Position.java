package com.gmail.inayakitorikhurram.boids.simulation.math;

public class Position {

    private float[] p;
    private final float[] bounds;
    private int dims;

    public Position(float[] p, float[] bounds){
        if(p.length != bounds.length) {
            throw new IllegalArgumentException("number of position elements must match number of boundary elements");
        }

        this.dims = p.length;
        this.bounds = bounds;
        this.p = p;

    }

    public Position(Position p){
        this(p.p, p.bounds);
    }

    public float[] add(float[] dp){
        for(int i = 0; i < dims; i++){
            add(i, dp[i]);
        }
        return p;
    }
    public float[] add(int i, float dx){
        p[i] += dx;
        float b = bounds[i];
        //not using mod cause need to detect changes so that klein bottle effects can be added later
        if(p[i] > b){
            p[i] -= b;
        } else if(p[i] < 0){
            p[i] += b;
        }
        return p;
    }

    public float[] get(){
        return p;
    }

    public float get(int i){
        return p[i];
    }



}
