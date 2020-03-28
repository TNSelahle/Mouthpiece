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
    /*
    private static byte[] BitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, bos);
        byte[] result = bos.toByteArray();
        return result;
    }

    private static Bitmap getBitmapOfDrawable(Drawable drawable) {
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bitmap = bd.getBitmap();
        return bitmap;
    }

    private boolean isImageEqual(ImageView actualImageView, int expectedDrawable) {

        Drawable expected = getActivity().getResources().getDrawable( expectedDrawable);
        Drawable actual = actualImageView.getDrawable();

        if (expected != null && actual != null)
        {
            Bitmap expectedBitmap = getBitmapOfDrawable(expected);
            Bitmap actualBitmap = getBitmapOfDrawable(actual);

            byte[] array1 = BitmapToByteArray(expectedBitmap);
            byte[] array2 = BitmapToByteArray(actualBitmap);
            if (java.util.Arrays.equals(array1, array2)) {
                return true;
            }
        }
        return false;
    }

    public void testImages()
    {
        ImageView mouthImage = (ImageView) getActivity().findViewById(R.id.img_mouth);
        int resIdImage = R.drawable.aei_animation;

        if (!isImagesEqual(mouthImage, resIdImage))
        {
            System.out.println("Incorrect mouth animation");
        }else {
            System.out.println("Correct mouth animation");
        }
    }


     */
}
