package br.well.tembici.common.factory

import br.well.tembici.common.provider.BaseSchedulerProvider
import br.well.tembici.gitservice.api.DataSourceFactory
import br.well.tembici.ui.repository.usecase.RepositoryUseCase

class UseCaseFactory(private val schedulerProvider: BaseSchedulerProvider) {
    fun provideRepositoryUseCase(): RepositoryUseCase = RepositoryUseCase(DataSourceFactory.provideRepositoryDataSource(), schedulerProvider)

}
