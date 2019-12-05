package br.well.tembici.ui.repository.view.controller

import androidx.lifecycle.Lifecycle
import br.well.tembici.common.ScreenNavigator
import br.well.tembici.common.util.ResourceState
import br.well.tembici.common.view.LiveController
import br.well.tembici.ui.repository.usecase.RepositoryUseCase

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
class RepositoryController(
    private val repositoryUseCase: RepositoryUseCase,
    private val lifecycle: Lifecycle,
    private val screenNavigator: ScreenNavigator
) :
    LiveController<RepositoryViewContract.Listener, RepositoryViewContract>(), RepositoryViewContract.Listener {

    var currentPage: Int = 1

    override fun onCreate(view: RepositoryViewContract) {
        super.onCreate(view)
        viewContract.registerListener(this)
    }

    fun onStart() {
        repositoryUseCase.fetchRepositories(currentPage)
    }

    override fun onResume() {
        viewContract.registerListener(this)
    }

    override fun onStop() {
        viewContract.unregisterListener(this)
    }

    override fun observeLive() {
        repositoryUseCase.repositoryLiveData.observe({lifecycle}, {
            when(it.status) {
                ResourceState.LOADING -> {
                    if(currentPage == 1){
                        when(it.loading) {
                            true -> viewContract.showLoading()
                            false -> viewContract.hideLoading()
                        }
                    } else {
                        when(it.loading){
                            true -> viewContract.showListLoad()
                            false -> viewContract.hideListLoad()
                        }
                    }
                }
                ResourceState.SUCCESS -> {
                    viewContract.bindRepositories(it.data!!)
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

    override fun loadNextPage() {
        currentPage += 1
        repositoryUseCase.fetchRepositories(currentPage)
    }

    override fun toPullRequests(userName: String, repoName: String) {
        screenNavigator.toPullRequests(userName, repoName)
    }
}