package com.group9.eda397.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Object extending the regular TravisBuild object.
 * <p/>
 * Includes more detailed information about the build like committee
 *
 * @author palmithor
 * @since 19/04/16.
 */
public class TravisBuildDetails extends TravisBuild {

    @SerializedName("committed_at") private final Date committedAt;
    @SerializedName("author_name") private final String authorName;
    @SerializedName("author_email") private final String authorEmail;
    @SerializedName("committer_name") private final String committerName;
    @SerializedName("committer_email") private final String committerEmail;

    public TravisBuildDetails() {
        super();
        this.committedAt = null;
        this.authorName = null;
        this.authorEmail = null;
        this.committerName = null;
        this.committerEmail = null;
    }

    public TravisBuildDetails(final Long id, final Long repositoryId, final String buildNumber, final String state, final Long result, final Date startedAt, final Date finishedAt, final Long duration, final String commit, final String branch, final String message, final String eventType, final Date committedAt, final String authorName, final String authorEmail, final String committerName, final String committerEmail) {
        super(id, repositoryId, buildNumber, state, result, startedAt, finishedAt, duration, commit, branch, message, eventType);
        this.committedAt = committedAt;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.committerName = committerName;
        this.committerEmail = committerEmail;
    }


    public Date getCommittedAt() {
        return committedAt;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public String getCommitterName() {
        return committerName;
    }

    public String getCommitterEmail() {
        return committerEmail;
    }
}
