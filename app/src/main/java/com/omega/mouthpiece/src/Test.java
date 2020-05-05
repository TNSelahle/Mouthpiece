import NN.*;
import Converter.*;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

public class Test {
    public static void main(String[] args) throws Exception {

		int choice = 0;
		while(choice!=4){//running testing environment
			//test options
			System.out.println("OMEGA NEURAL NETWORK ENVIRONMENT:");
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
		}
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
					//create random amplitudeVStime array for SegmentNode creation
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
					System.out.print("Display SegmentNode audio ampVStime array (0-No, 1-Yes): ");
					//enter choice
					choice = s.nextInt();
					if (choice==1) s1.printAudio();
					System.out.println("");
					break;
			   case 3:
					System.out.println("going back...\n");
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
			System.out.println("1. Start Recording");
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
					System.out.println("going back...\n");
					break;
			   default : 
					System.out.println("Invalid choice. Try again.\n");
			}
		}
	}
	
	public static void startRecording(){
		//test options
		System.out.println("___2. Converter Test___:");
		System.out.println("1. Start Recording");
		System.out.println("2. back\n");
		//enter choice
		System.out.print("Enter duration of recording(seconds): ");
		Scanner s = new Scanner(System.in);int duration = s.nextInt();
		System.out.println("");
		System.out.println("now recording...");
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
					System.out.print("..." + h1.getPhonetic(sn));//getPhonetic for node //display results for node
				}
				System.out.println("\nstopped recording...");
				line.stop();
				line.close();
			
			} catch (LineUnavailableException ex) {
				System.out.println("Line unavailable");
			}
		System.out.println("");
	}

	public static void CalibrateVoiceProfile(){
		//send segment nodes to handler to train the VoiceProfile
	}
	
	//////////////////////////USER MANAGEMENT//////////////////////////////////
	public static void UserManagementTest(){
		System.out.println("___User Management Test___");	
	   
		System.out.println("");
	}
}


