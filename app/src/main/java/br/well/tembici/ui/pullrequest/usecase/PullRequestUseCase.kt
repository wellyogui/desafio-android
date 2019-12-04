package br.well.tembici.ui.pullrequest.usecase

import androidx.lifecycle.MutableLiveData
import br.well.tembici.common.provider.BaseSchedulerProvider
import br.well.tembici.common.util.Resource
import br.well.tembici.gitservice.api.model.PullRequest
import br.well.tembici.gitservice.api.repo.RepoDataSource

class PullRequestUseCase(private val repoDataSource: RepoDataSource,
                         private val schedulerProvider: BaseSchedulerProvider) {

    val pullRequestLiveData = MutableLiveData<Resource<List<PullRequest>>>()

    fun fetchPullRequest(userName: String, repoName: String) {
        repoDataSource.pulls(userName, repoName)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { pullRequestLiveData.value = Resource.loading(true) }
                .subscribe({
                    pullRequestLiveData.value = Resource.loading(false)
                    pullRequestLiveData.value = Resource.success(it)
                }, {
                    pullRequestLiveData.value = Resource.loading(false)
                    pullRequestLiveData.value = Resource.error(it.message) {fetchPullRequest(userName, repoName)}

                })
    }

}
