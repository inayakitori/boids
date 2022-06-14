package com.gmail.inayakitorikhurram.boids.simulation.math;

public final class MyMath {

    public static float[] clamp(float[] v, float maxMag){
        if(maxMag == 0) return new float[v.length];
        float mag2 = getMag2(v);

        if(mag2!=0 && mag2 > maxMag * maxMag){
            return mult(maxMag / ((float) Math.sqrt(mag2)), v);
        }

        return v;
    }

    public static float[] mult(float s, float[] v){
        for(int i = 0; i < v.length; i++){
               v[i] *= s;
        }
        return v;
    }

    public static float getMag2(float[] v){
        float mag2 = 0;

        for(float f : v){
            mag2 += f * f;
        }
        return mag2;
    }

    public static float getMag(float[] v){
        return (float) Math.sqrt(getMag2(v));
    }
}
