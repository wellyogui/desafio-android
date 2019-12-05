package br.well.tembici.ui.pullrequest.view.controller

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import br.well.tembici.common.ScreenNavigator
import br.well.tembici.common.provider.ImmediateSchedulerProvider
import br.well.tembici.gitservice.api.model.PullRequest
import br.well.tembici.gitservice.api.model.User
import br.well.tembici.gitservice.api.repo.ProjectDataSource
import br.well.tembici.ui.pullrequest.usecase.PullRequestUseCase
import com.nhaarman.mockitokotlin2.argumentCaptor
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

    private val repoDataSourceMock = mock<ProjectDataSource>()
    private val viewContractMock = mock<PullRequestViewContract>()
    private val screenNavigatorMock = mock<ScreenNavigator>()

    val pullRequestMock = PullRequest("", "", 0, 1, "", "", User("", ""))
    lateinit var SUT: PullRequestController

    @Before
    fun setup() {
        val lifecycleMock = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycleMock.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        val useCase = PullRequestUseCase(repoDataSourceMock, ImmediateSchedulerProvider())
        SUT = PullRequestController(useCase, lifecycleMock, USER_NAME, REPOSITORY_NAME, screenNavigatorMock)
    }

    @Test
    fun onStart_fetchPullRequests_success() {
        //Arrange
        `when`(repoDataSourceMock.pulls(USER_NAME, REPOSITORY_NAME)).thenReturn(Single.just(arrayListOf(pullRequestMock)))
        //Act
        SUT.onCreate(viewContractMock)
        //Assert
        verify(viewContractMock).showLoading()
        verify(viewContractMock).bindPullRequests(arrayListOf(pullRequestMock))
        verify(viewContractMock).hideLoading()
    }

    @Test
    fun onStart_fetchPullRequests_failure() {
        //Arrange
        val captor = argumentCaptor<() -> Unit>()
        `when`(repoDataSourceMock.pulls(USER_NAME, REPOSITORY_NAME)).thenReturn(Single.error(RuntimeException(ERROR_MESSAGE)))
        //Act
        SUT.onCreate(viewContractMock)
        //Assert
        verify(viewContractMock).showLoading()
        verify(viewContractMock).showMessageError(ERROR_MESSAGE, captor.capture())
        verify(viewContractMock).hideLoading()
    }

    @Test
    fun onStart_fetchPullRequests_emptyPullRequest_success() {
        //Arrange
        `when`(repoDataSourceMock.pulls(USER_NAME, REPOSITORY_NAME)).thenReturn(Single.just(null))
        //Act
        SUT.onCreate(viewContractMock)
        //Assert
        verify(viewContractMock).showLoading()
        verify(viewContractMock).showNoPullRequestMessage()
        verify(viewContractMock).hideLoading()
    }

    @Test
    fun onPullRequestClicked_openPullRequestDetail() {
        //Act
        SUT.toPullRequestDetails("")
        //Assert
        verify(screenNavigatorMock).toPullRequestDetails("")
    }
}