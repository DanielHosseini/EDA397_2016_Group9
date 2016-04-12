package com.group9.eda397.data.github;

import android.test.InstrumentationTestCase;

import com.group9.eda397.model.GitHubCommitItem;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author palmithor
 * @since 24/03/16.
 */
public class GitHubServiceTest extends InstrumentationTestCase {
    public static final int RESPONSE_TIME_MILLISECONDS = 60;
    private MockRetrofit mockRetrofit;
    private Retrofit retrofit;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        retrofit = new Retrofit.Builder().baseUrl("http://test.com")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();
        behavior.setDelay(RESPONSE_TIME_MILLISECONDS, TimeUnit.MILLISECONDS);

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }

    @Test
    public void testGetGitHubCommits() throws Exception {
        BehaviorDelegate<GitHubService> delegate = mockRetrofit.create(GitHubService.class);
        GitHubServiceMock gitHubServiceMock = new GitHubServiceMock(delegate);
        Call<List<GitHubCommitItem>> gitHubCommitsCall = gitHubServiceMock.getGitHubCommits("", "");
        List<GitHubCommitItem> response = gitHubCommitsCall.execute().body();
        assertThat(response.size(), is(0));
    }

    /**
     * A mock implementation of the {@link GitHubService} API interface.
     */
    static final class GitHubServiceMock implements GitHubService {
        private final BehaviorDelegate<GitHubService> delegate;
        private final Map<String, Map<String, List<GitHubCommitItem>>> gitHubCommits;

        public GitHubServiceMock(BehaviorDelegate<GitHubService> delegate) {
            this.delegate = delegate;
            gitHubCommits = new LinkedHashMap<>();

            // Seed some mock data.

        }

        @Override
        public Call<List<GitHubCommitItem>> getGitHubCommits(final String owner, final String repository) {
            List<GitHubCommitItem> response = Collections.emptyList();
            return delegate.returningResponse(response).getGitHubCommits(owner, repository);
        }
    }

}