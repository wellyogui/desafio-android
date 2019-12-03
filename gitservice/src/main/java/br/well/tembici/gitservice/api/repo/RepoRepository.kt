package br.well.tembici.gitservice.api.repo

import br.well.tembici.gitservice.api.model.PullRequest
import br.well.tembici.gitservice.api.model.Repository
import rx.Single
import timber.log.Timber

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
class RepoRepository(private val repoDataSource: RepoDataSource) : RepoDataSource {

    companion object {
        private var INSTANCE: RepoRepository? = null

        @JvmStatic
        fun getInstance(remoteDataSource: RepoDataSource) = INSTANCE
            ?: synchronized(RepoRepository::class.java) {
                INSTANCE ?: RepoRepository(remoteDataSource)
                    .also { INSTANCE = it }
            }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun repositories(page: Int): Single<Repository> {
        return repoDataSource.repositories(page).doOnError {
            Timber.e(it, "repositories: ${it.message}")
        }
    }

    override fun pulls(owner: String, repo: String): Single<PullRequest> {
        return repoDataSource.pulls(owner, repo).doOnError {
            Timber.e(it, "pulls: ${it.message}")
        }
    }

}