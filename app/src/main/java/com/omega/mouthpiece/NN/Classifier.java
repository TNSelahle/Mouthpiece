package com.omega.mouthpiece.NN;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.omega.mouthpiece.ConverterP.*;
import com.omega.mouthpiece.LoginPage;

import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;

import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.io.InputStream;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;

public class Classifier{ //kept for the NN







    public VoiceProfile userModel;
    MultiLayerNetwork model = null;

    public Classifier(Context c)
    {

        float[] temp = new float[500];
        float num = 12113312321132123231F;
        for(int y = 0; y<500;y++)
        {
            temp[y] = num;
        }
        SegmentNode[] sg = new SegmentNode[12];
        for(int i= 0;i<12;i++)
        {
            sg[i] = new SegmentNode(temp);
        }

         userModel = new VoiceProfile("LMAO",sg,c);
        System.out.println("Classifier object created!");
        //Paths.get(voice.h5);
        Log.e("testing","whatever error you want");
        String simpleMlp = new File("file:///android_asset/","voice.h5").getPath();
        System.out.println(simpleMlp);
        try {
            AssetManager am =c.getAssets();
            InputStream is = am.open("file:///android_asset/" +"voice.h5");

            model = KerasModelImport.importKerasSequentialModelAndWeights(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKerasConfigurationException e) {
            Log.e("testing",e.toString());
        } catch (UnsupportedKerasConfigurationException e) {
            Log.e("testing",e.toString());
        }
    }

    public int classifySegment(SegmentNode segment) //segmentNodes pass through the NN to return a category for the phonetic of the the audio data
    {

        INDArray features = Nd4j.zeros(new int[]{1,500});

        for(int i = 0; i < segment.audio.length; i++)
        {
            features.putScalar(new int[]{0,i},segment.audio[i]);
        }
//        for(int i = 0; i < 500; i++)
//        {
//            features.putScalar(new int[]{0,i},segment.audio[i]);
//        }

        INDArray outp = model.output(features);
        Number max = outp.maxNumber();
        int phonetic = 0;
        while(outp.getNumber(phonetic) != max)
            phonetic++;

        return phonetic;
    }

    public void trainFilter(String id, SegmentNode[] segments,Context c) //(initializes VoiceProfile)handler calls this. voice profile receives labelled segments nodes and a name for the version of voice profile for all categories.like the data sets used to train the NN. returns feedback
    {
        this.userModel = new VoiceProfile(id, segments,c);
    }

    public String getUserVoiceProfile() //handler calls this function to retrieve the name from the newly saved VP file.
    {
        return this.userModel.saveProfile();
    }

    public void replaceVoiceProfile(String id) //handler calls this to restore the voiceProfile's state with another textfile with the given file id name.
    {
        this.userModel = new VoiceProfile(id);
    }
}
