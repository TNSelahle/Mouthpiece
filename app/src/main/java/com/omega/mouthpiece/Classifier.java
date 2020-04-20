package com.omega.mouthpiece;

public class Classifier { //kept for the NN

    public VoiceProfile_Trainer userModel;
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

    public VoiceProfile_Trainer getUserVoiceProfile()
    {
        return null;
    }

    public void replaceVoiceProfile(VoiceProfile_Trainer updatedVoiceProfile)
    {

    }

    public VoiceProfile_Trainer getFilter() ///???!!!!
    {
        //more a concept
        return null;
    }
    public String trainFilter(VoiceProfile_Trainer updatedModel) ///???!!!!
    {
        //after the NN
        //training the filter
        return null;
    }

}