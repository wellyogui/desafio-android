package br.well.tembici.ui.repository.view

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import br.well.tembici.R
import br.well.tembici.common.view.ObservableView
import br.well.tembici.ui.repository.view.controller.RepositoryViewContract
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_repository.view.*

class RepositoryView(inflater: LayoutInflater, parent: ViewGroup?): ObservableView<RepositoryViewContract.Listener>(inflater, parent, R.layout.fragment_repository),
    RepositoryViewContract {
    override fun showLoading() {
        rootView.loadingView.visibility = VISIBLE
    }

    override fun bindRepositories() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        rootView.loadingView.visibility = GONE
    }

    override fun showMessageError(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
    }

}