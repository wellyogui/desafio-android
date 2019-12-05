package br.well.tembici.ui.repository.view.controller

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import br.well.tembici.common.ScreenNavigator
import br.well.tembici.common.provider.ImmediateSchedulerProvider
import br.well.tembici.gitservice.api.repo.ProjectDataSource
import br.well.tembici.ui.repository.usecase.RepositoryUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import rx.Single

class RepositoryControllerTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    companion object {
        const val USER_NAME = "wellyogui"
        const val REPOSITORY_NAME = "desafio-android"
        const val ERROR_MESSAGE = "Erro ao carregar a lista"

    }

    private val repositoryDataSourceMock = mock<ProjectDataSource>()
    private val viewContractMock = mock<RepositoryViewContract>()
    private val screenNavigatorMock = mock<ScreenNavigator>()

    lateinit var SUT: RepositoryController

    @Before
    fun setup() {
        val lifecycleMock = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycleMock.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        val useCase = RepositoryUseCase(repositoryDataSourceMock, ImmediateSchedulerProvider())
        SUT = RepositoryController(useCase, lifecycleMock, screenNavigatorMock)
        SUT.onCreate(viewContractMock)
    }

    @Test
    fun onStart_fetchRepositories_success() {
        //Arrange
        `when`(repositoryDataSourceMock.repositories(1)).thenReturn(Single.just(mock()))
        //Act
        SUT.onStart()
        //Assert
        verify(viewContractMock).showLoading()
        verify(viewContractMock).bindRepositories(mock())
        verify(viewContractMock).hideLoading()
    }

    @Test
    fun onStart_fetchRepositories_failure() {
        //Arrange
        `when`(repositoryDataSourceMock.repositories(1)).thenReturn(Single.error(RuntimeException(ERROR_MESSAGE)))
        //Act
        SUT.onStart()
        //Assert
        verify(viewContractMock).showLoading()
        verify(viewContractMock).showMessageError(ERROR_MESSAGE)
        verify(viewContractMock).hideLoading()
    }

    @Test
    fun onScrolled_fetchMoreRepositories_success() {
        //Arrange
        `when`(repositoryDataSourceMock.repositories(2)).thenReturn(Single.just(mock()))
        //Act
        SUT.loadNextPage()
        //Assert
        verify(viewContractMock).showListLoad()
        verify(viewContractMock).bindRepositories(mock())
        verify(viewContractMock).hideListLoad()
    }

    @Test
    fun onScrolled_fetchMoreRepositories_failure() {
        //Arrange
        `when`(repositoryDataSourceMock.repositories(2)).thenReturn(Single.error(java.lang.RuntimeException(ERROR_MESSAGE)))
        //Act
        SUT.loadNextPage()
        //Assert
        verify(viewContractMock).showListLoad()
        verify(viewContractMock).showMessageError(ERROR_MESSAGE)
        verify(viewContractMock).hideListLoad()
    }

    @Test
    fun onRepositoryClicked_toPullRequestScreen() {
        //Act
        SUT.toPullRequests(USER_NAME, REPOSITORY_NAME)
        //Assert
        verify(screenNavigatorMock).toPullRequests(USER_NAME, REPOSITORY_NAME)
    }
}