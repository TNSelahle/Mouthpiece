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
public class MainActivityTest {
    @Rule
    public ActivityTestRule displaysViewMainAct = new ActivityTestRule(MainActivity.class, true, true);
    //this checks if everything is displayed from the loading page
    @Test
    public void ViewTest() throws Exception {
        displaysViewMainAct.launchActivity(new Intent());
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
        onView(withText(R.string.motto)).check(matches(isDisplayed()));
        onView(withText(R.string.prompt_email)).check(matches(isDisplayed()));
        onView(withText(R.string.prompt_password)).check(matches(isDisplayed()));
        onView(withText(R.string.loginB)).check(matches(isDisplayed()));
        onView(withText(R.string.regViewer)).check(matches(isDisplayed()));
    }

}