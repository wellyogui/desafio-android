package br.well.tembici.ui.repository.view.controller

import br.well.tembici.common.view.ObservableViewContract
import br.well.tembici.common.view.base.BaseListener
import br.well.tembici.gitservice.api.model.Repository

interface RepositoryViewContract: ObservableViewContract<RepositoryViewContract.Listener> {
    fun showLoading()
    fun bindRepositories(repositories: Repository)
    fun hideLoading()
    fun showMessageError(message: String)
    fun showListLoad()
    fun hideListLoad()

    interface Listener: BaseListener {
        fun loadNextPage()
    }
}