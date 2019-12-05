package br.well.tembici.ui.pullrequest.view.controller

import br.well.tembici.common.view.ObservableViewContract
import br.well.tembici.common.view.base.BaseListener
import br.well.tembici.gitservice.api.model.PullRequest

interface PullRequestViewContract : ObservableViewContract<PullRequestViewContract.Listener> {

    interface Listener : BaseListener {
        fun toPullRequestDetails(url: String)
        fun onBackPressed()
    }

    fun showLoading()
    fun bindPullRequests(pullRequests: List<PullRequest>)
    fun hideLoading()
    fun showMessageError(message: String, action: () -> Unit)
    fun showNoPullRequestMessage()
    fun initToolbar(title: String)
    fun onBackPressed()
}
