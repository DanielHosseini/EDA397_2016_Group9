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

    private TravisBuild(final Builder builder) {
        id = builder.id;
        repositoryId = builder.repositoryId;
        buildNumber = builder.buildNumber;
        state = builder.state;
        result = builder.result;
        startedAt = builder.startedAt;
        finishedAt = builder.finishedAt;
        duration = builder.duration;
        commit = builder.commit;
        branch = builder.branch;
        message = builder.message;
        eventType = builder.eventType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(final TravisBuild copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.repositoryId = copy.repositoryId;
        builder.buildNumber = copy.buildNumber;
        builder.state = copy.state;
        builder.result = copy.result;
        builder.startedAt = copy.startedAt;
        builder.finishedAt = copy.finishedAt;
        builder.duration = copy.duration;
        builder.commit = copy.commit;
        builder.branch = copy.branch;
        builder.message = copy.message;
        builder.eventType = copy.eventType;
        return builder;
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

    public boolean isOngoing() {
        return finishedAt == null;
    }


    public static final class Builder {
        private Long id;
        private Long repositoryId;
        private String buildNumber;
        private String state;
        private Long result;
        private Date startedAt;
        private Date finishedAt;
        private Long duration;
        private String commit;
        private String branch;
        private String message;
        private String eventType;

        private Builder() {
        }

        public Builder id(final Long val) {
            id = val;
            return this;
        }

        public Builder repositoryId(final Long val) {
            repositoryId = val;
            return this;
        }

        public Builder buildNumber(final String val) {
            buildNumber = val;
            return this;
        }

        public Builder state(final String val) {
            state = val;
            return this;
        }

        public Builder result(final Long val) {
            result = val;
            return this;
        }

        public Builder startedAt(final Date val) {
            startedAt = val;
            return this;
        }

        public Builder finishedAt(final Date val) {
            finishedAt = val;
            return this;
        }

        public Builder duration(final Long val) {
            duration = val;
            return this;
        }

        public Builder commit(final String val) {
            commit = val;
            return this;
        }

        public Builder branch(final String val) {
            branch = val;
            return this;
        }

        public Builder message(final String val) {
            message = val;
            return this;
        }

        public Builder eventType(final String val) {
            eventType = val;
            return this;
        }

        public TravisBuild build() {
            return new TravisBuild(this);
        }
    }
}
