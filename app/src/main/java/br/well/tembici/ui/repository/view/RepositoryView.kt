package br.well.tembici.ui.repository.view

import android.view.LayoutInflater
import android.view.ViewGroup
import br.well.tembici.R
import br.well.tembici.common.view.ObservableView
import br.well.tembici.ui.repository.view.controller.RepositoryViewContract

class RepositoryView(inflater: LayoutInflater, parent: ViewGroup?): ObservableView<RepositoryViewContract.Listener>(inflater, parent, R.layout.fragment_repository),
    RepositoryViewContract {
    override fun showLoading() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bindRepositories() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}