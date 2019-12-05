package br.well.tembici.gitservice.api.repo

import br.well.tembici.gitservice.api.model.PullRequest
import br.well.tembici.gitservice.api.model.Repository
import rx.Single
import timber.log.Timber

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
class ProjectRepository(private val projectDataSource: ProjectDataSource) : ProjectDataSource {

    companion object {
        private var INSTANCE: ProjectRepository? = null

        @JvmStatic
        fun getInstance(remoteDataSource: ProjectDataSource) = INSTANCE
            ?: synchronized(ProjectRepository::class.java) {
                INSTANCE ?: ProjectRepository(remoteDataSource)
                    .also { INSTANCE = it }
            }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun repositories(page: Int): Single<Repository> {
        return projectDataSource.repositories(page).doOnError {
            Timber.e(it, "repositories: ${it.message}")
        }
    }

    override fun pulls(owner: String, repo: String): Single<List<PullRequest>> {
        return projectDataSource.pulls(owner, repo).doOnError {
            Timber.e(it, "pulls: ${it.message}")
        }
    }

}