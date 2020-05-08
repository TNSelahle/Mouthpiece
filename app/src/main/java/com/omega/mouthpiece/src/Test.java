import NN.*;
import Converter.*;
import org.bytedeco.tesseract.UNICHAR;

import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

public class Test {

    private static Handler handler = new Handler();
    //*
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) throws Exception {

        int choice = 0;
        while(choice!=4){//running testing environment
            //test options
            System.out.println("\nOMEGA NEURAL NETWORK ENVIRONMENT:");
            System.out.println("1. Test Neural Network initialisation");
            System.out.println("2. Do Converter test");
            System.out.println("3. Do User Management Test");
            System.out.println("4. Quit\n");
            //enter choice
            System.out.print("Enter choice: ");
            Scanner s = new Scanner(System.in);
            choice = s.nextInt();
            System.out.println("");
            //run test option
            switch(choice) {
                case 1:
                    NeuralNetworkInitialiseTest();
                    break;
                case 2:
                    ConverterTest();
                    break;
                case 3:
                    UserManagementTest();
                    break;
                case 4:
                    System.out.println("Quitting...");
                    break;
                default :
                    System.out.println("Invalid choice. Try again.\n");
            }
        }//*/
    }

    ///////////////////////////NN Functionality/////////////////////////////////
    /////////////handler//classifier//VoiceProfile//SegmentNode//////////////////
    public static void NeuralNetworkInitialiseTest(){
        int choice = 0;
        while(choice!=3){//1. Test NN initialisation environment
            //intitialisation options
            System.out.println("___1. Test NN initialisation___:");
            System.out.println("1. Create Handler object");
            System.out.println("2. Create SegmentNode object");
            System.out.println("3. back\n");
            //enter choice
            System.out.print("Enter choice: ");
            Scanner s = new Scanner(System.in);
            choice = s.nextInt();
            System.out.println("");
            //run chosen option
            switch(choice) {
                case 1:
                    System.out.println("creating Handler...");
                    Handler h1 = new Handler();
                    System.out.println("");
                    break;
                case 2:
                    //create random amplitude audio array for SegmentNode creation
                    float[] audio = new float[500];
                    String[] strDouble = null;
                    float f = 1.02f;
                    for (int i = 0; i < audio.length; i++)
                    {
                        audio[i] = f;
                        f++;
                        //strDouble[i] = String.format("%.2f", audio[i]);
                    }
                    System.out.println("creating SegmentNode...");
                    SegmentNode s1 = new SegmentNode(audio);
                    //show segment array audio data?
                    System.out.print("Display SegmentNode audio amplitude array (0-No, 1-Yes): ");
                    //enter choice
                    choice = s.nextInt();
                    if (choice==1) s1.printAudio();
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("Going back...\n");
                    break;
                default :
                    System.out.println("Invalid choice. Try again.\ns");
            }
        }
    }

    //////////////////////////CONVERTER//////////////////////////////////
    public static void ConverterTest(){
        int choice = 0;
        while(choice!=2){//2. Converter testing environment
            //test options
            System.out.println("___2. Converter Test___:");
            System.out.println("1. Determine Phonetics");
            System.out.println("2. back\n");
            //enter choice
            System.out.print("Enter choice: ");
            Scanner s = new Scanner(System.in);
            choice = s.nextInt();
            System.out.println("");
            //run test option
            switch(choice) {
                case 1:
                    startRecording();
                    break;
                case 2:
                    System.out.println("Going back...\n");
                    break;
                default :
                    System.out.println("Invalid choice. Try again.\n");
            }
        }
    }

    public static void startRecording(){
        //test options
        System.out.println("___2. Converter Test___:");
        System.out.println("1. Determine Phonetics");
        System.out.println("2. back\n");
        //enter choice
//        System.out.print("Enter duration of recording(seconds): ");
//        Scanner sn = new Scanner(System.in);int duration = sn.nextInt();
        System.out.println("Determine Phonetics");

        System.out.print("Enter the filename of the amplitudes:" );
        Scanner s = new Scanner(System.in);
        System.out.println();
        String filename = s.nextLine();
        UnitTester unitTester = new UnitTester();
        unitTester.runSimulation(filename);


        /*
        Handler h1 = new Handler();
        //Open Target line
        TargetDataLine line;
        //int bufferSize = ;
        AudioFormat af = new AudioFormat(48000, 16, 1, true, false); //AudioFormat(float (samples per second,usually 14kHz-48kHz) sampleRate,int (8- or 16-bit) sampleSizeInBits,int channels,boolean signed,boolean bigEndian)
        DataLine.Info info = new DataLine.Info(TargetDataLine.class,af);
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("No targetLine");
        }
        // Obtain and open the line.
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            byte[] buf = new byte[(int)af.getSampleRate() * af.getFrameSize()];
            long end = System.currentTimeMillis() + 1000 * duration;
            int len;
            line.open(af);
            line.start();
            while ((System.currentTimeMillis() < end ) && ((len = line.read(buf, 0, buf.length)) != -1)){
                int byteNum = line.read(buf, 0, 480);//returns num of bytes read
                int available = line.available();//numb of bytes available
                //convert byte[] data amplitudes float[] array
                float[] audio = new float[120];
                for (int i = 0; i < audio.length; i++)
                {
                    float value = buf[i*2] & buf[i*2+1];//// here buffer values must be converted properly
                    audio[i] = value;
                }
                SegmentNode sn = new SegmentNode(audio);//create new SegmentNode
                //sn.printAudio();
                int phonetic = h1.getPhonetic(sn);
                System.out.print("..." + phonetic);//getPhonetic for node //display results for node
            }
            System.out.println("\nstopped recording...");
            line.stop();
            line.close();

        } catch (LineUnavailableException ex) {
            System.out.println("Line unavailable");
        }
        System.out.println("");

         */
    }

    //////////////////////////USER MANAGEMENT//////////////////////////////////
    public static void UserManagementTest(){
        //test options
        int choice = 0;
        while(choice!=5){//2. User Management testing environment
            System.out.println("___3. User Management Test___");
            System.out.println("1. Calibrate voice profile");
            System.out.println("2. Retrieve VoiceProfile textfile");
            System.out.println("3. Restore VoiceProfile textfile");
            System.out.println("4. Test Neural Network accuracy with VoiceProfile");
            System.out.println("5. back\n");
            //enter choice
            System.out.print("Enter choice: ");
            Scanner s = new Scanner(System.in);
            choice = s.nextInt();
            //run test option
            switch(choice) {
                case 1:
                    CalibrateVoiceProfile();
                    break;
                case 2:
                    RetrieveVoiceProfile();
                    break;
                case 3:
                    RestoreVoiceProfile();
                    break;
                case 4:
                    TestNNwithVP();
                    break;
                case 5:
                    System.out.println("Going back...\n");
                    break;
                default :
                    System.out.println("Invalid choice. Try again.\n");
            }
        }
    }

    public static void CalibrateVoiceProfile(){
        //send 12 segment nodes with audio in each category to handler to train the VoiceProfile.
        System.out.println("___Calibrate Voice Profile___");
        SegmentNode[] segments = new SegmentNode[12];



        //TO DO: populate 12 segment's audios with real audio data and their corresponding labels using user interface and making wav of the user for each category
        ///Britney
        //read data.txt for each category
        float[] audio = new float[500];

        int numLines = 0;

        for (int k=0; k<segments.length; k++){
            File file = new File(k+".txt");
            try{
                try (Scanner sc = new Scanner(file)) {

                    while((sc.hasNextLine()) && (numLines<=500)){

                        audio[k] = Float.parseFloat(sc.nextLine());

                        //System.out.println(audio[k]);

                        numLines++;
                    }
                    segments[k] = new SegmentNode(audio);
                    segments[k].setLabel(k);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        //user gives id for calibration
        System.out.print("Enter calibration id: ");
        Scanner s = new Scanner(System.in);
        String id = s.nextLine();
        handler.trainVoiceProfile(id,segments);
        System.out.println("Calibrated...\n");

        //listen to segments?
        int choice = 1;
        while(choice==1){
            System.out.print("Listen to calibration data? (0-No, 1-yes): ");
            choice = s.nextInt();

            if (choice==1){
                System.out.print("Enter segment number to listen(1-12): ");
                int index = s.nextInt();
                System.out.println("");
//                index--;
                switch(index) {
                    case 1:
                        System.out.println("listening to 1 - 'AAAAAAEEEEEIIIIIII'");
                        play(index);
                        break;
                    case 2:
                        System.out.println("listening to 2 - 'OOOOOOOOOooooo'");
                        play(index);
                        break;
                    case 3:
                        System.out.println("listening to 3 - 'LLLLLLLLLLL'");
                        play(index);
                        break;
                    case 4:
                        System.out.println("listening to 4 - 'CCDDDDGGGKKKNNNSSTTXXYYZZZ'");
                        play(index);
                        break;
                    case 5:
                        System.out.println("listening to 5 - 'FFFFFFFVVVVVVV'");
                        play(index);
                        break;
                    case 6:
                        System.out.println("listening to 6 - 'QQQQQQQQWWWWWW'");
                        play(index);
                        break;
                    case 7:
                        System.out.println("listening to 7 - 'BBBBBBMMMMMPPPP'");
                        play(index);
                        break;
                    case 8:
                        System.out.println("listening to 8 - 'UUUUUUUUuuuuuu'");
                        play(index);
                        break;
                    case 9:
                        System.out.println("listening to 9 - 'EeEeEeEeEeEeEe'");
                        play(index);
                        break;
                    case 10:
                        System.out.println("listening to 10 - 'RRRRRRRRRRRRR'");
                        play(index);
                        break;
                    case 11:
                        System.out.println("listening to 11 - 'ThThThThTh'");
                        play(index);
                        break;
                    case 12:
                        System.out.println("listening to 12 - 'ChChChChJJJJJJJJShShShShSh'");
                        play(index);
                        break;
                    default :
                        System.out.println("Invalid choice. Try again.\n");
                }//switch
            }//if listen
            System.out.println("");
        }//while environment
    }

    public static void play(int index){
        index--;
        File Wave = new File(index+".wav");

        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Wave));
            clip.start();
//            Thread.sleep(clip.getMicrosecondLength()/1000);
            Thread.sleep(5000);
        }catch (IOException e){
            e.printStackTrace();
        }catch (UnsupportedAudioFileException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void RetrieveVoiceProfile(){
        System.out.println("__Retrieve Voice Profile___");
        String id = handler.getUserVoiceProfile();//get id
        if (id!=null){//check if VP exist
            System.out.println("id: "+id);//current VP id (version of VP and name of VP file)
            //show VP file?
            System.out.print("Display textfile (0-No, 1-Yes): ");
            //enter choice
            Scanner s = new Scanner(System.in);
            int choice = s.nextInt();
            if (choice==1) {
                System.out.println("Reading file...\n");
                //TO DO: display file using id as the texfiles name
                File file = new File(id + ".txt");
                try{
                    try (Scanner sc = new Scanner(file)) {
                        while(sc.hasNextLine()){
                            System.out.println(sc.nextLine());
                        }
                    }
                }
                catch(Exception e){
                    System.out.println("Exception Occurred: File not Found ");
                }
            }
            System.out.println("");
        }else{
            System.out.println("No voice profile\n");
        }
    }

    public static void RestoreVoiceProfile(){//give the name of an existing textfile in which VP will change states to
        System.out.println("___Restore Voice Profile___");
        //getting new id/name of file from user
        System.out.print("Enter name of textfile for the VP id: ");
        Scanner s = new Scanner(System.in);
        String newId = s.nextLine();
        String id = handler.getUserVoiceProfile();//test if VP exists
        if (id!=null){
            System.out.println("Current VP id: "+id);//id before change
            handler.replaceUserVoiceProfile(newId);
            System.out.println("restored...");
            System.out.println("current VP id: "+ handler.getUserVoiceProfile());//id after change

        }else{
            System.out.println("No voice profile\n");
        }
    }

    public static void TestNNwithVP(){//tests the accuracy of the NN to the specific user
        System.out.println("___Testing Voice Profile___");
        String id = handler.getUserVoiceProfile();//test if VP exists
        if (id!=null){
            System.out.println("Using VP version(id): "+ id);//VP id (version)
            //TO DO: Test the accuracy of the NN to the users saved Voice Profile
            //ensure it looks informative and structured
            ///RANI
            //use the 12 segmentNodes inside voiceprofile to send them to the the classifier one by one to
            SegmentNode[] sg = handler.getVoiceProfileSegments();
            int i = 0;
            int totalStatus = 0;
            System.out.println("-------------------------------------------------------");
            System.out.println("| Node # | Label | Phenom | margin of error | Status  |");
            System.out.println("-------------------------------------------------------");
            while(i < sg.length)
            {
                //compare each result to the label of the segmentNode

                if(sg[i].getLabel() == handler.getPhonetic(sg[i]))
                {	//success

                    int label = sg[i].getLabel();
                    int phenom = handler.getPhonetic(sg[i]);
                    int total = label - phenom;
                    int fraction = 12 * total;
                    fraction /= 12;
                    fraction *= 100;
                    if(i < 10)
                    {	//display the results and the accuracy out of 12 being right or wrong
                        if(fraction == 100)
                            System.out.println("|    " + i + "   |   " + label + "   |    " + phenom + "   |      " + fraction + "%       |" +ANSI_GREEN+ " success "+ANSI_RESET+"|");
                        if(fraction < 100 && fraction >= 10)
                            System.out.println("|    " + i + "   |   " + label + "   |    " + phenom + "   |       " + fraction + "%       |" +ANSI_GREEN+ " success "+ANSI_RESET+"|");
                        if(fraction < 10)
                            System.out.println("|    " + i + "   |   " + label + "   |    " + phenom + "   |        " + fraction + "%       |" +ANSI_GREEN+ " success "+ANSI_RESET+"|");
                        totalStatus++;
                    } else if(i >= 10)
                    {
                        if(fraction == 100)
                            System.out.println("|   " + i + "   |   " + label + "   |    " + phenom + "   |      " + fraction + "%       |" +ANSI_GREEN+ " success "+ANSI_RESET+"|");
                        if(fraction < 100 && fraction >= 10)
                            System.out.println("|   " + i + "   |   " + label + "   |    " + phenom + "   |       " + fraction + "%       |" +ANSI_GREEN+ " success "+ANSI_RESET+"|");
                        if(fraction < 10)
                            System.out.println("|   " + i + "   |   " + label + "   |    " + phenom + "   |        " + fraction + "%       |" +ANSI_GREEN+ " success "+ANSI_RESET+"|");
                        totalStatus++;
                    }
                    i++;
                }
                else
                {	//failure
                    int label = sg[i].getLabel();
                    int phenom = handler.getPhonetic(sg[i]);
                    int difference = handler.getPhonetic(sg[i]) - sg[i].getLabel();
                    if(difference < 0)
                    {
                        difference *= -1;
                    }
                    int ratio = difference - 1;
                    int fraction = difference / 12;
                    fraction *= 100;
                    if(i < 10)
                    {
                        if(fraction == 100)
                            System.out.println("|    " + i + "   |   " + label + "   |    " + phenom + "   |      " + fraction + "%       |" +ANSI_RED+ " failure "+ANSI_RED+ANSI_RESET+"|");
                        if(fraction < 100 && fraction >= 10)
                            System.out.println("|    " + i + "   |   " + label + "   |    " + phenom + "   |       " + fraction + "%       |" +ANSI_RED+ " failure "+ANSI_RED+ANSI_RESET+"|");
                        if(fraction < 10)
                            System.out.println("|    " + i + "   |   " + label + "   |    " + phenom + "   |        " + fraction + "%       |" +ANSI_RED+ " failure "+ANSI_RED+ANSI_RESET+"|");
                    } else if(i >= 10)
                    {
                        if(fraction == 100)
                            System.out.println("|   " + i + "   |   " + label + "   |    " + phenom + "   |      " + fraction + "%       |" +ANSI_RED+ " failure "+ANSI_RED+ANSI_RESET+"|");
                        if(fraction < 100 && fraction >= 10)
                            System.out.println("|   " + i + "   |   " + label + "   |    " + phenom + "   |       " + fraction + "%       |" +ANSI_RED+ " failure "+ANSI_RED+ANSI_RESET+"|");
                        if(fraction < 10)
                            System.out.println("|   " + i + "   |   " + label + "   |    " + phenom + "   |        " + fraction + "%       |" +ANSI_RED+ " failure "+ANSI_RED+ANSI_RESET+"|");
                    }
                    i++;
                }


            }
            int accuracy = totalStatus/12;
            accuracy *= 100;
            System.out.println(ANSI_RESET+"-------------------------------------------------------"+ANSI_RESET);
            if(accuracy < 50)
                System.out.println(ANSI_RED+"Total Success Rate: " + totalStatus + "/12 (" + accuracy + "%)"+ANSI_RED+ANSI_RESET);
            if(accuracy >= 50)
                System.out.println(ANSI_GREEN+"Total Success Rate: " + totalStatus + "/12 (" + accuracy + "%)"+ANSI_GREEN+ANSI_RESET);
            System.out.println(ANSI_RESET+"-------------------------------------------------------"+ANSI_RESET);
            System.out.println(ANSI_RESET + "" +  ANSI_RESET);

        }else{
            System.out.println("No voice profile\n");
        }
    }
}
