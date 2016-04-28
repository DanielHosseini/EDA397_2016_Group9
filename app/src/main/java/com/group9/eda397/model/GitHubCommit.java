package com.group9.eda397.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author palmithor
 * @since 24/03/16.
 */
public class GitHubCommit {

    @SerializedName("author") private final GitHubCommitUser author;
    @SerializedName("committer") private final GitHubCommitUser committer;
    @SerializedName("message") private final String message;
    @SerializedName("tree") private final GitHubTree tree;
    @SerializedName("url") private final String url;
    @SerializedName("comment_count") private final Integer commentCount;

    public GitHubCommit() {
        this.author = null;
        this.committer = null;
        this.message = null;
        this.tree = null;
        this.url = null;
        this.commentCount = null;
    }

    public GitHubCommit(final GitHubCommitUser author, final GitHubCommitUser committer, final String message, final GitHubTree tree, final String url, final Integer commentCount) {
        this.author = author;
        this.committer = committer;
        this.message = message;
        this.tree = tree;
        this.url = url;
        this.commentCount = commentCount;
    }

    public GitHubCommitUser getAuthor() {
        return author;
    }

    public GitHubCommitUser getCommitter() {
        return committer;
    }

    public String getMessage() {
        return message;
    }

    public GitHubTree getTree() {
        return tree;
    }

    public String getUrl() {
        return url;
    }

    public Integer getCommentCount() {
        return commentCount;
    }
}
