package com.omega.mouthpiece;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)

public class imageConfirmationTest {

    @Rule
    public ActivityTestRule displaysViewImageConfirm = new ActivityTestRule(imageConfirmationFragment.class, true, false);

    @Test
    public void confirmButtonTest() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.accept)).check(matches(isDisplayed()));
        //onView(withId(R.id.confrimButtonAccept)).perform(click()).check(matches(isDisplayed()));
    }
    @Test
    public void cancelButtonTest() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText("Cancel")).check(matches(isDisplayed()));
        //onView(withId(R.id.confirmButtonCancel)).perform(click()).check(matches(isDisplayed()));
    }
    @Test
    public void confirmationTextTest() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.confirmation)).check(matches(isDisplayed()));
    }

    //Images testing
    @Test
    public void image1Test() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.mouthShape1)).check(matches(isDisplayed()));
    }
    @Test
    public void image2Test() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.mouthShape2)).check(matches(isDisplayed()));
    }
    @Test
    public void image3Test() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.mouthShape3)).check(matches(isDisplayed()));
    }
    @Test
    public void image4Test() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.mouthShape4)).check(matches(isDisplayed()));
    }
    @Test
    public void image5Test() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.mouthShape5)).check(matches(isDisplayed()));
    }
    @Test
    public void image6Test() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.mouthShape6)).check(matches(isDisplayed()));
    }
    @Test
    public void image7Test() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.mouthShape7)).check(matches(isDisplayed()));
    }
    @Test
    public void image8Test() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.mouthShape8)).check(matches(isDisplayed()));
    }
    @Test
    public void image9Test() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.mouthShape9)).check(matches(isDisplayed()));
    }
    @Test
    public void image10Test() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.mouthShape10)).check(matches(isDisplayed()));
    }
    @Test
    public void image11Test() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.mouthShape11)).check(matches(isDisplayed()));
    }
    @Test
    public void image12Test() throws Exception{
        displaysViewImageConfirm.launchActivity(new Intent());
        onView(withText(R.string.mouthShape12)).check(matches(isDisplayed()));
    }

}