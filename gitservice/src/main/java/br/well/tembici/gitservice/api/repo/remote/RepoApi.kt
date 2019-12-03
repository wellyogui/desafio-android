package br.well.tembici.gitservice.api.repo.remote

import br.well.tembici.gitservice.api.GitApiModule
import br.well.tembici.gitservice.api.model.PullRequest
import br.well.tembici.gitservice.api.model.Repository
import br.well.tembici.gitservice.api.repo.RepoDataSource
import rx.Single
import timber.log.Timber

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
object RepoApi : RepoDataSource {

    private val repoService by lazy {
        GitApiModule.retrofit.create(RepoService::class.java)
    }

    override fun repositories(page: Int): Single<Repository> {
        return repoService.repositories(page).doOnError {
            Timber.e(it, "repositories: ${it.message}")
        }
    }

    override fun pulls(owner: String, repo: String): Single<PullRequest> {
        return repoService.pulls(owner, repo).doOnError {
            Timber.e(it, "pulls: ${it.message}")
        }
    }

}