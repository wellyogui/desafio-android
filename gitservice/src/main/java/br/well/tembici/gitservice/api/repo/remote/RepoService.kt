package br.well.tembici.gitservice.api.repo.remote

import br.well.tembici.gitservice.api.model.Repository
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Single

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
interface RepoService {

    @GET("search/repositories?q=language:Java&sort=stars&page={page}")
    fun searchRepositories(@Path("page") page: Int): Single<Repository>
}