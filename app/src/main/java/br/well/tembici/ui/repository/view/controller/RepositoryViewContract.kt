package br.well.tembici.ui.repository.view.controller

import br.well.tembici.common.view.base.BaseListener
import br.well.tembici.common.view.ObservableViewContract

interface RepositoryViewContract: ObservableViewContract<RepositoryViewContract.Listener> {
    fun showLoading()
    fun bindRepositories()
    fun hideLoading()
    fun showMessageError(message: String)

    interface Listener: BaseListener {

    }
}