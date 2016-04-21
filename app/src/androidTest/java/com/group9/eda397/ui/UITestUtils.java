package com.group9.eda397.ui;

import com.group9.eda397.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Test Utility class with common functions used in multiple UI Tests
 * <p/>
 * For example an action opening the navigation drawer.
 *
 * @author palmithor
 * @since 17/04/16.
 */
public class UITestUtils {

    private UITestUtils() {
    }

    /**
     * Helper method to open a specific fragment from the navigation drawer.
     * Uses a string to find the drawer item
     *
     * @param stringResourceId the string resource id of the item
     */
    public static void openDrawerFragment(final int stringResourceId) {
        onView(withId(R.id.drawer_layout)).perform(open());
        onView(withText(stringResourceId)).perform(click());
    }
}
