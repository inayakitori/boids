package com.gmail.inayakitorikhurram.boids.simulation;

//global settings for the boids
public record BoidSettings(
    float[] bounds,
    float minMass,
    float maxMass,
    int msPerTick
)
{}
