package com.group9.eda397;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

/*
import com.group9.eda397.MainActivity;
import com.group9.eda397.R;
import com.group9.eda397.data.TravisServiceFactory;
import com.group9.eda397.ui.UIAssertions;
import com.group9.eda397.ui.UITestUtils;*/

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by ivar on 2016-04-19.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PlanningGameTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void allowUserToChooseDeck(){
        ViewInteraction fragmentElements = onView(allOf(withId(R.id.button_fibonacci),
                withId(R.id.button_power_of_two),
                withId(R.id.button_standard)));
        fragmentElements.check(ViewAssertions.doesNotExist());
        onView((withId(R.id.nav_planning_game))).perform(click());
        fragmentElements.check(ViewAssertions.matches(isDisplayed()));
    }

}