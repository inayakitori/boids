package com.gmail.inayakitorikhurram.boids.simulation;

import com.gmail.inayakitorikhurram.boids.simulation.math.position.Vector;

//global settings for the boids
public record BoidSettings(
    Vector bounds,
    boolean[] isToroidal,
    float minMass,
    float maxMass,
    float maxSpeed,
    int msPerTick,
    int boidCount,
    float[] defaultWeights
)
{}
