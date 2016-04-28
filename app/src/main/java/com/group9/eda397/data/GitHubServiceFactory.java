package com.group9.eda397.data;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group9.eda397.data.github.GitHubService;
import com.group9.eda397.data.util.Rfc339DateJsonAdapter;
import com.group9.eda397.model.GitHubCommitItem;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Path;

/**
 * Factory for providing instances of GitHub Service
 *
 * @author palmithor
 * @since 24/03/16.
 */
public class GitHubServiceFactory {

    private static final String json = "{\n" +
            "    \"sha\": \"83a238e0ba33366f650a6ad1dda266fe77d26d57\",\n" +
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
            "  }";
    public static String BASE_URL = "https://api.github.com";

    public static GitHubService getService(final Application app) {
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Rfc339DateJsonAdapter())
                .create();
        //return new Retrofit.Builder()
        //        .addConverterFactory(GsonConverterFactory.create(gson))
        //        .client(HttpUtil.createApiClient(app).build()) // todo add interceptors if needed, access token for instance
        //        .baseUrl(BASE_URL)
        //        .build()
        //        .create(GitHubService.class);

        return new GitHubService() {
            @Override
            public Call<List<GitHubCommitItem>> getCommits(@Path(PATH_PARAM_OWNER) final String owner, @Path(PATH_PARAM_REPO) final String repository) {
                return null;
            }

            @Override
            public List<GitHubCommitItem> getCommitsMock() {
                return Arrays.asList(
                        gson.fromJson(json, GitHubCommitItem.class),
                        gson.fromJson(json, GitHubCommitItem.class),
                        gson.fromJson(json, GitHubCommitItem.class),
                        gson.fromJson(json, GitHubCommitItem.class),
                        gson.fromJson(json, GitHubCommitItem.class),
                        gson.fromJson(json, GitHubCommitItem.class),
                        gson.fromJson(json, GitHubCommitItem.class),
                        gson.fromJson(json, GitHubCommitItem.class)
                );
            }
        };
    }
}