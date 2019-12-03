package br.well.tembici.ui.repository.view

import android.view.LayoutInflater
import android.view.ViewGroup
import br.well.tembici.R
import br.well.tembici.common.view.ObservableView
import br.well.tembici.ui.repository.view.controller.RepositoryViewContract

class RepositoryView(inflater: LayoutInflater, parent: ViewGroup?): ObservableView<RepositoryViewContract.Listener>(inflater, parent, R.layout.fragment_repository),
    RepositoryViewContract {

}