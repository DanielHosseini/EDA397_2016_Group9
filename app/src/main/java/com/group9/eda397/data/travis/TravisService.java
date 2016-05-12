package com.group9.eda397.data.travis;

import com.group9.eda397.model.TravisBuild;
import com.group9.eda397.model.TravisBuildDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Retrofit interface for Travis CI REST API
 *
 * @author palmithor
 * @since 24/03/16.
 */
public interface TravisService {

    String PATH_PARAM_OWNER = "owner";
    String PATH_PARAM_REPO = "repo";
    String PATH_PARAM_ID = "id";

    @GET("/repos/{" + PATH_PARAM_OWNER + "}/{" + PATH_PARAM_REPO + "}/builds")
    Call<List<TravisBuild>> getBuilds(@Path(PATH_PARAM_OWNER) final String owner,
                                      @Path(PATH_PARAM_REPO) final String repository);

    @GET("/repos/{" + PATH_PARAM_OWNER + "}/{" + PATH_PARAM_REPO + "}/builds/{" + PATH_PARAM_ID + "}")
    Call<TravisBuildDetails> getBuildDetails(@Path(PATH_PARAM_OWNER) final String owner,
                                             @Path(PATH_PARAM_REPO) final String repository,
                                             @Path(PATH_PARAM_ID) final Long id);
}
