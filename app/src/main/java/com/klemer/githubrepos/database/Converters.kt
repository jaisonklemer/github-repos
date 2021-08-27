package com.klemer.githubrepos.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.klemer.githubrepos.models.GithubLanguages

class Converters {
    @TypeConverter
    fun langsListToJson(value: List<GithubLanguages>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToLangsList(value: String) =
        Gson().fromJson(value, Array<GithubLanguages>::class.java).toList()

}