package com.omega.mouthpiece;

public class Classifier { //kept for the NN

    public VoiceProfile userModel;
    public Classifier()
    {
        System.out.println("Classifier created!");
    }

    public SegmentNode classifySegment(SegmentNode segment)
    {
        return null;
    }

    public String trainModel(SegmentNode voiceSegment)
    {
        return null;
    }

    public VoiceProfile getUserVoiceProfile()
    {
        return null;
    }

    public void replaceVoiceProfile(VoiceProfile updatedVoiceProfile)
    {

    }

    public VoiceProfile getFilter() ///???!!!!
    {
        //more a concept
        return null;
    }
    public String trainFilter(VoiceProfile updatedModel) ///???!!!!
    {
        //after the NN
        //training the filter
        return null;
    }

}