package com.gmail.inayakitorikhurram.boids.simulation.boids;

import com.gmail.inayakitorikhurram.boids.simulation.BoidSettings;
import com.gmail.inayakitorikhurram.boids.simulation.math.SphereVector;
import com.gmail.inayakitorikhurram.boids.simulation.math.Vector;
import com.gmail.inayakitorikhurram.boids.window.WindowSettings;

import java.awt.*;
import java.util.ArrayList;

public class TraceBoid extends Boid{

    private int traceLength;
    private int traceGap;
    private int currentTraceGap;
    private Vector[] trace;

    public TraceBoid(BoidSettings bs, int traceLength, int traceGap) {
        super(bs);
        this.traceLength = traceLength;
        this.traceGap = traceGap;
        trace = new Vector[traceLength];
        for(int i = 0; i < traceLength; i++){
            int[] renderPos = pos.positionToRenderPosition();
            trace[i] = new Vector(new float[]{renderPos[0],renderPos[1]});
        }
    }

    @Override
    public Boid update(ArrayList<Boid> friends) {
        currentTraceGap++;
        if(currentTraceGap < traceGap){
            return super.update(friends);
        }
        currentTraceGap = 0;
        for(int i = 0; i < traceLength - 1; i++){
            trace[i] = trace[i+1];
        }
        super.update(friends);
        int[] renderPos = pos.positionToRenderPosition();
        trace[traceLength-1] = new Vector(new float[]{renderPos[0],renderPos[1]});
        return this;
    }

    @Override
    public void draw(Graphics2D g2d, WindowSettings ws) {
        super.draw(g2d, ws);
        for(int i = 0; i < traceLength; i++){
            //float h = pos.get(2) / bs.bounds().get(2);
            float r = 1f + 4f * i/(float)traceLength * (float)Math.sqrt(mass);
            Color color = Color.getHSBColor(colorHSB[0], colorHSB[1], colorHSB[2]);
            drawCircle(g2d, trace[i].positionToRenderPosition(), color, r);
        }
    }
}
