package Converter;

public class SegmentNode
{
//    public float[] audio = new float[120]; //break audio into smaller segments;
    public float[] audio;// = new float[500]; //break audio into smaller segments;
    public int label;

    public SegmentNode(float[] audio) //audio passed from top-down towards NN
    {
        //System.out.println("SegmentNode object created!");
        label = 0;
        this.audio = new float[audio.length];
        for (int i = 0; i < audio.length; i++)
        {
            this.audio[i] = audio[i];
        }
    }

    // I added this contructor so that I could use it inside the VoiceProfile class
    public SegmentNode() {

    }

    public float[] getAudio()
    {
        return this.audio;
    }

    public void printAudio(){
        for(int i = 0; i < audio.length; i++) {
            System.out.println("audio[" + i + "]" + " = " + this.audio[i] + " (with id = " + label + ")");
        }
    }

    public int getLabel()
    {
        return this.label;
    }

    public void setLabel(int label)
    {
        this.label = label;
    }

    public void setAudio(float[] audio){
        this.audio = audio;
    }

}
