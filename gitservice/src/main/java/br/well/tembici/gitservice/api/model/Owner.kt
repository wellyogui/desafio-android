package br.well.tembici.gitservice.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by well_ on 03/12/2019 for tembici-challenge.
 */

@Parcelize
data class Owner(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val id: Int,
    val login: String
) : Parcelable