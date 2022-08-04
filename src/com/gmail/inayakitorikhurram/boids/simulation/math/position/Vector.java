package com.gmail.inayakitorikhurram.boids.simulation.math.position;

import java.util.Arrays;

public class Vector {

    protected float[] v;
    public final int dims;

    public Vector(int dims){
        this.dims = dims;
        v = new float[dims];
    }

    public Vector(float value, int dims){
        this.dims = dims;
        v = new float[dims];
        for(int i = 0; i < dims; i++){
            v[i] = value;
        }
    }

    public Vector(float[] v){
        this.dims = v.length;
        this.v = new float[dims];
        for(int i = 0; i < dims; i++) {
            this.v[i] = v[i];
        }
    }

    public Vector(Vector v){
        this(v.get());
    }



    public boolean isNumeric(){
        for(int i = 0; i < dims; i++){
            if(!Float.isFinite(v[i])) return false;
        }
        return true;
    }

    //TODO store the magnitudes as a field and just call to update everytime it's updated
    public float getMag2(){
        float mag2 = 0;
        for(float f : v){
            mag2 += f * f;
        }
        return mag2;
    }

    public float getMag(){
        return (float)(Math.sqrt(getMag2()));
    }

    public Vector setMagnitude(float s){
        float mag = getMag();
        if(mag == 0) return this;
        return mult(s / mag);
    }
    public void clamp(float s) {
        if(getMag2() > s * s){
            setMagnitude(s);
        }
    }

    public Vector normalise(){
        return setMagnitude(1.0f);
    }

    public Vector add(final Vector dv){
        for(int i = 0; i < dims; i++){
            add(i, dv.v[i]);
        }
        return this;
    }

    public Vector add(final float[] dv){
        for(int i = 0; i < dims; i++){
            add(i, dv[i]);
        }
        return this;
    }

    public Vector add(int i, float dx){
        v[i] += dx;
        return this;
    }

    public Vector square(){
        for(int i = 0; i < dims; i++){
            v[i] = v[i] * v[i];
        }
        return this;
    }

    public Vector pow(float a){
        for(int i = 0; i < dims; i++) {
            v[i] = (float) Math.pow(v[i],a);
        }
        return this;
     }

    public float[] get(){
        return v;
    }

    public boolean set(final float[] v){
        if(this.v.length != v.length) throw new IllegalArgumentException("Out of range");

        this.v = new float[dims];
        for(int i = 0; i < dims; i++) {
            this.v[i] = v[i];
        }
        return true;
    }

    public boolean set(int i, float val){
        if(i < 0 || dims <= i) throw new IllegalArgumentException("Out of range");
        v[i] = val;
        return true;
    }

    public float get(int i){
        if(i < 0 || dims <= i) throw new IllegalArgumentException("Out of range");
        return v[i];
    }

    public Vector getSlice(int i, int j){
        Vector slice = new Vector(j + 1 - i);
        for(int k = 0; k < slice.dims; k++){
            slice.v[i+k] = v[i+k];
        }
        return slice;
    }

    public Vector getExpanded(int newDims, int startingIndex){
        Vector expanded = new Vector(newDims);
        for(int i = startingIndex; i < startingIndex + dims; i++){
            expanded.v[i] = v[i];
        }
        return expanded;
    }

    public Vector getDisplacement(final Vector other){
        if(dims != other.dims){
            throw new IllegalArgumentException("Vector length mismatch");
        }

        Vector disp = new Vector(dims);

        for(int i = 0; i < dims; i++){
            disp.v[i] = other.v[i] - v[i];
        }

        return disp;

    }

    public Vector mult(int i, float s){
        v[i]*=s;
        return this;
    }

    public Vector mult(float s){
        for(int i = 0; i < dims; i++){
            v[i] *= s;
        }
        return this;
    }




/*
    public static Vector getAverageDisplacement(final Vector v, ArrayList<Vector> others){
        int n = others.size();
        int dims = others.get(0).dims;
        Vector avg = new Vector(dims);

        for(Vector other : others){
            avg.add(Vector.getDisplacement(v, other));
        }

        return avg.mult(1.0f/n);
    }

    public static Vector getAverageDisplacement(final Vector v, ArrayList<Boid> others, boolean hasBoids){
        int n = others.size();
        int dims = others.get(0).getPos().dims;
        Vector avg = new Vector(dims);

        for(Boid other : others){
            avg.add(Vector.getDisplacement(v, (Vector) other.getPos()));
        }

        return avg.mult(1.0f/n);
    }
*/

}
