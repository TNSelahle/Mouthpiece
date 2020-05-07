package com.omega.mouthpiece.ConverterP;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedWriter;

public class VoiceProfile {

    private SegmentNode nodes[] = new SegmentNode[12];
    private String id;


    private Context context;

    // This contructor is used to create new voice profiles
    public VoiceProfile(String newId, SegmentNode[] given, Context c){
        this.id = newId;
        context = c;
        if (given.length > 12){
            System.out.println("There are too many nodes in the passed in parameter.");
            return;
        } else if (given.length < 12){
            System.out.println("There are too few nodes in the passed in paramater");
            return;
        }

        for (int i = 0; i < nodes.length; i++){
            // This should more than likely be replaced with a deep copy to ensure that we
            // will not replace the nodes data on accident
            this.nodes[i] = given[i];
        }
    }
    // This constructor is used to load old voice profile information
    // instead of having to use a "getProfile" type method
    public VoiceProfile(String id){
        this.id = id;
        try {
            File f = new File(id + ".txt");
            Scanner reader = new Scanner(f);

            int count = 0;

            while (reader.hasNextLine()){
                // A redundant check to make sure we aren't breaking beyond the scope of how
                // many nodes that we have.
                if (count >= 12){
                    // We have GONE TOO FAR.
                    break;
                }

                SegmentNode temp = new SegmentNode();
                String data = reader.nextLine();
                float[] tempAudio = new float[500];
                int tempLabel = 0;
                int internalCounter = 0;
                boolean label = false;
                for (String s: data.split(" ")){
                    // The label is always the first value
                    if (!label){
                        tempLabel = Integer.parseInt(s);
                        label = true;
                        break;
                    }
                    if (internalCounter < 500){
                        tempAudio[internalCounter] = Float.parseFloat(s);
                        internalCounter++;
                    }
                }

                temp.setLabel(tempLabel);
                temp.setAudio(tempAudio);
                this.nodes[count] = temp;
                count++;
            }
            reader.close();

        } catch (FileNotFoundException e){
            System.out.println("There is no VoiceProfile with that ID");
        }
        System.out.println("Profile loaded succesfully");
    }

    public String getId(){
        return this.id;
    }

    public SegmentNode[] getNodes(){
        return this.nodes;
    }




    // Use this method to save the profile
    // TODO: This whole thing should probably be wrapped inside a single try catch
    // Atleast now we will know where it breaks if it does.
    public String saveProfile() {
        File file = new File(context.getFilesDir(), "text");
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            File gpxfile = new File(file, "sample");
            FileWriter writer = new FileWriter(gpxfile);
            for (int i = 0; i < nodes.length; i++){
                float audio[] = nodes[i].getAudio();
                int label = nodes[i].getLabel();
                // Write the label as the first int on the line
                try {
                    writer.append(id+" adsadfassd");
                } catch (IOException e){
                    System.out.println("Error writing the label: " + e.toString());
                }
                // Write each float of the audio
                for (int j = 0; j < audio.length; j++){
                    try {
                        writer.write(Float.toString(audio[j]) + " ");
                    } catch (IOException e){
                        System.out.println("An error as occured in writing to the file: " + e.toString());
                    }
                }
                // Add a new line to seperate the nodes
                try {
                    writer.append("\n");
                } catch (IOException e){
                    System.out.println("Error on newLine: " + e.toString());
                }
            }
            writer.append(id + " ");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.id;
    }
}
