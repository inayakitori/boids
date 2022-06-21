package com.gmail.inayakitorikhurram.boids.simulation.math;

import com.gmail.inayakitorikhurram.boids.simulation.BoidSettings;

import java.awt.*;

public class ToroidalPosition extends Position{

    protected Vector bounds;

    public ToroidalPosition(int dims){
        super(dims);
        bounds = new Vector(dims);
        vel = new Vector(dims);
    }

    public ToroidalPosition(Vector v, Vector bounds, Vector vel){
        super(v, vel);
        if(v.dims != bounds.dims) {
            throw new IllegalArgumentException("number of position elements must match number of boundary elements");
        }

        this.bounds = new Vector(bounds);
        this.vel = new Vector(vel);
    }

    public ToroidalPosition(ToroidalPosition v){
        this(v, v.bounds, v.vel);
    }
    public ToroidalPosition add(final Vector dv){
        for(int i = 0; i < dims; i++){
            add(i, dv.v[i]);
        }
        return this;
    }

    @Override
    public ToroidalPosition add(int i, float dx){
        super.add(i, dx);
        float b = bounds.v[i];
        //not using mod cause need to detect changes so that klein bottle effects can be added later
        if(v[i] > b){
            v[i] -= b;
        } else if(v[i] < 0){
            v[i] += b;
        }
        return this;
    }

    public static Vector getDisplacement(ToroidalPosition p1, ToroidalPosition p2){

        return p1.getDisplacement(p2);
    }

    public Vector getDisplacement(ToroidalPosition other){
        if(dims != other.dims){
        throw new IllegalArgumentException("Vector length mismatch");
        }
        //delta
        Vector disp = new Vector(dims);
        for(int i = 0; i < dims; i++){
            if(v[i] < bounds.v[i]/2){ //on negative side
                if(other.v[i] - v[i] < bounds.v[i]/2){//non-toroidal gap is smaller
                    disp.v[i] = other.v[i] - v[i];
                } else{//toroidal gap is smaller
                    disp.v[i] = (other.v[i] - bounds.v[i]) - v[i];
                }
            } else{ //on positive side
                if(other.v[i] - v[i] > - bounds.v[i]/2){//non-toroidal gap is smaller
                    disp.v[i] = other.v[i] - v[i];
                } else{//toroidal gap is smaller
                    disp.v[i] = (other.v[i] + bounds.v[i]) - v[i];
                }
            }
        }

        return disp;
    }

    //TODO fix :c
    @Override
    public ToroidalPosition getSlice(int i, int j){
        ToroidalPosition slice = new ToroidalPosition(j + 1 - i);
        for(int k = 0; k < slice.dims; k++){
            slice.v[i+k] = v[i+k];
            slice.bounds.v[i+k] = bounds.v[i+k];
            slice.vel.v[i+k] = vel.v[i+k];
        }
        return slice;
    }

    @Override
    public ToroidalPosition getExpanded(int newDims, int startingIndex) {
        return new ToroidalPosition(
                super.getExpanded(newDims, startingIndex),
                bounds.getExpanded(newDims, startingIndex),
                vel.getExpanded(newDims, startingIndex));
    }
/*
    public static Vector getAverageDisplacement(Position p, ArrayList<Position> others){
        int n = others.size();
        int dims = others.get(0).dims;
        Vector avg = new Vector(dims);

        for(Vector other : others){
            avg.add(Position.getDisplacement(p, other));
        }

        return avg.mult(1.0f/n);
    }

    public static Vector getAverageDisplacement(Position p, ArrayList<Boid> others, boolean hasBoids){
        int n = others.size();
        int dims = others.get(0).getPos().dims;
        Vector avg = new Vector(dims);

        for(Boid other : others){
            avg.add(Position.getDisplacement(p, other.getPos()));
        }

        return avg.mult(1.0f/n);
    }
*/

}
