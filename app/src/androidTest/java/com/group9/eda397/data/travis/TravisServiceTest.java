package com.group9.eda397.data.travis;

import android.test.InstrumentationTestCase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.group9.eda397.data.TravisServiceFactory;
import com.group9.eda397.data.github.GitHubService;
import com.group9.eda397.data.github.TravisService;
import com.group9.eda397.data.util.Rfc339DateJsonAdapter;
import com.group9.eda397.model.TravisBuild;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
public class TravisServiceTest extends InstrumentationTestCase {
    public static final String OWNER_NO_BUILDS = "OWNER_NO_BUILDS";
    public static final String OWNER_WITH_BUILDS = "OWNER_WITH_LIST";

    public static final int RESPONSE_TIME_MILLISECONDS = 60;
    public static final String REPOSITORY = "repository";
    private MockRetrofit mockRetrofit;
    private Retrofit retrofit;
    private Gson gson;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        retrofit = new Retrofit.Builder().baseUrl(TravisServiceFactory.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();
        behavior.setDelay(RESPONSE_TIME_MILLISECONDS, TimeUnit.MILLISECONDS);

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();

        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new Rfc339DateJsonAdapter())
                .create();
    }

    @Test
    public void testGetOwnerWithNoBuilds() throws Exception {
        BehaviorDelegate<TravisService> delegate = mockRetrofit.create(TravisService.class);
        TravisServiceMock travisServiceMock = new TravisServiceMock(delegate);
        Call<List<TravisBuild>> travisBuilds = travisServiceMock.getBuilds(OWNER_NO_BUILDS, REPOSITORY);
        List<TravisBuild> response = travisBuilds.execute().body();
        assertThat(response.size(), is(0));
    }

    @Test
    public void testGetOwnerWithBuilds() throws Exception {
        BehaviorDelegate<TravisService> delegate = mockRetrofit.create(TravisService.class);
        TravisServiceMock travisServiceMock = new TravisServiceMock(delegate);
        Call<List<TravisBuild>> travisBuilds = travisServiceMock.getBuilds(OWNER_WITH_BUILDS, REPOSITORY);
        List<TravisBuild> response = travisBuilds.execute().body();
        assertThat(response.size(), is(10));
    }

    /**
     * A mock implementation of the {@link GitHubService} API interface.
     */
    final class TravisServiceMock implements TravisService {
        static final String jsonListResponse = "\n" +
                "[{\"id\":123058698,\"repository_id\":8281689,\"number\":\"10\",\"state\":\"finished\",\"result\":0,\"started_at\":\"2016-04-14T13:45:37Z\",\"finished_at\":\"2016-04-14T13:51:23Z\",\"duration\":346,\"commit\":\"f82dc9b83d90832f3d179132dc1e61a947cdc75a\",\"branch\":\"develop\",\"message\":\"Add code for replacing the main content with a fragment which will be used by every feature.\",\"event_type\":\"push\"},{\"id\":123057087,\"repository_id\":8281689,\"number\":\"9\",\"state\":\"finished\",\"result\":0,\"started_at\":\"2016-04-14T13:40:30Z\",\"finished_at\":\"2016-04-14T13:45:45Z\",\"duration\":315,\"commit\":\"6d9a392884a6807bf678b7205482c90e4357b26b\",\"branch\":\"feature/timer\",\"message\":\"Fragment example to get started.\",\"event_type\":\"push\"},{\"id\":123052180,\"repository_id\":8281689,\"number\":\"8\",\"state\":\"finished\",\"result\":0,\"started_at\":\"2016-04-14T13:23:44Z\",\"finished_at\":\"2016-04-14T13:29:28Z\",\"duration\":344,\"commit\":\"173dc8d6b49707a16eb9deca27e092ca86701448\",\"branch\":\"develop\",\"message\":\"Merge branch 'feature/Travis_CI_integration' into develop\",\"event_type\":\"push\"},{\"id\":123039728,\"repository_id\":8281689,\"number\":\"7\",\"state\":\"finished\",\"result\":0,\"started_at\":\"2016-04-14T12:36:53Z\",\"finished_at\":\"2016-04-14T12:41:47Z\",\"duration\":294,\"commit\":\"e756ad2d23ab8e878ce573d4c06edcdfd9167bbf\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"Adding support for android support libraries in travis yaml.\",\"event_type\":\"push\"},{\"id\":123038842,\"repository_id\":8281689,\"number\":\"6\",\"state\":\"finished\",\"result\":1,\"started_at\":\"2016-04-14T12:32:37Z\",\"finished_at\":\"2016-04-14T12:34:26Z\",\"duration\":109,\"commit\":\"ffb1140002cca4c7e842069272c821052b97f76c\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"Update support library versions for tests as well.\",\"event_type\":\"push\"},{\"id\":123038531,\"repository_id\":8281689,\"number\":\"5\",\"state\":\"finished\",\"result\":1,\"started_at\":\"2016-04-14T12:31:03Z\",\"finished_at\":\"2016-04-14T12:32:53Z\",\"duration\":110,\"commit\":\"a06f33e34605d660d05f9fb087a0bc7acb1f800a\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"Update support library versions.\",\"event_type\":\"push\"},{\"id\":123037776,\"repository_id\":8281689,\"number\":\"4\",\"state\":\"finished\",\"result\":1,\"started_at\":\"2016-04-14T12:27:45Z\",\"finished_at\":\"2016-04-14T12:29:40Z\",\"duration\":115,\"commit\":\"5a502cd7db0ac78e94bc3afa4d914ea1e71c9b5f\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"Trying to get it to build on Travis-CI by changing the yaml.\",\"event_type\":\"push\"},{\"id\":123036358,\"repository_id\":8281689,\"number\":\"3\",\"state\":\"finished\",\"result\":1,\"started_at\":\"2016-04-14T12:20:53Z\",\"finished_at\":\"2016-04-14T12:22:05Z\",\"duration\":72,\"commit\":\"00ecd26bbf9dc0679ea118b1d70e6bd7666b8120\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"Merge branch 'develop' into feature/Travis_CI_integration\",\"event_type\":\"push\"},{\"id\":123035935,\"repository_id\":8281689,\"number\":\"2\",\"state\":\"finished\",\"result\":1,\"started_at\":\"2016-04-14T12:18:43Z\",\"finished_at\":\"2016-04-14T12:19:25Z\",\"duration\":42,\"commit\":\"898abca217e401371930c21b4dd57c6c25d91b44\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"update gradlewrapper and use platform-tools as component\",\"event_type\":\"push\"},{\"id\":123034328,\"repository_id\":8281689,\"number\":\"1\",\"state\":\"finished\",\"result\":1,\"started_at\":\"2016-04-14T12:08:57Z\",\"finished_at\":\"2016-04-14T12:09:47Z\",\"duration\":50,\"commit\":\"a0d92954f5d5079d2569713ca38595c3b4fec3cd\",\"branch\":\"feature/Travis_CI_integration\",\"message\":\"add .travis.yml file\",\"event_type\":\"push\"}]";
        private final BehaviorDelegate<TravisService> delegate;

        public TravisServiceMock(BehaviorDelegate<TravisService> delegate) {
            this.delegate = delegate;
            // Seed some mock data.

        }

        @Override
        public Call<List<TravisBuild>> getBuilds(final String owner, final String repository) {
            if (owner.equals(OWNER_NO_BUILDS)) {
                return delegate.returningResponse(Collections.emptyList()).getBuilds(owner, repository);
            } else if (owner.equals(OWNER_WITH_BUILDS)) {
                Type listType = new TypeToken<List<TravisBuild>>() {
                }.getType();
                return delegate.returningResponse(gson.fromJson(jsonListResponse, listType)).getBuilds(owner, repository);
            }
            throw new RuntimeException("Unknown owner");
        }
    }

}