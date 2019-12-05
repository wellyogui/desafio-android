package br.well.tembici.ui.repository.view.controller

import br.well.tembici.common.view.ObservableViewContract
import br.well.tembici.common.view.base.BaseListener
import br.well.tembici.gitservice.api.model.Project

interface RepositoryViewContract : ObservableViewContract<RepositoryViewContract.Listener> {
    interface Listener : BaseListener {
        fun loadNextPage()
        fun toPullRequests(userName: String, repoName: String)
    }

    fun showLoading()
    fun bindRepositories(repositories: Project)
    fun hideLoading()
    fun showMessageError(message: String, action: () -> Unit)
    fun showListLoad()
    fun hideListLoad()
}