package br.well.tembici.common.view

import androidx.annotation.CallSuper

abstract class BaseController<VIEW_CONTRACT : RootViewContract> {
    lateinit var viewContract: VIEW_CONTRACT

    abstract fun initViews()

    abstract fun onStop()

    @CallSuper
    open fun onCreate(view: VIEW_CONTRACT) {
        this.viewContract = view
    }
}