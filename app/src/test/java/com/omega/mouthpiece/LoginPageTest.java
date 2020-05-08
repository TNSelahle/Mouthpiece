package com.omega.mouthpiece;

import android.content.Context;
import android.content.Intent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

public class LoginPageTest {

    @Rule
    public ActivityTestRule displaysViewLoading = new ActivityTestRule(LoadingPageUnit.class, true, true);
    //this checks if everything is displayed from the loading page
    @Test
    public void ViewTest() throws Exception {
        displaysViewLoading.launchActivity(new Intent());
        onView(withText(R.string.From)).check(matches(isDisplayed()));
        onView(withText(R.string.OMEGA)).check(matches(isDisplayed()));
        onView(withText(R.string.University_of_Pretoria)).check(matches(isDisplayed()));
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
        onView(withText(R.string.motto)).check(matches(isDisplayed()));
    }
        @Test(expected = NullPointerException.class)
        public void nullStringTest() {
            String str = null;
            assertTrue(str.isEmpty());
        }

    }



}