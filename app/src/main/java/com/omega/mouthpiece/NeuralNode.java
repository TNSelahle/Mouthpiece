package com.omega.mouthpiece;

public class NeuralNode {

    public float weight;
    public float index;
    public float layer;
    public NeuralNode[] children;
    public int childCounter = 0;

    public NeuralNode() {
    }

    public NeuralNode(float weight, float index, float layer)
    {
        this.weight = weight;
        this.index = index;
        this.layer = layer;
        //childern = new NeuralNode(weight, index, layer);
        childCounter++;
    }

    public SegmentNode classifySegment(SegmentNode segment)
    {
        return null;
    }

    public String train(SegmentNode segment)
    {
        return null;
    }

    public String updateWeights()
    {
        return null;
    }
}