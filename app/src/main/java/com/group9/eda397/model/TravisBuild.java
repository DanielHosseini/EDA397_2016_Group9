package com.group9.eda397.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Model representing the Travis CI /build REST API
 *
 * @author palmithor
 * @since 16/04/16.
 */
public class TravisBuild {
    @SerializedName("id") final Long id;
    @SerializedName("repository_id") final Long repositoryId;
    @SerializedName("number") final String buildNumber;
    @SerializedName("state") final String state;
    @SerializedName("result") final Long result;
    @SerializedName("started_at") final Date startedAt;
    @SerializedName("finished_at") final Date finishedAt;
    @SerializedName("duration") final Long duration;
    @SerializedName("commit") final String commit;
    @SerializedName("branch") final String branch;
    @SerializedName("message") final String message;
    @SerializedName("event_type") final String eventType;

    public TravisBuild() {
        this.id = null;
        this.repositoryId = null;
        this.buildNumber = null;
        this.state = null;
        this.result = null;
        this.startedAt = null;
        this.finishedAt = null;
        this.duration = null;
        this.commit = null;
        this.branch = null;
        this.message = null;
        this.eventType = null;
    }

    public TravisBuild(final Long id, final Long repositoryId, final String buildNumber, final String state, final Long result, final Date startedAt, final Date finishedAt, final Long duration, final String commit, final String branch, final String message, final String eventType) {
        this.id = id;
        this.repositoryId = repositoryId;
        this.buildNumber = buildNumber;
        this.state = state;
        this.result = result;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.duration = duration;
        this.commit = commit;
        this.branch = branch;
        this.message = message;
        this.eventType = eventType;
    }

    public Long getId() {
        return id;
    }

    public Long getRepositoryId() {
        return repositoryId;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public String getState() {
        return state;
    }

    public Long getResult() {
        return result;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public Long getDuration() {
        return duration;
    }

    public String getCommit() {
        return commit;
    }

    public String getBranch() {
        return branch;
    }

    public String getMessage() {
        return message;
    }

    public String getEventType() {
        return eventType;
    }
}
