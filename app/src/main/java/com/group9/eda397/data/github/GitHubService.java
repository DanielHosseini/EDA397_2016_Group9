package com.group9.eda397.data.github;

import com.group9.eda397.model.GitHubCommitItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Retrofit interface for GitHub restful APi
 *
 * @author palmithor
 * @since 24/03/16.
 */
public interface GitHubService {

    String PATH_PARAM_OWNER = "owner";
    String PATH_PARAM_REPO = "repo";

    @GET("/repos/{" + PATH_PARAM_OWNER + "}/{" + PATH_PARAM_REPO + "}/commits")
    Call<List<GitHubCommitItem>> getGitHubCommits(@Path(PATH_PARAM_OWNER) final String owner,
                                                  @Path(PATH_PARAM_REPO) final String repository);
}
