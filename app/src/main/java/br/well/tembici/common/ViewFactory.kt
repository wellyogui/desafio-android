package br.well.tembici.common

import android.view.LayoutInflater
import android.view.ViewGroup
import br.well.tembici.ui.repository.view.RepositoryView
import br.well.tembici.ui.repository.view.controller.RepositoryViewContract

class ViewFactory(private val layoutInflater: LayoutInflater) {
    fun provideRepositoryView(parent: ViewGroup?): RepositoryViewContract = RepositoryView(layoutInflater, parent)
}
