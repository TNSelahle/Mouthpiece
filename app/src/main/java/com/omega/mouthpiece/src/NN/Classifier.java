package NN;
import Converter.*;
import org.apache.log4j.BasicConfigurator;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.io.File;
import java.io.IOException;

public class Classifier{ //kept for the NN

    public VoiceProfile userModel;
    MultiLayerNetwork model = null;

    public Classifier()
    {
        System.out.println("Classifier object created!");
        String simpleMlp = new File("models/voice.h5").getPath();

        try {
            java.io.PrintStream defaultout = System.out;
            System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
                @Override public void write(int b) {}
            }) {
                @Override public void flush() {}
                @Override public void close() {}
                @Override public void write(int b) {}
                @Override public void write(byte[] b) {}
                @Override public void write(byte[] buf, int off, int len) {}
                @Override public void print(boolean b) {}
                @Override public void print(char c) {}
                @Override public void print(int i) {}
                @Override public void print(long l) {}
                @Override public void print(float f) {}
                @Override public void print(double d) {}
                @Override public void print(char[] s) {}
                @Override public void print(String s) {}
                @Override public void print(Object obj) {}
                @Override public void println() {}
                @Override public void println(boolean x) {}
                @Override public void println(char x) {}
                @Override public void println(int x) {}
                @Override public void println(long x) {}
                @Override public void println(float x) {}
                @Override public void println(double x) {}
                @Override public void println(char[] x) {}
                @Override public void println(String x) {}
                @Override public void println(Object x) {}
                @Override public java.io.PrintStream printf(String format, Object... args) { return this; }
                @Override public java.io.PrintStream printf(java.util.Locale l, String format, Object... args) { return this; }
                @Override public java.io.PrintStream format(String format, Object... args) { return this; }
                @Override public java.io.PrintStream format(java.util.Locale l, String format, Object... args) { return this; }
                @Override public java.io.PrintStream append(CharSequence csq) { return this; }
                @Override public java.io.PrintStream append(CharSequence csq, int start, int end) { return this; }
                @Override public java.io.PrintStream append(char c) { return this; }
            });

            BasicConfigurator.configure();
            model = KerasModelImport.
                    importKerasSequentialModelAndWeights(simpleMlp);

            System.setOut(defaultout);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKerasConfigurationException e) {
            e.printStackTrace();
        } catch (UnsupportedKerasConfigurationException e) {
            e.printStackTrace();
        }
    }

    public int classifySegment(SegmentNode segment) //segmentNodes pass through the NN to return a category for the phonetic of the the audio data
    {
        INDArray features = Nd4j.zeros(new int[]{1, 500});

        for (int i = 0; i < segment.audio.length; i++) {
            features.putScalar(new int[]{0, i}, segment.audio[i]);
        }

        INDArray outp = model.output(features);
        Number max = outp.maxNumber();
        int phonetic = 0;
        while (!outp.getNumber(phonetic).equals(max))
            phonetic++;

        return phonetic;
    }

    public void trainFilter(String id, SegmentNode[] segments) //(initializes VoiceProfile)handler calls this. voice profile receives labelled segments nodes and a name for the version of voice profile for all categories.like the data sets used to train the NN. returns feedback
    {
        this.userModel = new VoiceProfile(id, segments);
    }

    public String getUserVoiceProfile() //handler calls this function to retrieve the name from the newly saved VP file.
    {
        return this.userModel.saveProfile();
    }

    public void replaceVoiceProfile(String id) //handler calls this to restore the voiceProfile's state with another textfile with the given file id name.
    {
        this.userModel = new VoiceProfile(id);
    }

    public SegmentNode[] getVoiceProfileSegments() {
        return this.userModel.getNodes();
    }
}
