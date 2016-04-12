package com.group9.eda397.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author palmithor
 * @since 24/03/16.
 */
public class GitHubParent {

    @SerializedName("sha") private final String sha;
    @SerializedName("url") private final String url;
    @SerializedName("html_url") private final String htmlUrl;

    public GitHubParent() {
        this.sha = null;
        this.url = null;
        this.htmlUrl = null;
    }

    public GitHubParent(final String sha, final String url, final String htmlUrl) {
        this.sha = sha;
        this.url = url;
        this.htmlUrl = htmlUrl;
    }

    public String getSha() {
        return sha;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }
}
