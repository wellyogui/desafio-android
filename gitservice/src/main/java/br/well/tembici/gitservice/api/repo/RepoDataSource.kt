package br.well.tembici.gitservice.api.repo

import br.well.tembici.gitservice.api.model.PullRequest
import br.well.tembici.gitservice.api.model.Repository
import rx.Single

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
interface RepoDataSource {
    fun repositories(page: Int): Single<Repository>
    fun pulls(owner: String, repo: String): Single<List<PullRequest>>
}