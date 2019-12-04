package br.well.tembici.common.factory

import androidx.lifecycle.LifecycleOwner
import br.well.tembici.common.provider.AppProvider
import br.well.tembici.ui.repository.view.controller.RepositoryController

class ControllerFactory(private val useCaseFactory: UseCaseFactory,
                        private val appProvider: AppProvider,
                        private val lifecycleOwner: LifecycleOwner) {
    fun provideRepositoryController(): RepositoryController = RepositoryController(
        useCaseFactory.provideRepositoryUseCase(),
        lifecycleOwner.lifecycle, appProvider.screenNavigator
    )

    fun providePullRequestController(userName: String, repoName: String): RepositoryController {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
