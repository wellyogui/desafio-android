package br.well.tembici.gitservice.api

import br.well.tembici.gitservice.api.repo.ProjectRepository
import br.well.tembici.gitservice.api.repo.remote.ProjectApi

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
object DataSourceFactory {
    fun provideProjectDataSource() = ProjectRepository.getInstance(ProjectApi)
}