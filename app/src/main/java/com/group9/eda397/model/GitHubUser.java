package com.group9.eda397.model;

import com.google.gson.annotations.SerializedName;
import com.group9.eda397.utils.StringUtils;

/**
 * @author palmithor
 * @since 24/03/16.
 */
public class GitHubUser {

    @SerializedName("login") private final String username;
    @SerializedName("id") private final Long id;
    @SerializedName("avatar_url") private final String avatarUrl;
    @SerializedName("gravatar_id") private final String gravatarUrl;
    @SerializedName("url") private final String url;
    @SerializedName("html_url") private final String htmlUrl;
    @SerializedName("followers_url") private final String followersUrl;
    @SerializedName("following_url") private final String followingUrl;
    @SerializedName("gist_url") private final String gistUrl;
    @SerializedName("starred_url") private final String starredUrl;
    @SerializedName("subscriptions_url") private final String subscriptionsUrl;
    @SerializedName("organizations_url") private final String organizationsUrl;
    @SerializedName("repos_url") private final String reposUrl;
    @SerializedName("events_url") private final String eventsUrl;
    @SerializedName("received_events_url") private final String receivedEventsUrl;
    @SerializedName("type") private final String type;
    @SerializedName("site_admin") private final Boolean siteAdmin;

    public GitHubUser() {
        this.username = null;
        this.id = null;
        this.avatarUrl = null;
        this.gravatarUrl = null;
        this.url = null;
        this.htmlUrl = null;
        this.followersUrl = null;
        this.followingUrl = null;
        this.gistUrl = null;
        this.starredUrl = null;
        this.subscriptionsUrl = null;
        this.organizationsUrl = null;
        this.reposUrl = null;
        this.eventsUrl = null;
        this.receivedEventsUrl = null;
        this.type = null;
        this.siteAdmin = null;
    }

    public GitHubUser(final String username, final Long id, final String avatarUrl, final String gravatarUrl, final String url, final String htmlUrl, final String followersUrl, final String followingUrl, final String gistUrl, final String starredUrl, final String subscriptionsUrl, final String organizationsUrl, final String reposUrl, final String eventsUrl, final String receivedEventsUrl, final String type, final boolean siteAdmin) {
        this.username = username;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.gravatarUrl = gravatarUrl;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.followersUrl = followersUrl;
        this.followingUrl = followingUrl;
        this.gistUrl = gistUrl;
        this.starredUrl = starredUrl;
        this.subscriptionsUrl = subscriptionsUrl;
        this.organizationsUrl = organizationsUrl;
        this.reposUrl = reposUrl;
        this.eventsUrl = eventsUrl;
        this.receivedEventsUrl = receivedEventsUrl;
        this.type = type;
        this.siteAdmin = siteAdmin;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getGravatarUrl() {
        return gravatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public String getGistUrl() {
        return gistUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public String getType() {
        return type;
    }

    public Boolean getSiteAdmin() {
        return siteAdmin;
    }

    public boolean hasUsername() {
        return StringUtils.isNotBlank(username);
    }
}
