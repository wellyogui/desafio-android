package br.well.tembici.gitservice.api.repo

import br.well.tembici.gitservice.api.model.Project
import br.well.tembici.gitservice.api.model.PullRequest
import rx.Single

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
interface ProjectDataSource {
    fun repositories(page: Int): Single<Project>
    fun pulls(owner: String, repo: String): Single<List<PullRequest>>
}