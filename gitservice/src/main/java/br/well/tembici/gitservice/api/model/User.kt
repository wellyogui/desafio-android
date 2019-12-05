package br.well.tembici.gitservice.api.model

import com.google.gson.annotations.SerializedName

class User(@SerializedName("login")
           val userName: String,
           @SerializedName("avatar_url")
           val userImage: String) {

}
