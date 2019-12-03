package br.well.tembici.ui.repository.view.adapter.model

class RepositoryItemAdapter(
    val userName: String,
    val userNickname: String,
    val userImage: String,
    val repository: String,
    val repositoryDescription: String,
    val forkCount: Int,
    val pullRequestCount: Int
)
