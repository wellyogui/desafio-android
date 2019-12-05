package br.well.tembici.gitservice.api.model

import com.google.gson.annotations.SerializedName

data class PullRequest(
    @SerializedName("body")
    val description: String,
    val created_at: String,
    val id: Int,
    val number: Int,
    val title: String,
    val html_url: String,
    val user: User
)