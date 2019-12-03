package br.well.tembici.common.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes

abstract class ObservableView<LISTENER_TYPE : BaseListener>(inflater: LayoutInflater,
                                                            parent: ViewGroup?,
                                                            @LayoutRes layoutId: Int)
    : BaseView(inflater, parent, layoutId), ObservableViewContract<LISTENER_TYPE> {
    val listeners = HashSet<LISTENER_TYPE>()

    override fun registerListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }
}