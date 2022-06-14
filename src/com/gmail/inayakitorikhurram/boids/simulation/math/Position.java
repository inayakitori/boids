package com.gmail.inayakitorikhurram.boids.simulation.math;

import com.gmail.inayakitorikhurram.boids.simulation.Boid;

import java.util.ArrayList;

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

    public static float[] getDisplacement(Position p1, Position p2){
        if(p1.dims != p2.dims){
            throw new IllegalArgumentException("Vector length mismatch");
        }
        //delta
        float[] disp = new float[p1.dims];
        for(int i = 0; i < p1.dims; i++){
            if(p1.p[i] < p1.bounds[i]/2){ //on negative side
                if(p2.p[i] - p1.p[i] < p1.bounds[i]/2){//non-toroidal gap is smaller
                    disp[i] = p2.p[i] - p1.p[i];
                } else{//toroidal gap is smaller
                    disp[i] = (p2.p[i] - p1.bounds[i]) - p1.p[i];
                }
            } else{ //on positive side
                if(p2.p[i] - p1.p[i] > - p1.bounds[i]/2){//non-toroidal gap is smaller
                    disp[i] = p2.p[i] - p1.p[i];
                } else{//toroidal gap is smaller
                    disp[i] = (p2.p[i] + p1.bounds[i]) - p1.p[i];
                }
            }
        }

        return disp;
    }

    public Position mult(float s){
        return new Position(MyMath.mult(s, p), bounds);
    }

    public static float[] getAverageDisplacement(Position p, ArrayList<Position> others){
        int n = others.size();
        int dims = others.get(0).dims;
        float[] v = new float[dims];

        for(Position other : others){
            v = MyMath.sum(v, Position.getDisplacement(p, other));
        }

        return MyMath.mult(1.0f/n, v);
    }

    public static float[] getAverageDisplacement(Position p, ArrayList<Boid> others, boolean hasBoids){
        int n = others.size();
        int dims = others.get(0).getPos().dims;
        float[] v = new float[dims];

        for(Boid other : others){
            v = MyMath.sum(v, Position.getDisplacement(p, other.getPos()));
        }

        return MyMath.mult(1.0f/n, v);
    }

}
