package br.well.tembici.gitservice.api.repo.remote

import br.well.tembici.gitservice.api.model.PullRequest
import br.well.tembici.gitservice.api.model.Repository
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Single

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
interface RepoService {

    @GET("repos/{owner}/{repo}/pulls")
    fun pulls(@Path("owenr") owner: String, @Path("repo") repo: String): Single<List<PullRequest>>

    @GET("search/repositories")
    fun repositories(@Query("q") language: String, @Query("sort") sort:String, @Query("page") page: Int): Single<Repository>
}