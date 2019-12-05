package br.well.tembici.ui.repository.view

import android.view.LayoutInflater
import android.view.ViewGroup
import br.well.tembici.R
import br.well.tembici.common.ext.gone
import br.well.tembici.common.ext.visible
import br.well.tembici.common.view.ObservableView
import br.well.tembici.gitservice.api.model.Repository
import br.well.tembici.ui.repository.view.adapter.RepositoriesAdapter
import br.well.tembici.ui.repository.view.adapter.model.RepositoryItemAdapter
import br.well.tembici.ui.repository.view.controller.RepositoryViewContract
import br.well.tembici.ui.repository.view.listener.PaginationScrolledListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_repository.view.*

class RepositoryView(inflater: LayoutInflater, parent: ViewGroup?) :
    ObservableView<RepositoryViewContract.Listener>(inflater, parent, R.layout.fragment_repository),
    RepositoryViewContract, RepositoriesAdapter.Listener {

    var isLoading = false
    var isLastPage = false

    private val repositoryAdapter by lazy {
        RepositoriesAdapter(arrayListOf(), this)
    }

    override fun showLoading() {
        rootView.loadingView.visible()
    }

    override fun bindRepositories(repositories: Repository) {
        val repositoryItemsAdapter = arrayListOf<RepositoryItemAdapter>()

        repositories.items.forEach {
            val repository = RepositoryItemAdapter(
                it.owner.login,
                it.owner.avatarUrl,
                it.name,
                it.description,
                it.forks_count,
                it.stargazers_count
            )

            repositoryItemsAdapter.add(repository)
        }

        repositoryAdapter.addAll(repositoryItemsAdapter)
        with(rootView.repositoriesView) {
            setHasFixedSize(true)
            if (adapter == null) {
                adapter = repositoryAdapter
            }
            visible()

            addOnScrollListener(object : PaginationScrolledListener(layoutManager!!) {
                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {
                    isLoading = true
                    listeners.forEach {
                        it.loadNextPage()
                    }
                }

                override fun isLastPage(): Boolean {
                    return isLastPage
                }

            })
        }
    }

    override fun hideLoading() {
        rootView.loadingView.gone()
    }

    override fun showMessageError(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
    }

    override fun showListLoad() {
        isLoading = true
        repositoryAdapter.showLoading()
    }

    override fun hideListLoad() {
        isLoading = false
        repositoryAdapter.hideLoading()
    }

    override fun onRepositoryClicked(userName: String, repoName: String) {
        listeners.forEach {
            it.toPullRequests(userName, repoName)
        }
    }

}