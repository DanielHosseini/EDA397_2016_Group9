package com.group9.eda397.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author palmithor
 * @since 24/03/16.
 */
public class GitHubCommitUser {
    private final String name;
    private final String email;
    private final Date date;

    public GitHubCommitUser() {
        this.name = null;
        this.email = null;
        this.date = null;
    }

    public GitHubCommitUser(final String name, final String email, final Date date) {
        this.name = name;
        this.email = email;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getDate() {
        return date;
    }
}
