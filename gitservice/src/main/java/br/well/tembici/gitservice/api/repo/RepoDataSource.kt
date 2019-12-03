package br.well.tembici.gitservice.api.repo

import br.well.tembici.gitservice.api.model.Repository
import rx.Single

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */
interface RepoDataSource  {
  fun searchRepositories(page: Int): Single<Repository>
}