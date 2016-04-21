package com.group9.eda397.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.group9.eda397.R;
import com.group9.eda397.data.TravisServiceFactory;
import com.group9.eda397.ui.UIAssertions;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * @author doniramadhan
 * @since 19/04/16
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TravisBuildDetailsActivityTest {
    private static final String EXTRA_BUILD_ID = "travis_build_id_extra";
    private static final String EXTRA_BUILD_NUMBER = "travis_build_number_extra";
    private static final String EXTRA_REPOSITORY = "travis_build_owner_extra";
    private static final String EXTRA_OWNER = "travis_build_repository_extra";

    private static final String EXAMPLE_RESPONSE = "{\"id\":124261736,\"repository_id\":8281689,\"number\":\"32\",\"config\":{\"language\":\"android\",\"android\":{\"components\":[\"platform-tools\",\"tools\",\"build-tools-23.0.2\",\"android-23\",\"extra-google-google_play_services\",\"extra-android-m2repository\"]},\"script\":[\"./gradlew build app:test\"],\".result\":\"configured\",\"group\":\"stable\",\"dist\":\"precise\"},\"state\":\"finished\",\"result\":0,\"status\":0,\"started_at\":\"2016-04-19T18:04:11Z\",\"finished_at\":\"2016-04-19T18:09:02Z\",\"duration\":291,\"commit\":\"bab1caea1820237b76f6b342a18ec7cc1b067cab\",\"branch\":\"feature/TravisCI-Integration\",\"message\":\"add errorView\",\"committed_at\":\"2016-04-19T18:03:42Z\",\"author_name\":\"Doni Ramadhan\",\"author_email\":\"doniramadhan@gmail.com\",\"committer_name\":\"Doni Ramadhan\",\"committer_email\":\"doniramadhan@gmail.com\",\"compare_url\":\"https://github.com/DanielHosseini/EDA397_2016_Group9/compare/cb4235ba65b1...bab1caea1820\",\"event_type\":\"push\",\"matrix\":[{\"id\":124261737,\"repository_id\":8281689,\"number\":\"32.1\",\"config\":{\"language\":\"android\",\"android\":{\"components\":[\"platform-tools\",\"tools\",\"build-tools-23.0.2\",\"android-23\",\"extra-google-google_play_services\",\"extra-android-m2repository\"]},\"script\":[\"./gradlew build app:test\"],\".result\":\"configured\",\"group\":\"stable\",\"dist\":\"precise\",\"os\":\"linux\"},\"result\":0,\"started_at\":\"2016-04-19T18:04:11Z\",\"finished_at\":\"2016-04-19T18:09:02Z\",\"allow_failure\":false}]}";
    private static final String EXAMPLE_EMPTY_RESPONSE = "{\"file\":\"not found\"}";
    private static final long EXAMPLE_BUILD_ID = 124261736;
    private static final String EXAMPLE_OWNER = "DanielHosseini";
    private static final String EXAMPLE_REPOSITORY = "EDA397_2016_Group9";
    private static final String EXAMPLE_BUILD_NUMBER = "32";

    @Rule
    public ActivityTestRule<TravisBuildDetailsActivity> activityRule =
            new ActivityTestRule<>(TravisBuildDetailsActivity.class, true, false);

    private MockWebServer server;

    @Before
    public void before() throws IOException {
        server = new MockWebServer();
        server.start();
        TravisServiceFactory.BASE_URL = server.url("/").toString();
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void testDisplayError() {
        server.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(EXAMPLE_EMPTY_RESPONSE));
        startActivity();
        onView(withId(R.id.travis_build_details)).check(UIAssertions.isGone());
        onView(withId(R.id.fl_loading)).check(UIAssertions.isGone());
        onView(withId(R.id.rl_error)).check(UIAssertions.isVisible());
    }

    @Test
    public void testDisplayResults() {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(EXAMPLE_RESPONSE));
        startActivity();
        onView(withId(R.id.travis_build_details)).check(UIAssertions.isVisible());
        onView(withId(R.id.fl_loading)).check(UIAssertions.isGone());
        onView(withId(R.id.rl_error)).check(UIAssertions.isGone());
    }

    private void startActivity() {
        final Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final Intent intent = new Intent(targetContext, TravisBuildDetailsActivity.class);
        intent.putExtra(EXTRA_BUILD_ID, EXAMPLE_BUILD_ID);
        intent.putExtra(EXTRA_OWNER, EXAMPLE_OWNER);
        intent.putExtra(EXTRA_REPOSITORY, EXAMPLE_REPOSITORY);
        intent.putExtra(EXTRA_BUILD_NUMBER, EXAMPLE_BUILD_NUMBER);
        activityRule.launchActivity(intent);
    }

}