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
public class mouthCreation_ImageUploadTest {
    @Rule
    public ActivityTestRule displaysViewMainAct = new ActivityTestRule(mouthCreation_ImageUpload.class, true, true);
    //this checks if everything is displayed from the loading page
    @Test
    public void ViewTest() throws Exception {
        displaysViewMainAct.launchActivity(new Intent());
        onView(withText("Choose Image")).check(matches(isDisplayed()));
        onView(withText("Next")).check(matches(isDisplayed()));
        onView(withText("Cancel")).check(matches(isDisplayed()));
        }

}