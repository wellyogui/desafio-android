package br.well.tembici.gitservice.api

import br.well.tembici.gitservice.api.repo.RepoRepository
import br.well.tembici.gitservice.api.repo.remote.RepoApi

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
object DataSourceFactory {
    fun provideRepositoryDataSource() = RepoRepository.getInstance(RepoApi)
}