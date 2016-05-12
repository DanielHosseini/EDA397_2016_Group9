package com.group9.eda397.model;

import com.google.gson.internal.bind.util.ISO8601Utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author palmithor
 * @since 19/04/16.
 */
public class TravisBuildDetailsTest extends GsonSerializationTest {

    private static final String json = "{\n" +
            "  \"id\": 123757369,\n" +
            "  \"repository_id\": 8281689,\n" +
            "  \"number\": \"16\",\n" +
            "  \"config\": {\n" +
            "    \"language\": \"android\",\n" +
            "    \"android\": {\n" +
            "      \"components\": [\"platform-tools\", \"tools\", \"build-tools-23.0.2\", \"android-23\", \"extra-google-google_play_services\", \"extra-android-m2repository\"]\n" +
            "    },\n" +
            "    \"script\": [\"./gradlew build app:test\"],\n" +
            "    \".result\": \"configured\",\n" +
            "    \"group\": \"stable\",\n" +
            "    \"dist\": \"precise\"\n" +
            "  },\n" +
            "  \"state\": \"finished\",\n" +
            "  \"result\": 0,\n" +
            "  \"status\": 0,\n" +
            "  \"started_at\": \"2016-04-17T19:55:34Z\",\n" +
            "  \"finished_at\": \"2016-04-17T20:01:08Z\",\n" +
            "  \"duration\": 334,\n" +
            "  \"commit\": \"ef793efa625e0133a434cc4af63cb9c28bab71a8\",\n" +
            "  \"branch\": \"feature/TravisCI-Integration\",\n" +
            "  \"message\": \"Fix tests, assertions were incorrect.\",\n" +
            "  \"committed_at\": \"2016-04-17T19:55:11Z\",\n" +
            "  \"author_name\": \"palmithor\",\n" +
            "  \"author_email\": \"palmithor@gmail.com\",\n" +
            "  \"committer_name\": \"palmithor\",\n" +
            "  \"committer_email\": \"palmithor@gmail.com\",\n" +
            "  \"compare_url\": \"https://github.com/DanielHosseini/EDA397_2016_Group9/compare/ab4f9955192c...ef793efa625e\",\n" +
            "  \"event_type\": \"push\",\n" +
            "  \"matrix\": [{\n" +
            "    \"id\": 123757370,\n" +
            "    \"repository_id\": 8281689,\n" +
            "    \"number\": \"16.1\",\n" +
            "    \"config\": {\n" +
            "      \"language\": \"android\",\n" +
            "      \"android\": {\n" +
            "        \"components\": [\"platform-tools\", \"tools\", \"build-tools-23.0.2\", \"android-23\", \"extra-google-google_play_services\", \"extra-android-m2repository\"]\n" +
            "      },\n" +
            "      \"script\": [\"./gradlew build app:test\"],\n" +
            "      \".result\": \"configured\",\n" +
            "      \"group\": \"stable\",\n" +
            "      \"dist\": \"precise\",\n" +
            "      \"os\": \"linux\"\n" +
            "    },\n" +
            "    \"result\": 0,\n" +
            "    \"started_at\": \"2016-04-17T19:55:34Z\",\n" +
            "    \"finished_at\": \"2016-04-17T20:01:08Z\",\n" +
            "    \"allow_failure\": false\n" +
            "  }]\n" +
            "}";

    @Test
    public void testDeserializeJson() {
        TravisBuildDetails travisBuild = gson.fromJson(json, TravisBuildDetails.class);
        assertThat(travisBuild.getId(), is(123757369L));
        assertThat(travisBuild.getRepositoryId(), is(8281689L));
        assertThat(travisBuild.getBuildNumber(), is("16"));
        assertThat(travisBuild.getState(), is("finished"));
        assertThat(travisBuild.getResult(), is(0L));
        assertThat(ISO8601Utils.format(travisBuild.getStartedAt()), is("2016-04-17T19:55:34Z"));
        assertThat(ISO8601Utils.format(travisBuild.getFinishedAt()), is("2016-04-17T20:01:08Z"));
        assertThat(travisBuild.getDuration(), is(334L));
        assertThat(travisBuild.getCommit(), is("ef793efa625e0133a434cc4af63cb9c28bab71a8"));
        assertThat(travisBuild.getBranch(), is("feature/TravisCI-Integration"));
        assertThat(travisBuild.getMessage(), is("Fix tests, assertions were incorrect."));
        assertThat(travisBuild.getEventType(), is("push"));

        assertThat(travisBuild.getAuthorName(), is("palmithor"));
        assertThat(travisBuild.getAuthorEmail(), is("palmithor@gmail.com"));
        assertThat(ISO8601Utils.format(travisBuild.getCommittedAt()), is("2016-04-17T19:55:11Z"));
        assertThat(travisBuild.getCommitterEmail(), is("palmithor@gmail.com"));
        assertThat(travisBuild.getCommitterName(), is("palmithor"));
        assertThat(travisBuild.getCompareUrl(), is("https://github.com/DanielHosseini/EDA397_2016_Group9/compare/ab4f9955192c...ef793efa625e"));
    }

}