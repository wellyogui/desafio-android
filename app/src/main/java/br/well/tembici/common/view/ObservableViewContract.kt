package br.well.tembici.common.view

import br.well.tembici.common.view.base.BaseListener

interface ObservableViewContract<LISTENER_TYPE: BaseListener>: RootViewContract {
    fun registerListener(listener: LISTENER_TYPE)
    fun unregisterListener(listener: LISTENER_TYPE)
}