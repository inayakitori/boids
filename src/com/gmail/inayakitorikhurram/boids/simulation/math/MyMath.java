package com.gmail.inayakitorikhurram.boids.simulation.math;

import java.util.ArrayList;

public final class MyMath {


    public static float lerp(float a, float b, float t){
        return a * (1.0f - t) + b * t;
    }

    public static Vector lerp(Vector a, Vector b, float t){
        return new Vector(a).mult(1.0f-t)
                .add(new Vector(b).mult(t));
    }

    //random
    public static Vector randomVector(final Vector max){
        return randomVector(new Vector(max.dims), max);
    }

    public static Vector randomVector(final Vector min, final Vector max) {
        if(min.dims != max.dims){
            throw new IllegalArgumentException("minimum bounds must match maximum bounds");
        }
        Vector v = new Vector(min.dims);
        for(int i = 0; i < min.dims; i++){
            v.v[i] = MyMath.random(min.v[i], max.v[i]);
        }

        return v;
    }

    public static float random(float min, float max){
        return lerp(min, max, (float) Math.random());
    }



}
