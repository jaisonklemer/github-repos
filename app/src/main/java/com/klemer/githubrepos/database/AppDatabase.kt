package com.klemer.githubrepos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.klemer.githubrepos.database.dao.LanguagesDAO
import com.klemer.githubrepos.models.GithubLanguages

@Database(entities = [GithubLanguages::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun languageDAO() : LanguagesDAO

    companion object {

        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "github_repos__db"
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}