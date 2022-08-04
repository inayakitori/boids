package com.gmail.inayakitorikhurram.boids.window;

import java.awt.*;

public record WindowSettings(
        Color backgroundColor,
        int msPerFrame,
        float boidRadius,
        float drawScale
){}
