package com.group9.eda397.ui.fragments;


import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.FragmentTransaction;

import com.group9.eda397.MainActivity;
import com.group9.eda397.R;
import com.group9.eda397.data.GitHubServiceFactory;
import com.group9.eda397.data.TravisServiceFactory;
import com.group9.eda397.ui.UIAssertions;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class GithubCommitsFragmentTest {

    private static final String EXAMPLE_RESPONSE = "[\"sha\": \"83a238e0ba33366f650a6ad1dda266fe77d26d57\",\n" +
            "    \"commit\":  {\n" +
            "      \"author\":  {\n" +
            "        \"name\": \"DanielHosseini\",\n" +
            "        \"email\": \"daniel.hosseini1@gmail.com\",\n" +
            "        \"date\": \"2016-03-24T13:39:43Z\"\n" +
            "      },\n" +
            "      \"committer\":  {\n" +
            "        \"name\": \"DanielHosseini\",\n" +
            "        \"email\": \"daniel.hosseini1@gmail.com\",\n" +
            "        \"date\": \"2016-03-24T13:39:43Z\"\n" +
            "      },\n" +
            "      \"message\": \"Added README file\",\n" +
            "      \"tree\":  {\n" +
            "        \"sha\": \"b5f0d0b647fcb67a7bffa1b1441e785384316f90\",\n" +
            "        \"url\": \"https://api.github.com/repos/DanielHosseini/EDA397_2016_Group9/git/trees/b5f0d0b647fcb67a7bffa1b1441e785384316f90\"\n" +
            "      },\n" +
            "      \"url\": \"https://api.github.com/repos/DanielHosseini/EDA397_2016_Group9/git/commits/83a238e0ba33366f650a6ad1dda266fe77d26d57\",\n" +
            "      \"comment_count\": 0\n" +
            "    },\n" +
            "    \"url\": \"https://api.github.com/repos/DanielHosseini/EDA397_2016_Group9/commits/83a238e0ba33366f650a6ad1dda266fe77d26d57\",\n" +
            "    \"html_url\": \"https://github.com/DanielHosseini/EDA397_2016_Group9/commit/83a238e0ba33366f650a6ad1dda266fe77d26d57\",\n" +
            "    \"comments_url\": \"https://api.github.com/repos/DanielHosseini/EDA397_2016_Group9/commits/83a238e0ba33366f650a6ad1dda266fe77d26d57/comments\",\n" +
            "    \"author\":  {\n" +
            "      \"login\": \"DanielHosseini\",\n" +
            "      \"id\": 2711831,\n" +
            "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/2711831?v=3\",\n" +
            "      \"gravatar_id\": \"\",\n" +
            "      \"url\": \"https://api.github.com/users/DanielHosseini\",\n" +
            "      \"html_url\": \"https://github.com/DanielHosseini\",\n" +
            "      \"followers_url\": \"https://api.github.com/users/DanielHosseini/followers\",\n" +
            "      \"following_url\": \"https://api.github.com/users/DanielHosseini/following{/other_user}\",\n" +
            "      \"gists_url\": \"https://api.github.com/users/DanielHosseini/gists{/gist_id}\",\n" +
            "      \"starred_url\": \"https://api.github.com/users/DanielHosseini/starred{/owner}{/repo}\",\n" +
            "      \"subscriptions_url\": \"https://api.github.com/users/DanielHosseini/subscriptions\",\n" +
            "      \"organizations_url\": \"https://api.github.com/users/DanielHosseini/orgs\",\n" +
            "      \"repos_url\": \"https://api.github.com/users/DanielHosseini/repos\",\n" +
            "      \"events_url\": \"https://api.github.com/users/DanielHosseini/events{/privacy}\",\n" +
            "      \"received_events_url\": \"https://api.github.com/users/DanielHosseini/received_events\",\n" +
            "      \"type\": \"User\",\n" +
            "      \"site_admin\": false\n" +
            "    },\n" +
            "    \"committer\":  {\n" +
            "      \"login\": \"DanielHosseini\",\n" +
            "      \"id\": 2711831,\n" +
            "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/2711831?v=3\",\n" +
            "      \"gravatar_id\": \"\",\n" +
            "      \"url\": \"https://api.github.com/users/DanielHosseini\",\n" +
            "      \"html_url\": \"https://github.com/DanielHosseini\",\n" +
            "      \"followers_url\": \"https://api.github.com/users/DanielHosseini/followers\",\n" +
            "      \"following_url\": \"https://api.github.com/users/DanielHosseini/following{/other_user}\",\n" +
            "      \"gists_url\": \"https://api.github.com/users/DanielHosseini/gists{/gist_id}\",\n" +
            "      \"starred_url\": \"https://api.github.com/users/DanielHosseini/starred{/owner}{/repo}\",\n" +
            "      \"subscriptions_url\": \"https://api.github.com/users/DanielHosseini/subscriptions\",\n" +
            "      \"organizations_url\": \"https://api.github.com/users/DanielHosseini/orgs\",\n" +
            "      \"repos_url\": \"https://api.github.com/users/DanielHosseini/repos\",\n" +
            "      \"events_url\": \"https://api.github.com/users/DanielHosseini/events{/privacy}\",\n" +
            "      \"received_events_url\": \"https://api.github.com/users/DanielHosseini/received_events\",\n" +
            "      \"type\": \"User\",\n" +
            "      \"site_admin\": false\n" +
            "    },\n" +
            "    \"parents\":  [\n" +
            "       {\n" +
            "        \"sha\": \"09470dcee3c688145732f235f26e32f55cc2e834\",\n" +
            "        \"url\": \"https://api.github.com/repos/DanielHosseini/EDA397_2016_Group9/commits/09470dcee3c688145732f235f26e32f55cc2e834\",\n" +
            "        \"html_url\": \"https://github.com/DanielHosseini/EDA397_2016_Group9/commit/09470dcee3c688145732f235f26e32f55cc2e834\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }]";

    private static final String EXAMPLE_EMPTY_RESPONSE = "[]";
    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    private MockWebServer server;

    @Before
    public void setUp() throws IOException {
        server = new MockWebServer();
        server.start();

        GitHubServiceFactory.BASE_URL = server.url("/").toString();
        replaceFragment();
    }

    private void replaceFragment() {
        FragmentTransaction ft = mainActivityRule.getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, GithubCommitsFragment.newInstance());
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
