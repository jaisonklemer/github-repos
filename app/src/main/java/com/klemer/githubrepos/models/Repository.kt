package com.klemer.githubrepos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepositoryResponse(
    @SerializedName("items")
    val repositories: List<Repository>
)

data class Repository(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("stargazers_count")
    val starsCount: Int,

    @SerializedName("forks_count")
    val forksCount: Int,

    @SerializedName("description")
    val description: String,

    @SerializedName("owner")
    val user: User,

    @SerializedName("language")
    val language: String
) : Serializable

data class User(
    @SerializedName("avatar_url")
    val avatar: String,

    @SerializedName("login")
    val name: String
)
