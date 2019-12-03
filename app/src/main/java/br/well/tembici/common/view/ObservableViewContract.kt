package br.well.tembici.common.view

interface ObservableViewContract<LISTENER_TYPE: BaseListener>: RootViewContract {
    fun registerListener(listener: LISTENER_TYPE)
    fun unregisterListener(listener: LISTENER_TYPE)
}