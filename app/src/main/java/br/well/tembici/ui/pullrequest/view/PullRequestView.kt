package br.well.tembici.ui.pullrequest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import br.well.tembici.R
import br.well.tembici.common.ext.gone
import br.well.tembici.common.ext.visible
import br.well.tembici.common.view.ObservableView
import br.well.tembici.gitservice.api.model.PullRequest
import br.well.tembici.ui.pullrequest.view.adapter.PullRequestsAdapter
import br.well.tembici.ui.pullrequest.view.adapter.model.PullRequestItemAdapter
import br.well.tembici.ui.pullrequest.view.controller.PullRequestViewContract
import kotlinx.android.synthetic.main.fragment_pull_request.view.*
import kotlinx.android.synthetic.main.fragment_repository.view.loadingView

/**
 * Created by well_ on 04/12/2019 for tembici-challenge.
 */
class PullRequestView(inflater: LayoutInflater, parent: ViewGroup?) :
        ObservableView<PullRequestViewContract.Listener>(inflater, parent, R.layout.fragment_pull_request),
        PullRequestViewContract, PullRequestsAdapter.Listener {

    private val pullRequestAdapter: PullRequestsAdapter by lazy {
        PullRequestsAdapter(arrayListOf(), this)
    }

    override fun showLoading() {
        rootView.loadingView.visible()
        rootView.pullRequestsView.gone()
    }

    override fun bindPullRequests(pullRequests: List<PullRequest>) {
        val pullRequestItemsAdapter = arrayListOf<PullRequestItemAdapter>()

        pullRequests.forEach {
            val pullrequest = PullRequestItemAdapter(
                it.user.userName,
                it.user.userImage,
                it.title,
                it.description,
                it.created_at,
                it.html_url
            )

            pullRequestItemsAdapter.add(pullrequest)
        }

        pullRequestAdapter.addAll(pullRequestItemsAdapter)
        with(rootView.pullRequestsView) {
            setHasFixedSize(true)
            if (adapter == null) {
                adapter = pullRequestAdapter
            }
            visible()
        }
    }

    override fun hideLoading() {
        rootView.loadingView.gone()
    }

    override fun showMessageError(message: String?) {

    }

    override fun onPullRequestListener(url: String) {
        listeners.forEach {
            it.toPullRequestDetails(url)
        }
    }

}