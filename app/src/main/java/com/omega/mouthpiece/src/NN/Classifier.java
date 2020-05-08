
/*
import Converter.*;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class Classifier{ //kept for the NN

    public VoiceProfile userModel;
	MultiLayerNetwork model = null;
    
    public Classifier()
    {
        System.out.println("Classifier object created!");
        String simpleMlp = new File("models/voice.h5").getPath();

		try {
			model = KerasModelImport.
					importKerasSequentialModelAndWeights(simpleMlp);
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
        INDArray features = Nd4j.zeros(new int[]{1,500});

		for(int i = 0; i < 500; i++)
		{
			features.putScalar(new int[]{0,i},segment.audio[i]);
		}

		INDArray outp = model.output(features);
		Number max = outp.maxNumber();
		int phonetic = 0;
		while(outp.getNumber(phonetic) != max)
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
}*/
