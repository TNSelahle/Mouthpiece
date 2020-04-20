package com.omega.mouthpiece;

public class NN_Handler {
    //Python Interpreter/////////////
//    PythonInterpreter r = new PythonInterpreter();
//    r.execfile("Classifier.py");
//    PyObject classifier = r.get("Classifier");
//    PyObjectDerived p = (PyObjectDerived) o.__call__();
    /////////////////////////////////

    Classifier classifier;
    NNModel serverNetwork;
    public NN_Handler()
    {
        System.out.println("Handler object created!");
         classifier = new Classifier();
    }

    public SegmentNode getPhonetic(SegmentNode segment)
    {
        System.out.println("getting phonetic!");
        return classifier.classifySegment(segment);
    }

    private SegmentNode classifySegment()
    {
        System.out.println("Classifying Segment");
        //use  the nn model with the users filter
        //->done after NN
        return null;
    }

    public String trainVoiceProfile(SegmentNode voiceSegement) 
    {   /* BritneyChu -- NB!!!! This function was not required from us yet 
        from integration team but we need it and someone needs to provide it. 
        
        The function reeceives audio segments, a populated array of float[500] 
        and a Label of what the audio contains in "phonetic form" as integer
        (follows the "12-category-formants" picture to describe the phonetic)
        it returns an empty string or some useful information as feedback,
        such as errors or warnings. This could be the voice profile class feedback 
        describing what the neural network was able to achieve or what the neural network 
        perceived the audio to be before it changed.
        */
        return null;
    }

    public VoiceProfile_Trainer getUserVoiceProfile()
    {   /*BritneyChu -- User Management Module needs this
        Returns the current Voice Profile only if it has been calibrated correctly
        It will be exported in a format (some file) for the User Management Module
        to save.
         */
        return null;
    }

    public String replaceUserVoiceProfile(VoiceProfile_Trainer backupProfile)
    {   /*BritneyChu -- User Management Module needs this
        Use this received file format to change or "replace" the current voice profile.  
        */
        return null;
    }



}