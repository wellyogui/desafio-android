package br.well.tembici.ui.repository.view.controller

import androidx.lifecycle.Lifecycle
import br.well.tembici.common.util.ResourceState
import br.well.tembici.common.view.LiveController
import br.well.tembici.ui.repository.usecase.RepositoryUseCase

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
class RepositoryController(private val repositoryUseCase: RepositoryUseCase, val lifecycle: Lifecycle) :
    LiveController<RepositoryViewContract.Listener, RepositoryViewContract>(), RepositoryViewContract.Listener {

    fun onStart() {
        viewContract.registerListener(this)
        repositoryUseCase.fetchRepositories()
    }

    override fun onStop() {
        viewContract.unregisterListener(this)
    }

    override fun observeLive() {
        repositoryUseCase.repositoryLiveData.observe({lifecycle}, {
            when(it.status) {
                ResourceState.LOADING -> {
                    when(it.loading) {
                        true -> viewContract.showLoading()
                        false -> viewContract.hideLoading()
                    }
                }
                ResourceState.SUCCESS -> {
                    viewContract.bindRepositories()
                }
                ResourceState.ERROR -> {
                    viewContract.showMessageError(it.message!!)
                }
            }
        })
    }

    override fun initViews() {
        observeLive()
    }



}