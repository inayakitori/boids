package com.gmail.inayakitorikhurram.boids.simulation.math;

public class Position {

    private int[] p;
    private final int[] bounds;
    private int dims;

    public Position(int[] p, int[] bounds){
        if(p.length != bounds.length) {
            throw new IllegalArgumentException("number of position elements must match number of bound elements");
        }

        this.dims = p.length;
        this.bounds = bounds;
        this.p = p;

    }

    public Position(Position p){
        this(p.p, p.bounds);
    }

    public int[] add(int[] dp){
        for(int i = 0; i < dims; i++){
            add(i, dp[i]);
        }
        return p;
    }
    public int[] add(int i, int dx){
        p[i] += dx;
        int b = bounds[i];
        //not using mod cause need to detect changes so that klein bottle effects can be added later
        if(p[i] > b){
            p[i] -= b;
        } else if(p[i] < 0){
            p[i] += b;
        }
        return p;
    }

    public int[] get(){
        return p;
    }

    public int get(int i){
        return p[i];
    }

    public static Position randomPos(int[] bounds){
        int dims = bounds.length;
        Position p = new Position(new int[dims], bounds);

        for(int i = 0; i < dims; i++){
            p.p[i] = (int)(Math.random() * bounds[i]);
        }

        return p;
    }

}
