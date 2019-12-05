package br.well.tembici.gitservice.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */

@Parcelize
data class Repo(
    val description: String,
    @SerializedName("forks_count")
    val forksCount: Int,
    val name: String,
    val id: Int,
    val owner: Owner,
    @SerializedName("stargazers_count")
    val starsCount: Int
) : Parcelable