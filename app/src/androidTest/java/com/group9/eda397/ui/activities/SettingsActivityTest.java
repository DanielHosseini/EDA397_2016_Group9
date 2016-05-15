package com.group9.eda397.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.group9.eda397.R;
import com.group9.eda397.ui.UIAssertions;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by doniramadhan on 15/05/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SettingsActivityTest {
    private static final String EXAMPLE_OWNER1 = "donirn";
    private static final String EXAMPLE_REPOSITORY1 = "travis-broken-example";
    private static final String EXAMPLE_OWNER2 = "DanielHosseini";
    private static final String EXAMPLE_REPOSITORY2 = "EDA397_2016_Group9";

    @Rule
    public ActivityTestRule<SettingsActivity> activityRule =
            new ActivityTestRule<>(SettingsActivity.class, true, false);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConfiguration(){
        setupConfiguration(EXAMPLE_OWNER1, EXAMPLE_REPOSITORY1);
        setupConfiguration(EXAMPLE_OWNER2, EXAMPLE_REPOSITORY2);
    }

    private void setupConfiguration(String owner, String repository){
        SettingsActivity activity = startActivity();
        onView(withId(R.id.et_username)).check(UIAssertions.isVisible())
                .perform(clearText(),typeText(owner)).check(matches(withText(owner)));
        closeSoftKeyboard();
        onView(withId(R.id.et_repository)).check(UIAssertions.isVisible())
                .perform(clearText(),typeText(repository)).check(matches(withText(repository)));
        closeSoftKeyboard();
        onView(withId(R.id.action_save)).check(UIAssertions.isVisible())
                .perform(click());

        SharedPreferences sharedPreferences = activity.getSharedPreferences(SettingsActivity.SHARED_PREF_NAME_DEFAULT, Context.MODE_PRIVATE);
        String sharedOwner = sharedPreferences.getString(SettingsActivity.SHARED_PREF_KEY_USERNAME, SettingsActivity.DEFAULT_USERNAME);
        String sharedRepository = sharedPreferences.getString(SettingsActivity.SHARED_PREF_KEY_REPOSITORY, SettingsActivity.DEFAULT_REPOSITORY);

        assertEquals(sharedOwner, owner);
        assertEquals(sharedRepository, repository);
    }

    private SettingsActivity startActivity() {
        final Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final Intent intent = new Intent(targetContext, SettingsActivity.class);
        return activityRule.launchActivity(intent);
    }
}