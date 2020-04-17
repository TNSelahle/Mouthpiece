package com.omega.mouthpiece;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class TrainingPageTest {
    @Rule
    public ActivityTestRule displaysView = new ActivityTestRule(TrainingPage.class, true, false);

    //Checks if the heading is displayed
    @Test
    public void rendersView() throws Exception{

        displaysView.launchActivity(new Intent());
        onView(withText(R.string.vTraining)).check(matches(isDisplayed()));
    }
    //checks if when the button is pressed, the phonetic pangrams shows up
    @Test
    public void checkEnableVoiceTrainingButton() throws Exception {
        displaysView.launchActivity(new Intent());
        onView(withId(R.id.enableTrainingButton)).perform(click()).check(matches(isDisplayed()));
        onView(withText(R.string.Phonetic_Pangram_Read)).check(matches(isDisplayed()));
    }
    //checks if the stop voice recording button works
    @Test
    public void stopVoiceRecordingButton() throws Exception {
        displaysView.launchActivity(new Intent());
        onView(withId(R.id.stopVoiceRecording)).perform(click()).check(matches(isDisplayed()));
    }
    //checks if the test audio recording button works
    @Test
    public void testAudioRecording() throws Exception {
        displaysView.launchActivity(new Intent());
        onView(withId(R.id.testAudioRec)).perform(click()).check(matches(isDisplayed()));
    }
}