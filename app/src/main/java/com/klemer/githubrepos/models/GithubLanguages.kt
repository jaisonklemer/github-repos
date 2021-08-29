package com.klemer.githubrepos.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GithubLanguages(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("color")
    val color: String? = "#F69E6A"
){
    override fun toString(): String {
        return name
    }
}