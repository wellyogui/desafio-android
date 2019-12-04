package br.well.tembici.ui.repository.usecase

import androidx.lifecycle.MutableLiveData
import br.well.tembici.common.provider.BaseSchedulerProvider
import br.well.tembici.common.util.Resource
import br.well.tembici.gitservice.api.model.Repository
import br.well.tembici.gitservice.api.repo.RepoDataSource

class RepositoryUseCase(
    private val repoDataSource: RepoDataSource,
    private val schedulerProvider: BaseSchedulerProvider
) {

    val repositoryLiveData = MutableLiveData<Resource<Repository>>()

    fun fetchRepositories(page: Int) {
        repoDataSource.repositories(page)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { repositoryLiveData.value = Resource.loading(true) }
            .subscribe({
                repositoryLiveData.value = Resource.loading(false)
                repositoryLiveData.value = Resource.success(it)
            }, {
                repositoryLiveData.value = Resource.loading(false)
                repositoryLiveData.value = Resource.error(it.message) { fetchRepositories(page) }
            })
    }
}