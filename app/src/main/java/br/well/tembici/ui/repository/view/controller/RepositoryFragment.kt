package br.well.tembici.ui.repository.view.controller

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.well.tembici.common.AppProvider
import br.well.tembici.common.ControllerFactory
import br.well.tembici.common.FragmentLayoutProvider
import br.well.tembici.common.view.BaseFragment

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
class RepositoryFragment :
    BaseFragment<RepositoryViewContract, RepositoryController, AppProvider, ControllerFactory>() {
    override val viewContract: RepositoryViewContract by lazy {
        appProvider.viewFactory.provideRepositoryView(view?.parent as ViewGroup?)
    }
    override val controller: RepositoryController by lazy {
        controllerFactory.provideRepositoryController()
    }
    override val appProvider: AppProvider by lazy {
        AppProvider(
            activity as AppCompatActivity,
            (requireActivity() as FragmentLayoutProvider).fragmentFrame()
        )
    }
    override val controllerFactory: ControllerFactory by lazy {
        ControllerFactory(appProvider.useCaseFactory, appProvider, this)
    }

}