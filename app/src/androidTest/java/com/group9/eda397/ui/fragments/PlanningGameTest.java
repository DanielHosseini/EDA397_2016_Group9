package com.group9.eda397.ui.fragments;

import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.FragmentTransaction;

import com.group9.eda397.MainActivity;
import com.group9.eda397.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class PlanningGameTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    private void replaceFragment() {
        FragmentTransaction ft = mainActivityRule.getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, PlanningGameFragment.newInstance());
        ft.commit();
    }

    @Before
    public void setUp() throws IOException {
        replaceFragment();
    }

    @Test
    public void allowUserToChooseDeck() {
        onView(withId(R.id.button_fibonacci)).check(matches(isDisplayed()));
        onView(withId(R.id.button_power_of_two)).check(matches(isDisplayed()));
        onView(withId(R.id.button_standard)).check(matches(isDisplayed()));
    }

    @Test
    public void rightFibonacciCardIsPresented() {
        onView(withId(R.id.button_fibonacci)).perform(click());
        onView(withText("0")).check(matches(isDisplayed()));
        onView(withText(R.string.label_select)).perform(click());
        onView(withText("0")).check(matches(isDisplayed())); // the number 1 should be shown as card

        pressBack();

        onView(withId(R.id.button_fibonacci)).perform(click());
        onView(withText("0")).check(matches(isDisplayed()));
        onView(withText(R.string.label_select)).perform(click());
        onView(withText("1")).check(doesNotExist()); // the number 1 shouldn't be shown as card!
    }

}