package com.gmail.inayakitorikhurram.boids.simulation.math;

import java.util.ArrayList;

public final class MyMath {
    public static float[] randomVector(float[] max){
        return randomVector(new float[max.length], max);
    }

    public static float[] randomVector(float[] min, float[] max) {
        if(min.length != max.length){
            throw new IllegalArgumentException("minimum bounds must match maximum bounds");
        }
        float[] v = new float[min.length];
        for(int i = 0; i < min.length; i++){
            float t = (float)Math.random();
            v[i] = ( min[i] * (1.0f-t) ) + ( max[i] * t );
        }

        return v;
    }

    public static int[] randomVectori(int[] bounds){
        int[] v = new int[bounds.length];
        for(int i = 0; i < bounds.length; i++){
            v[i] = (int)(Math.random() * bounds[i]);
        }

        return v;
    }

    public static float[] clamp(final float[] v,final float maxMag){
        if(maxMag == 0) return new float[v.length];
        float mag2 = getMag2(v);

        if(mag2!=0 && mag2 > maxMag * maxMag){
            return mult(maxMag / ((float) Math.sqrt(mag2)), v);
        }

        return v;
    }

    public static float[] mult(final float s,final float[] v){
        float[] vs = new float[v.length];
        for(int i = 0; i < v.length; i++){
               vs[i] = v[i] * s;
        }
        return vs;
    }

    public static float[] sum(final float[] v1,final float[] v2){
        if(v1.length != v2.length){
            throw new IllegalArgumentException("v1.length != v2.length as " + v1.length + " != " + v2.length);
        }
        float[] v3 = new float[v1.length];
        for(int i = 0; i < v1.length; i++){
            v3[i] = v1[i] + v2[i];
        }
        return v3;
    }

    public static float getMag2(final float[] v){
        float mag2 = 0;

        for(float f : v){
            mag2 += f * f;
        }
        return mag2;
    }

    public static float getMag(final float[] v){
        return (float) Math.sqrt(getMag2(v));
    }

    public static float[] average(final ArrayList<float[]> others){
        int n = others.size();
        int dims = others.get(0).length;
        float[] v = new float[dims];

        for(float[] other : others){
            for(int i = 0; i < dims; i++){
                v[i] += other[i];
            }
        }

        return mult(1.0f/n, v);
    }

    public static float[] average(final float[][] others){
        int n = others.length;
        int dims = others[0].length;
        //others = float[n][dims]
        float[] v = new float[dims];

        for(int vectori = 0; vectori < n; vectori++){
            for(int dimi = 0; dimi < dims; dimi++){
                v[dimi] += others[vectori][dimi];
            }
        }

        return mult(1.0f/n, v);
    }



}
