package br.well.tembici.ui.pullrequest.view.controller

import androidx.lifecycle.Lifecycle
import br.well.tembici.common.util.ResourceState
import br.well.tembici.common.view.LiveController
import br.well.tembici.ui.pullrequest.usecase.PullRequestUseCase

class PullRequestController(
    private val useCase: PullRequestUseCase,
    private val lifecycle: Lifecycle,
    private val userName: String,
    private val repoName: String
) :  LiveController<PullRequestViewContract.Listener, PullRequestViewContract>(), PullRequestViewContract.Listener {

    override fun onCreate(view: PullRequestViewContract) {
        super.onCreate(view)
        onStart()
    }

    fun onStart() {
        viewContract.registerListener(this)
        useCase.fetchPullRequest(userName, repoName)
    }

    override fun observeLive() {
        useCase.pullRequestLiveData.observe({lifecycle}, {
            when(it.status){
                ResourceState.LOADING -> {when(it.loading) {
                    true -> viewContract.showLoading()
                    false -> viewContract.hideLoading()
                }}
                ResourceState.SUCCESS -> {
                    viewContract.bindPullRequests(it.data!!)
                }
                ResourceState.ERROR -> {
                    viewContract.showMessageError(it.message)
                }
            }
        })
    }

    override fun initViews() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
