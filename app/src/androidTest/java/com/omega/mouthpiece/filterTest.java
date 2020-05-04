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
public class filterTest {
    @Rule
    public ActivityTestRule displaysViewFilter = new ActivityTestRule(filter.class, true, false);

    @Test
    public void testSort() throws Exception{
        displaysViewFilter.launchActivity(new Intent());
        onView(withText(R.string.fragmentSort)).check(matches(isDisplayed()));
    }
    @Test
    public void testRatings() throws Exception{
        displaysViewFilter.launchActivity(new Intent());
        onView(withText(R.string.Ratings)).check(matches(isDisplayed()));
    }
    @Test
    public void testApply() throws Exception{
        displaysViewFilter.launchActivity(new Intent());
        onView(withText(R.string.fragmentButtonApply)).check(matches(isDisplayed()));
        //onView(withId(R.id.button2)).perform(click()).check(matches(isDisplayed()));
    }
    @Test
    public void testCancel() throws Exception{
        displaysViewFilter.launchActivity(new Intent());
        onView(withText(R.string.fragmentButtonCancel)).check(matches(isDisplayed()));
        //onView(withId(R.id.button3)).perform(click()).check(matches(isDisplayed()));
    }
}