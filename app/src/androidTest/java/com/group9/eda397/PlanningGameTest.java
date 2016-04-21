package com.group9.eda397;

import android.app.Application;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


/**
 * Created by ivar on 2016-04-19.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PlanningGameTest extends android.test.ActivityInstrumentationTestCase2 {
    public PlanningGameTest(Class<Application> applicationClass) {
        super(applicationClass);
    }

    @Test
    public void allowUserToChooseDeck(){
        ViewInteraction fragmentElements = onView(allOf(withId(R.id.text_choose_deck),
                withText("Fibonacci"),
                withText("Standard")));
        fragmentElements.check(ViewAssertions.doesNotExist());
        onView((withId(R.id.nav_planning_game))).perform(click());
        fragmentElements.check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void changeToFibonacciDeckTest(){
        String selectionText = "Fibonacci";
        ViewInteraction spinner = onView(withId(R.id.spinner_decks));

        spinner.perform(click());
        onData(allOf(is(instanceOf(String.class)), is(selectionText))).perform(click());
        spinner.check(matches(withSpinnerText(containsString(selectionText))));

        ViewInteraction deckNumbers = onView(allOf(withText("0"),
                withText("1"),
                withText("2"),
                withText("3"),
                withText("5"),
                withText("8"),
                withText("13"),
                withText("21"),
                withText("34"),
                withText("55"),
                withText("89")));

        deckNumbers.check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void changeToStandardDeckTest(){
        String selectionText = "Standard";
        ViewInteraction spinner = onView(withId(R.id.spinner_decks));

        spinner.perform(click());
        onData(allOf(is(instanceOf(String.class)), is(selectionText))).perform(click());
        spinner.check(matches(withSpinnerText(containsString(selectionText))));

        ViewInteraction deckNumbers = onView(allOf(withText("0"),
                withText("½"),
                withText("1"),
                withText("2"),
                withText("3"),
                withText("5"),
                withText("8"),
                withText("13"),
                withText("20"),
                withText("40"),
                withText("100")));

        deckNumbers.check(ViewAssertions.matches(isDisplayed()));
    }
}