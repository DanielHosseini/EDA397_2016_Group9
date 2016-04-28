package com.group9.eda397.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A model describing a GitHub Commit from GitHub API
 *
 * @author palmithor
 * @since 24/03/16.
 */
public class GitHubCommitItem {

    @SerializedName("sha") private final String sha;
    @SerializedName("commit") private final GitHubCommit commit;
    @SerializedName("url") private final String url;
    @SerializedName("html_url") private final String htmlUrl;
    @SerializedName("author") private final GitHubUser author;
    @SerializedName("committer") private final GitHubUser committer;
    @SerializedName("parents") private final List<GitHubParent> parents;

    public GitHubCommitItem() {
        this.sha = null;
        this.commit = null;
        this.url = null;
        this.htmlUrl = null;
        this.author = null;
        this.committer = null;
        this.parents = null;
    }

    public GitHubCommitItem(final String sha, final GitHubCommit commit, final String url, final String htmlUrl, final GitHubUser author, final GitHubUser committer, final List<GitHubParent> parents) {
        this.sha = sha;
        this.commit = commit;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.author = author;
        this.committer = committer;
        this.parents = parents;
    }

    public String getSha() {
        return sha;
    }

    public GitHubCommit getCommit() {
        return commit;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public GitHubUser getAuthor() {
        return author;
    }

    public GitHubUser getCommitter() {
        return committer;
    }

    public List<GitHubParent> getParents() {
        return parents;
    }
}

/* Example response
{
    "sha": "83a238e0ba33366f650a6ad1dda266fe77d26d57",
    "commit":  {
      "author":  {
        "name": "DanielHosseini",
        "email": "daniel.hosseini1@gmail.com",
        "date": "2016-03-24T13:39:43Z"
      },
      "committer":  {
        "name": "DanielHosseini",
        "email": "daniel.hosseini1@gmail.com",
        "date": "2016-03-24T13:39:43Z"
      },
      "message": "Added README file",
      "tree":  {
        "sha": "b5f0d0b647fcb67a7bffa1b1441e785384316f90",
        "url": "https://api.github.com/repos/DanielHosseini/EDA397_2016_Group9/git/trees/b5f0d0b647fcb67a7bffa1b1441e785384316f90"
      },
      "url": "https://api.github.com/repos/DanielHosseini/EDA397_2016_Group9/git/commits/83a238e0ba33366f650a6ad1dda266fe77d26d57",
      "comment_count": 0
    },
    "url": "https://api.github.com/repos/DanielHosseini/EDA397_2016_Group9/commits/83a238e0ba33366f650a6ad1dda266fe77d26d57",
    "html_url": "https://github.com/DanielHosseini/EDA397_2016_Group9/commit/83a238e0ba33366f650a6ad1dda266fe77d26d57",
    "comments_url": "https://api.github.com/repos/DanielHosseini/EDA397_2016_Group9/commits/83a238e0ba33366f650a6ad1dda266fe77d26d57/comments",
    "author":  {
      "login": "DanielHosseini",
      "id": 2711831,
      "avatar_url": "https://avatars.githubusercontent.com/u/2711831?v=3",
      "gravatar_id": "",
      "url": "https://api.github.com/users/DanielHosseini",
      "html_url": "https://github.com/DanielHosseini",
      "followers_url": "https://api.github.com/users/DanielHosseini/followers",
      "following_url": "https://api.github.com/users/DanielHosseini/following{/other_user}",
      "gists_url": "https://api.github.com/users/DanielHosseini/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/DanielHosseini/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/DanielHosseini/subscriptions",
      "organizations_url": "https://api.github.com/users/DanielHosseini/orgs",
      "repos_url": "https://api.github.com/users/DanielHosseini/repos",
      "events_url": "https://api.github.com/users/DanielHosseini/events{/privacy}",
      "received_events_url": "https://api.github.com/users/DanielHosseini/received_events",
      "type": "User",
      "site_admin": false
    },
    "committer":  {
      "login": "DanielHosseini",
      "id": 2711831,
      "avatar_url": "https://avatars.githubusercontent.com/u/2711831?v=3",
      "gravatar_id": "",
      "url": "https://api.github.com/users/DanielHosseini",
      "html_url": "https://github.com/DanielHosseini",
      "followers_url": "https://api.github.com/users/DanielHosseini/followers",
      "following_url": "https://api.github.com/users/DanielHosseini/following{/other_user}",
      "gists_url": "https://api.github.com/users/DanielHosseini/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/DanielHosseini/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/DanielHosseini/subscriptions",
      "organizations_url": "https://api.github.com/users/DanielHosseini/orgs",
      "repos_url": "https://api.github.com/users/DanielHosseini/repos",
      "events_url": "https://api.github.com/users/DanielHosseini/events{/privacy}",
      "received_events_url": "https://api.github.com/users/DanielHosseini/received_events",
      "type": "User",
      "site_admin": false
    },
    "parents":  [
       {
        "sha": "09470dcee3c688145732f235f26e32f55cc2e834",
        "url": "https://api.github.com/repos/DanielHosseini/EDA397_2016_Group9/commits/09470dcee3c688145732f235f26e32f55cc2e834",
        "html_url": "https://github.com/DanielHosseini/EDA397_2016_Group9/commit/09470dcee3c688145732f235f26e32f55cc2e834"
      }
    ]
  }
 */