package br.well.tembici.ui.pullrequest.view.adapter.model

data class PullRequestItemAdapter(
    val userName: String,
    val userImage: String,
    val pullRequestTitle: String,
    val pullRequestDescription: String,
    val pullRequestDate: String,
    val pullRequestUrl: String
) {

}
