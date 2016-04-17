package com.group9.eda397.ui.fragments;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.group9.eda397.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Espresso unit tests for the Travis Builds Fragment
 *
 * @author palmithor
 * @since 16/04/16.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class TravisBuildsFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.

    }

}