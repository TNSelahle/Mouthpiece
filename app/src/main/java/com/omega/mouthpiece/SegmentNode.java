package com.omega.mouthpiece;

public class SegmentNode {
    private float[] audio;
    private int label;

    public SegmentNode(float[] audio)
    {
        this.audio = new float[500];
        this.audio = audio;
        label = -1;
    }

    public float[] getAudio()
    {
        return this.audio;
    }

    public int getLabel()
    {
        return this.label;
    }

    public void setLabel(int label)
    {
        this.label = label;
    }
}
