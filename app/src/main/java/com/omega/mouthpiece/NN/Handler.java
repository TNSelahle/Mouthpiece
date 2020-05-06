package com.omega.mouthpiece.NN;
import com.omega.mouthpiece.ConverterP.*;

public class Handler {

    Classifier classifier;
    SegmentNode segment;

    public Handler()
    {
        System.out.println("Handler object created!");
        this.classifier = new Classifier();
    }

    ///////////////CONVERTER FUNCTIONS///////////////
    public int getPhonetic(SegmentNode segment)
    {//NB might have to change return type to be integer
        System.out.println("getting phonetic!");
        return classifier.classifySegment(segment);
    }

    ///////////////USER MANAGEMENT FUNCTIONS///////////////
    public void trainVoiceProfile(String id, SegmentNode[] voiceSegments)
    {   /* BritneyChu -- receives 12 segments( has a populated array of float[500] and populated Label in "12-category-formants")*/
        System.out.println("sending filter training data node...");
        classifier.trainFilter(id,voiceSegments);
    }

    public String getUserVoiceProfile()
    {   /*BritneyChu -- User Management Module - Returns the current Voice Profile name.*/
        System.out.println("creating Voice Profile file export...");
        return classifier.getUserVoiceProfile();
    }

    public void replaceUserVoiceProfile(String id)  //////////////////////////////*******//////////////////////////
    {   /*BritneyChu -- User Management Module - Received file format to replace the classifier's current voice profile. Returns classifier's feedback.*/
        System.out.println("updating Voice Profile with import...");
        classifier.replaceVoiceProfile(id);
    }
}
