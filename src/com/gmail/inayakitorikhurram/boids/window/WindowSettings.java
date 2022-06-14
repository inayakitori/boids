package com.gmail.inayakitorikhurram.boids.window;

import java.awt.*;

public record WindowSettings(
        int width,
        int height,
        Color backgroundColor,
        int msPerFrame
){}
