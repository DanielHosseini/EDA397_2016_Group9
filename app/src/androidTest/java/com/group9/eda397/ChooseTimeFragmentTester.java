package com.group9.eda397;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.group9.eda397.ui.ChooseTimeFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;



/**
 * Created by Isabelle on 2016-04-27.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChooseTimeFragmentTester{
    ChooseTimeFragment fragment;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setup() {
        fragment = ChooseTimeFragment.newInstance("fragment");
    }

    @Test
    public void testStartingTheTimer(){
        String time = "10";
        onView(withId(R.layout.countdown_timer)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.editText)).perform(click(),typeText(time), closeSoftKeyboard()).check(matches(isDisplayed()));
        onView(withId(R.id.startButton)).perform(click());

    }
}