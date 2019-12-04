package br.well.tembici.ui.pullrequest.view.controller

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import br.well.tembici.common.provider.ImmediateSchedulerProvider
import br.well.tembici.gitservice.api.repo.RepoDataSource
import br.well.tembici.ui.pullrequest.controller.PullRequestController
import br.well.tembici.ui.pullrequest.controller.PullRequestViewContract
import br.well.tembici.ui.pullrequest.usecase.PullRequestUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import rx.Single

class PullRequestControllerTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    companion object {
        const val USER_NAME = "wellyogui"
        const val REPOSITORY_NAME = "desafio-android"
        const val ERROR_MESSAGE = "Erro ao carregar a lista"
    }

    private val repoDataSourceMock = mock<RepoDataSource>()
    private val viewContractMock = mock<PullRequestViewContract>()
    lateinit var SUT: PullRequestController

    @Before
    fun setup() {
        val lifecycleMock = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycleMock.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        val useCase = PullRequestUseCase(repoDataSourceMock, ImmediateSchedulerProvider())
        SUT = PullRequestController(useCase, lifecycleMock, REPOSITORY_NAME, USER_NAME)
        SUT.onCreate(viewContractMock)
    }

    @Test
    fun onStart_fetchPullRequests_success() {
        //Arrange
        `when`(repoDataSourceMock.pulls(USER_NAME, REPOSITORY_NAME)).thenReturn(Single.just(mock()))
        //Act
        SUT.onStart()
        //Assert
        verify(viewContractMock).showLoading()
        verify(viewContractMock).bindPullRequests(mock())
        verify(viewContractMock).hideLoading()
    }

    @Test
    fun onStart_fetchPullRequests_failure() {
        //Arrange
        `when`(repoDataSourceMock.pulls(USER_NAME, REPOSITORY_NAME)).thenReturn(Single.just(mock()))
        //Act
        SUT.onStart()
        //Assert
        verify(viewContractMock).showLoading()
        verify(viewContractMock).showMessageError(ERROR_MESSAGE)
        verify(viewContractMock).hideLoading()
    }
}