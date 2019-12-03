package br.well.tembici.common.view

import br.well.tembici.common.view.base.BaseController
import br.well.tembici.common.view.base.BaseListener


abstract class LiveController<VIEW_LISTENER: BaseListener, VIEW_CONTRACT: ObservableViewContract<VIEW_LISTENER>>
    : BaseController<VIEW_CONTRACT>() {
    abstract fun observeLive()

    override fun onCreate(view: VIEW_CONTRACT) {
        super.onCreate(view)
        observeLive()
    }
}