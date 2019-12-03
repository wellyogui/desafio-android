package br.well.tembici.common.provider

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import br.well.tembici.common.ScreenNavigator
import br.well.tembici.common.view.base.BaseAppProvider
import br.well.tembici.common.factory.UseCaseFactory
import br.well.tembici.common.factory.ViewFactory

class AppProvider(override val activity: AppCompatActivity, @IdRes frameId: Int) : BaseAppProvider(activity) {
    val viewFactory by lazy { ViewFactory(layoutInflater) }
    val screenNavigator by lazy {
        ScreenNavigator(
            activity,
            frameId
        )
    }
    val useCaseFactory by lazy {
        UseCaseFactory(
            SchedulerProvider
        )
    }
}