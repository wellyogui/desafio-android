package br.well.tembici.ui.pullrequest.view.controller

import androidx.lifecycle.Lifecycle
import br.well.tembici.common.ScreenNavigator
import br.well.tembici.common.util.ResourceState
import br.well.tembici.common.view.LiveController
import br.well.tembici.ui.pullrequest.usecase.PullRequestUseCase

class PullRequestController(
    private val useCase: PullRequestUseCase,
    private val lifecycle: Lifecycle,
    private val userName: String,
    private val repoName: String,
    private val screenNavigator: ScreenNavigator
) : LiveController<PullRequestViewContract.Listener, PullRequestViewContract>(),
    PullRequestViewContract.Listener {

    override fun onCreate(view: PullRequestViewContract) {
        super.onCreate(view)
        viewContract.registerListener(this)
        viewContract.initToolbar(repoName)
        useCase.fetchPullRequest(userName, repoName)
    }

    override fun onResume() {
        viewContract.registerListener(this)
    }

    override fun observeLive() {
        useCase.pullRequestLiveData.observe({ lifecycle }, {
            when (it.status) {
                ResourceState.LOADING -> {
                    when (it.loading) {
                        true -> viewContract.showLoading()
                        false -> viewContract.hideLoading()
                    }
                }
                ResourceState.SUCCESS -> {
                    when (it.data.isNullOrEmpty()) {
                        true -> viewContract.showNoPullRequestMessage()
                        false -> viewContract.bindPullRequests(it.data)
                    }
                }
                ResourceState.ERROR -> {
                    viewContract.showMessageError(it.message!!, it.callback!!)
                }
            }
        })
    }

    override fun initViews() {
        observeLive()
    }

    override fun onStop() {
        viewContract.unregisterListener(this)
    }

    override fun toPullRequestDetails(url: String) {
        screenNavigator.toPullRequestDetails(url)
    }

    override fun onBackPressed() {
        viewContract.onBackPressed()
    }

}
