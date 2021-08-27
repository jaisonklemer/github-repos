package com.klemer.githubrepos.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.klemer.githubrepos.models.GithubLanguages

@Dao
interface LanguagesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(languages: List<GithubLanguages>)

    @Query("SELECT * FROM GithubLanguages")
    fun getAll() : List<GithubLanguages>
}