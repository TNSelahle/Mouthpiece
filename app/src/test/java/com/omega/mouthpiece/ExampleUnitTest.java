package com.omega.mouthpiece;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private MainActivity mainActivity;
    @Before
    public void setUp() {
        mainActivity = new MainActivity();
    }

    @After
    public void tearDown() {
        System.out.println("Done with testing");
    }

    @Test
    public void test() {
        testGettingFormants();
        testAmplitude();
    }
    public void testAmplitude() {
        double amplitude = mainActivity.getAmplitude();
        if(amplitude>0)
        {
            System.out.println("recording");
        }
        else
        {
            System.out.println("Not recording");
        }
        // assertEquals("Not recording", 0, amplitude);
    }

    public void testGettingFormants() {
        int formant = mainActivity.getFormant();
        if(formant == 0)
        {
            System.out.println("close mouth");
        }
        else if(formant > 0 && formant < 13)
        {
            System.out.println("other formant");
        }
        else
        {
            System.out.println("error in formant");
        }
    }
}
