package com.omega.mouthpiece;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class _ImageUploadFragmentTest {
    @Rule
    public ActivityTestRule displaysViewMainAct = new ActivityTestRule(ImageUploadFragmentUnitClass.class, true, true);
    //this checks if everything is displayed from the loading page
    @Test
    public void ViewTest() throws Exception {
        displaysViewMainAct.launchActivity(new Intent());
        onView(withText("Choose Image")).check(matches(isDisplayed()));
        onView(withText("Next")).check(matches(isDisplayed()));
        onView(withText("Cancel")).check(matches(isDisplayed()));
        }

}