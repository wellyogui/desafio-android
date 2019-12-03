package br.well.tembici.common.view.base

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import br.well.tembici.common.view.RootViewContract

abstract class BaseView(inflater: LayoutInflater, parent: ViewGroup?, @LayoutRes layoutId: Int) :
    RootViewContract {
    override val rootView: View by lazy { inflater.inflate(layoutId, parent, false) }
    val activity by lazy { rootView.context as Activity }
}