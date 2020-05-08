import Converter.SegmentNode;
import NN.Handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *  Reads a .txt file of amplitudes. Assumes the first line of text file is the sample rate.
 *  Reads the list of amplitudes into an array of an array of 500 float values that is used to
 *  call getPhonetic() in the Handler class on line XX. The input parameter for the constructor
 *  is the filename of the text file with the amplitude values.
 */
public class UnitTester {

    private String filename;

    private int dotcount = 0;

    private long timems = 0;
    private StringBuilder tmp = new StringBuilder(), breakstr = new StringBuilder();
    private String message;

    private Timer loadTimer;
    private TimerTask showReadFileTask;

    private Handler handler;

    private void clearAndWrite(String msg) {
        if (tmp.length() > 0) {
            breakstr.delete(0, breakstr.length());
            for (int n = 0; n < tmp.length(); n++)
                breakstr.append('\b');
            System.out.print(breakstr);
        }
        tmp.delete(0, tmp.length());
        tmp.append(msg);
        System.out.print(tmp);
    }

    private void createNewTask() {
        showReadFileTask = new TimerTask() {
            @Override
            public void run() {
                if (tmp.length() > 0) {
                    breakstr.delete(0, breakstr.length());
                    for (int n = 0; n < tmp.length(); n++)
                        breakstr.append('\b');
                    System.out.print(breakstr);
                }
                tmp.delete(0, tmp.length());
                tmp.append(message);
                if (dotcount == 4)
                    dotcount = 1;
                for (int d = 0; d < dotcount; d++)
                    tmp.append(".");
                dotcount++;
                System.out.print(tmp);
            }
        };
    }

    private String phoneticToString(int phonetic) {
        switch (phonetic) {
            case 0:
                return "æ, ei = a, e, i";
            case 1:
                return "l = l, ll";
            case 2:
                return "o = o, oo, u,ou";
            case 3:
                return "c, d, g, k, n, s, t, x, y, z";
            case 4:
                return "f = f, ff, ph, gh, lf, ft";
            case 5:
                return "k, w = qu ,q(u), ck, w, wh";
            case 6:
                return "b, m, p = b, bb, mb, pp";
            case 7:
                return "u: = u, ou";
            case 8:
                return "e = e, ee, ea";
            case 9:
                return "r = r, rr, wr, rh";
            case 10:
                return "t = t, tt, th";
            case 11:
                return "tf = ch, tch, j, sh";
            default:
                return "Indeterminate.";
        }
    }

    public UnitTester() {
        this.dotcount = 1;
        this.tmp = new StringBuilder();
        handler = new Handler();
    }

    public void runSimulation(String file) {
        message = "Reading Audio File";
        this.filename = file;
        this.loadTimer = new Timer();
        this.createNewTask();
        this.loadTimer.scheduleAtFixedRate(this.showReadFileTask, 0, 1000);

        Path path = Paths.get(file);
        long phoneticCount = 0;
        try {
            phoneticCount = Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        phoneticCount--;

        phoneticCount /= 500;

        float[][] amplitudes = new float[(int)phoneticCount+1][500];
        int amps_i = 0, inner_i = 0;
        try {
            Scanner scanner = new Scanner(new File(file));
            scanner.nextFloat();
            while (scanner.hasNextLine()) {
                if (amps_i == 500) {
                    amps_i = 0;
                    inner_i++;
                }
                if (inner_i == phoneticCount)
                    break;
                amplitudes[inner_i][amps_i++] = scanner.nextFloat();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.loadTimer.cancel();

        message = "Determining Phonetic";
        this.loadTimer = new Timer();
        this.createNewTask();
        this.loadTimer.scheduleAtFixedRate(this.showReadFileTask, 0, 800);

        System.out.println();

        int mscounter = 20, p;
        for (int s = 0; s < phoneticCount; s++) {
            message = "Determining Phonetic between " + mscounter + "ms and " + (mscounter+20) + "ms";
            SegmentNode sn = new SegmentNode(amplitudes[s]);
            p = handler.getPhonetic(sn);
            System.out.println("\nPhonetic between " + mscounter + "ms and " + (mscounter+20) + "ms is: " + p + "  " + phoneticToString(p));
            mscounter += 20;
        }

        this.loadTimer.cancel();
    }
}
