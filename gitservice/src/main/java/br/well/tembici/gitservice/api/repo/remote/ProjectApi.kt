package br.well.tembici.gitservice.api.repo.remote

import br.well.tembici.gitservice.api.GitApiModule
import br.well.tembici.gitservice.api.model.PullRequest
import br.well.tembici.gitservice.api.model.Project
import br.well.tembici.gitservice.api.repo.ProjectDataSource
import rx.Single
import timber.log.Timber

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
object ProjectApi : ProjectDataSource {

    private val repoService by lazy {
        GitApiModule.retrofit.create(ProjectService::class.java)
    }

    override fun repositories(page: Int): Single<Project> {
        val language = "Java"
        val sort = "stars"
        return repoService.repositories(language, sort, page).doOnError {
            Timber.e(it, "repositories: ${it.message}")
        }
    }

    override fun pulls(owner: String, repo: String): Single<List<PullRequest>> {
        return repoService.pulls(owner, repo).doOnError {
            Timber.e(it, "pulls: ${it.message}")
        }
    }

}