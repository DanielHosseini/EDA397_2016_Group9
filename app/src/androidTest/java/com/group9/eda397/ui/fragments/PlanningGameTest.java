package com.group9.eda397.ui.fragments;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;

import com.group9.eda397.MainActivity;
import com.group9.eda397.R;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class PlanningGameTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void allowUserToChooseDeck() {
        onView(allOf(withId(R.id.button_fibonacci),
                withId(R.id.button_power_of_two),
                withId(R.id.button_standard))
        ).check(ViewAssertions.doesNotExist());;

        onView(withId(R.id.drawer_layout)).perform(open());
        String FragmentName = mainActivityRule.getActivity().getResources().getString(R.string.title_planning_game);
        onView(withText(Matchers.containsString(FragmentName))).perform(click());

        onView(withId(R.id.button_fibonacci)).check(matches(isDisplayed()));
        onView(withId(R.id.button_power_of_two)).check(matches(isDisplayed()));
        onView(withId(R.id.button_standard)).check(matches(isDisplayed()));
    }
}