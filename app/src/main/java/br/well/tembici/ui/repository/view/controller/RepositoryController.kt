package br.well.tembici.ui.repository.view.controller

import androidx.lifecycle.Lifecycle
import br.well.tembici.common.util.ResourceState
import br.well.tembici.common.view.LiveController
import br.well.tembici.ui.repository.usecase.RepositoryUseCase

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
class RepositoryController(private val repositoryUseCase: RepositoryUseCase, val lifecycle: Lifecycle) :
    LiveController<RepositoryViewContract.Listener, RepositoryViewContract>() {

    fun onStart() {
        repositoryUseCase.fetchRepositories()
    }

    override fun onStop() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

                }// TODO()
            }
        })
    }

    override fun initViews() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}