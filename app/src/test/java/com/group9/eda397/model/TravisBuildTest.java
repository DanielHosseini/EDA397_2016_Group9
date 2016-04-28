package com.group9.eda397.model;

import com.google.gson.internal.bind.util.ISO8601Utils;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author palmithor
 * @since 16/04/16.
 */
public class TravisBuildTest extends GsonSerializationTest {

    private static final String json = "{\n" +
            "id: 123058698,\n" +
            "repository_id: 8281689,\n" +
            "number: \"10\",\n" +
            "state: \"finished\",\n" +
            "result: 0,\n" +
            "started_at: \"2016-04-14T13:45:37Z\",\n" +
            "finished_at: \"2016-04-14T13:51:23Z\",\n" +
            "duration: 346,\n" +
            "commit: \"f82dc9b83d90832f3d179132dc1e61a947cdc75a\",\n" +
            "branch: \"develop\",\n" +
            "message: \"Add code for replacing the main content with a fragment which will be used by every feature.\",\n" +
            "event_type: \"push\"\n" +
            "}";

    @Test
    public void testDeserializeJson() {
        TravisBuild travisBuild = gson.fromJson(json, TravisBuild.class);
        assertThat(travisBuild.getId(), is(123058698L));
        assertThat(travisBuild.getRepositoryId(), is(8281689L));
        assertThat(travisBuild.getBuildNumber(), is("10"));
        assertThat(travisBuild.getState(), is("finished"));
        assertThat(travisBuild.getResult(), is(0L));
        assertThat(ISO8601Utils.format(travisBuild.getStartedAt()), is("2016-04-14T13:45:37Z"));
        assertThat(ISO8601Utils.format(travisBuild.getFinishedAt()), is("2016-04-14T13:51:23Z"));
        assertThat(travisBuild.getDuration(), is(346L));
        assertThat(travisBuild.getCommit(), is("f82dc9b83d90832f3d179132dc1e61a947cdc75a"));
        assertThat(travisBuild.getBranch(), is("develop"));
        assertThat(travisBuild.getMessage(), is("Add code for replacing the main content with a fragment which will be used by every feature."));
        assertThat(travisBuild.getEventType(), is("push"));
    }

    @Test
    public void testOngoing() {
        TravisBuild ongoingBuild = TravisBuild.newBuilder().build();
        assertThat(ongoingBuild.isOngoing(), is(true));
        TravisBuild finishedBuild = TravisBuild.newBuilder().finishedAt(new Date(new Date().getTime() - 10000)).build();
        assertThat(finishedBuild.isOngoing(), is(false));
    }
}