package com.gmail.inayakitorikhurram.boids.simulation;

import com.gmail.inayakitorikhurram.boids.simulation.math.Vector;

//global settings for the boids
public record BoidSettings(
    Vector bounds,
    float minMass,
    float maxMass,
    int msPerTick,
    int boidCount,
    float[] defaultWeights
)
{}
