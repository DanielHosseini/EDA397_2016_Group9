package com.group9.eda397.ui.fragments;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentTransaction;

import com.group9.eda397.MainActivity;
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
 * Espresso unit tests for the Travis Builds Fragment
 *
 * @author palmithor
 * @since 16/04/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TravisBuildsFragmentTest {

    private static final String EXAMPLE_RESPONSE = "[{\"id\":123685710,\"repository_id\":8281689,\"number\":\"12\",\"state\":\"finished\",\"result\":0,\"started_at\":\"2016-04-17T10:57:56Z\",\"finished_at\":\"2016-04-17T11:02:29Z\",\"duration\":273,\"commit\":\"20a8913b73976de745fe18698ed182200ee2b8ed\",\"branch\":\"develop\",\"message\":\"Created a base fragment which all other fragments should extend. Introduces a good structure for inflating the layout as well as initializing Icepick and Butterknife.\",\"event_type\":\"push\"},{\"id\":123588154,\"repository_id\":8281689,\"number\":\"11\",\"state\":\"finished\",\"result\":0,\"started_at\":\"2016-04-16T17:26:32Z\",\"finished_at\":\"2016-04-16T17:30:42Z\",\"duration\":250,\"commit\":\"a63025f0e09dbb3c4fc198395c2cc53dbeb18b45\",\"branch\":\"feature/TravisCI-Integration\",\"message\":\"Added TravisBuild model and deserialization test\",\"event_type\":\"push\"},{\"id\":123058698,\"repository_id\":8281689,\"number\":\"10\",\"state\":\"finished\",\"result\":0,\"started_at\":\"2016-04-14T13:45:37Z\",\"finished_at\":\"2016-04-14T13:51:23Z\",\"duration\":346,\"commit\":\"f82dc9b83d90832f3d179132dc1e61a947cdc75a\",\"branch\":\"develop\",\"message\":\"Add code for replacing the main content with a fragment which will be used by every feature.\",\"event_type\":\"push\"},{\"id\":123057087,\"repository_id\":8281689,\"number\":\"9\",\"state\":\"finished\",\"result\":0,\"started_at\":\"2016-04-14T13:40:30Z\",\"finished_at\":\"2016-04-14T13:45:45Z\",\"duration\":315,\"commit\":\"6d9a392884a6807bf678b7205482c90e4357b26b\",\"branch\":\"feature/timer\",\"message\":\"Fragment example to get started.\",\"event_type\":\"push\"},{\"id\":123052180,\"repository_id\":8281689,\"number\":\"8\",\"state\":\"finished\",\"result\":0,\"started_at\":\"2016-04-14T13:23:44Z\",\"finished_at\":\"2016-04-14T13:29:28Z\",\"duration\":344,\"commit\":\"173dc8d6b49707a16eb9deca27e092ca86701448\",\"branch\":\"develop\",\"message\":\"Merge branch 'feature/Travis_CI_integration' into develop\",\"event_type\":\"push\"},{\"id\":123039728,\"repository_id\":8281689,\"number\":\"7\",\"state\":\"finished\",\"result\":0,\"started_at\":\"2016-04-14T12:36:53Z\",\"finished_at\":\"2016-04-14T12:41:47Z\",\"duration\":294,\"commit\":\"e756ad2d23ab8e878ce573d4c06edcdfd9167bbf\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"Adding support for android support libraries in travis yaml.\",\"event_type\":\"push\"},{\"id\":123038842,\"repository_id\":8281689,\"number\":\"6\",\"state\":\"finished\",\"result\":1,\"started_at\":\"2016-04-14T12:32:37Z\",\"finished_at\":\"2016-04-14T12:34:26Z\",\"duration\":109,\"commit\":\"ffb1140002cca4c7e842069272c821052b97f76c\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"Update support library versions for tests as well.\",\"event_type\":\"push\"},{\"id\":123038531,\"repository_id\":8281689,\"number\":\"5\",\"state\":\"finished\",\"result\":1,\"started_at\":\"2016-04-14T12:31:03Z\",\"finished_at\":\"2016-04-14T12:32:53Z\",\"duration\":110,\"commit\":\"a06f33e34605d660d05f9fb087a0bc7acb1f800a\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"Update support library versions.\",\"event_type\":\"push\"},{\"id\":123037776,\"repository_id\":8281689,\"number\":\"4\",\"state\":\"finished\",\"result\":1,\"started_at\":\"2016-04-14T12:27:45Z\",\"finished_at\":\"2016-04-14T12:29:40Z\",\"duration\":115,\"commit\":\"5a502cd7db0ac78e94bc3afa4d914ea1e71c9b5f\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"Trying to get it to build on Travis-CI by changing the yaml.\",\"event_type\":\"push\"},{\"id\":123036358,\"repository_id\":8281689,\"number\":\"3\",\"state\":\"finished\",\"result\":1,\"started_at\":\"2016-04-14T12:20:53Z\",\"finished_at\":\"2016-04-14T12:22:05Z\",\"duration\":72,\"commit\":\"00ecd26bbf9dc0679ea118b1d70e6bd7666b8120\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"Merge branch 'develop' into feature/Travis_CI_integration\",\"event_type\":\"push\"},{\"id\":123035935,\"repository_id\":8281689,\"number\":\"2\",\"state\":\"finished\",\"result\":1,\"started_at\":\"2016-04-14T12:18:43Z\",\"finished_at\":\"2016-04-14T12:19:25Z\",\"duration\":42,\"commit\":\"898abca217e401371930c21b4dd57c6c25d91b44\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"update gradlewrapper and use platform-tools as component\",\"event_type\":\"push\"},{\"id\":123034328,\"repository_id\":8281689,\"number\":\"1\",\"state\":\"finished\",\"result\":1,\"started_at\":\"2016-04-14T12:08:57Z\",\"finished_at\":\"2016-04-14T12:09:47Z\",\"duration\":50,\"commit\":\"a0d92954f5d5079d2569713ca38595c3b4fec3cd\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"add .travis.yml file\",\"event_type\":\"push\"}]";
    private static final String EXAMPLE_EMPTY_RESPONSE = "[]";
    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    private MockWebServer server;

    @Before
    public void setUp() throws IOException {
        server = new MockWebServer();
        server.start();

        TravisServiceFactory.BASE_URL = server.url("/").toString();
        replaceFragment();
    }

    private void replaceFragment() {
        FragmentTransaction ft = mainActivityRule.getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, TravisBuildsFragment.newInstance());
        ft.commit();
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void testDisplayEmptyList() throws InterruptedException {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(EXAMPLE_EMPTY_RESPONSE));
        onView(withId(R.id.tv_no_results)).check(UIAssertions.isVisible());
        onView(withId(R.id.tv_no_results)).check(UIAssertions.isVisible());
        onView(withId(R.id.recycler_view)).check(UIAssertions.isVisible());
        onView(withId(R.id.rl_error)).check(UIAssertions.isGone());
        onView(withId(R.id.fab)).check(UIAssertions.isGone());
    }

    @Test
    public void testDisplayError() throws InterruptedException {
        server.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(EXAMPLE_EMPTY_RESPONSE));
        onView(withId(R.id.tv_no_results)).check(UIAssertions.isGone());
        onView(withId(R.id.recycler_view)).check(UIAssertions.isVisible());
        onView(withId(R.id.swipe_refresh_layout)).check(UIAssertions.isVisible());
        onView(withId(R.id.rl_error)).check(UIAssertions.isVisible());
        onView(withId(R.id.fab)).check(UIAssertions.isGone());
    }

    @Test
    public void testDisplayResults() throws InterruptedException {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(EXAMPLE_RESPONSE));
        onView(withId(R.id.tv_no_results)).check(UIAssertions.isGone());
        onView(withId(R.id.recycler_view)).check(UIAssertions.isVisible());
        onView(withId(R.id.swipe_refresh_layout)).check(UIAssertions.isVisible());
        onView(withId(R.id.rl_error)).check(UIAssertions.isGone());
        onView(withId(R.id.fab)).check(UIAssertions.isGone());
    }
}