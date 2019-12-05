package br.well.tembici.common.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import br.well.tembici.ui.pullrequest.view.PullResquestView
import br.well.tembici.ui.pullrequest.view.controller.PullRequestViewContract
import br.well.tembici.ui.repository.view.RepositoryView
import br.well.tembici.ui.repository.view.controller.RepositoryViewContract

class ViewFactory(private val layoutInflater: LayoutInflater) {
    fun provideRepositoryView(parent: ViewGroup?): RepositoryViewContract = RepositoryView(layoutInflater, parent)
    fun providePullRequestView(parent: ViewGroup?): PullRequestViewContract = PullResquestView(layoutInflater, parent)
}
