package com.omega.mouthpiece;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class TrainingPageTest {
    @Rule
    public ActivityTestRule displaysView = new ActivityTestRule(TrainingPage.class, true, false);

    @Test
    public void rendersView() throws Exception{

        displaysView.launchActivity(new Intent());
        onView(withText(R.string.vTraining)).check(matches(isDisplayed()));
    }
}