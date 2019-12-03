package br.well.tembici.ui.repository.view

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import br.well.tembici.R
import br.well.tembici.common.view.ObservableView
import br.well.tembici.gitservice.api.model.Repository
import br.well.tembici.ui.repository.view.adapter.RepositoriesAdapter
import br.well.tembici.ui.repository.view.adapter.model.RepositoryItemAdapter
import br.well.tembici.ui.repository.view.controller.RepositoryViewContract
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_repository.view.*

class RepositoryView(inflater: LayoutInflater, parent: ViewGroup?): ObservableView<RepositoryViewContract.Listener>(inflater, parent, R.layout.fragment_repository),
    RepositoryViewContract, RepositoriesAdapter.Listener {

    private val repositoryAdapter by lazy {
        RepositoriesAdapter(arrayListOf(), this)
    }
    override fun showLoading() {
        rootView.loadingView.visibility = VISIBLE
    }

    override fun bindRepositories(repositories: Repository) {
        val repositoryItemAdapter =  arrayListOf<RepositoryItemAdapter>()

        repositories.items.forEach {
            val repository = RepositoryItemAdapter(
                it.owner.login,
                it.owner.avatarUrl,
                it.name,
                it.description,
                it.forks_count,
                it.stargazers_count
            )

            repositoryItemAdapter.add(repository)
        }

        repositoryAdapter.add(repositoryItemAdapter)
        with(rootView.repositoriesView) {
            setHasFixedSize(true)
            adapter = repositoryAdapter
            visibility = VISIBLE
        }
    }

    override fun hideLoading() {
        rootView.loadingView.visibility = GONE
    }

    override fun showMessageError(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
    }

    override fun onRepositoryClicked() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}