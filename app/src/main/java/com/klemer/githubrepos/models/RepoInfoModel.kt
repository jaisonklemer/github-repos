package com.klemer.githubrepos.models

import com.google.gson.annotations.SerializedName

data class RepoInfoModel(

    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val description: String,

    @SerializedName("html_url")
    val url: String,

    @SerializedName("user")
    val user: User
)
