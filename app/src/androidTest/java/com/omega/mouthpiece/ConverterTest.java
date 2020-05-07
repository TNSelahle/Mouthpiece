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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class ConverterTest {
    @Rule
    public ActivityTestRule displaysViewConv = new ActivityTestRule(ConverterUnit.class, true, false);

    @Test
    public void fomantButtonRead() throws Exception{

        displaysViewConv.launchActivity(new Intent());
        onView(withText(R.string.formant_based)).check(matches(isDisplayed()));
        onView(withId(R.id.btnFormant)).perform(click()).check(matches(isDisplayed()));
    }
    @Test
    public void volumeButtonRead() throws Exception{

        displaysViewConv.launchActivity(new Intent());
        onView(withText(R.string.volume_based)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_record)).perform(click()).check(matches(isDisplayed()));
    }
}