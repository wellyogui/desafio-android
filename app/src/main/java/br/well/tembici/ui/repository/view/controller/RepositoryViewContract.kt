package br.well.tembici.ui.repository.view.controller

import br.well.tembici.common.view.BaseListener
import br.well.tembici.common.view.ObservableViewContract

interface RepositoryViewContract: ObservableViewContract<RepositoryViewContract.Listener> {
    interface Listener: BaseListener {

    }
}