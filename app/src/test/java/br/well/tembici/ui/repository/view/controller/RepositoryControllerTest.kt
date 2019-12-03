package br.well.tembici.ui.repository.view.controller

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import br.well.tembici.common.ImmediateSchedulerProvider
import br.well.tembici.gitservice.api.repo.RepoDataSource
import br.well.tembici.ui.repository.usecase.RepositoryUseCase
import com.nhaarman.mockitokotlin2.mock
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

    //region Constants

    //endregion Constants

    //region Helper Fields
    private val repositoryDataSourceMock = mock<RepoDataSource>()
    private val viewContractMock = mock<RepositoryViewContract>()
    //endregion Helper Fields

    lateinit var SUT: RepositoryController

    @Before
    fun setup() {
        val lifecycleMock = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycleMock.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        val useCase = RepositoryUseCase(repositoryDataSourceMock, ImmediateSchedulerProvider())
        SUT = RepositoryController(useCase,lifecycleMock)
        SUT.onCreate(viewContractMock)
    }

    @Test
    fun onStart_fetchRepositories_Success() {
        //Arrange
        `when`(repositoryDataSourceMock.repositories(1)).thenReturn(Single.just(mock()))
        //Act
        SUT.onStart()
        //Assert
        (viewContractMock).showLoading()
        (viewContractMock).bindRepositories()
        (viewContractMock).hideLoading()
    }
    //region Helper Methods

    //endregion Helper Methods

    //region Helper Classes

    //endregion Helper Classes
}