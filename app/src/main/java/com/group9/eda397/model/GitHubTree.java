package com.group9.eda397.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author palmithor
 * @since 24/03/16.
 */
public class GitHubTree {

    @SerializedName("sha") private final String sha;
    @SerializedName("url") private final String url;

    public GitHubTree() {
        this.sha = null;
        this.url = null;
    }

    public GitHubTree(final String sha, final String url) {
        this.sha = sha;
        this.url = url;
    }

    public String getSha() {
        return sha;
    }

    public String getUrl() {
        return url;
    }
}
