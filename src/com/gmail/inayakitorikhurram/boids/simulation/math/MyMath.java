package com.gmail.inayakitorikhurram.boids.simulation.math;

import com.gmail.inayakitorikhurram.boids.simulation.math.position.Vector;

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
    public static float[] randomArray(float min, float max, int dims){
        float[] v = new float[dims];
        for(int i = 0; i < dims; i++){
            v[i] = random(min, max);
        }
        return v;
    }

    public static float[] randomArray(float[] min, float[] max){
        if(min.length != max.length){
            throw new IllegalArgumentException("minimum bounds must match maximum bounds");
        }
        float[] v = new float[min.length];
        for(int i = 0; i < min.length; i++){
            v[i] = random(min[i], max[i]);
        }
        return v;
    }

    public static Vector randomVector(final Vector min, final Vector max) {
        if(min.dims != max.dims){
            throw new IllegalArgumentException("minimum bounds must match maximum bounds");
        }
        Vector v = new Vector(min.dims);
        for(int i = 0; i < min.dims; i++){
            v.set(i,MyMath.random(min.get(i), max.get(i)));
        }

        return v;
    }

    public static float random(float min, float max){
        return lerp(min, max, (float) Math.random());
    }



}
