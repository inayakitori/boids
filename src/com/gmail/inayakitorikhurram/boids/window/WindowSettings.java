package com.gmail.inayakitorikhurram.boids.window;

import com.gmail.inayakitorikhurram.boids.simulation.math.Vector;

import java.awt.*;

public record WindowSettings(
        Color backgroundColor,
        int msPerFrame,
        float boidRadius,
        float drawScale
){}
