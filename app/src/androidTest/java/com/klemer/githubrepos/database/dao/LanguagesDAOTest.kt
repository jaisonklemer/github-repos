package com.klemer.githubrepos.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.klemer.githubrepos.database.AppDatabase
import com.klemer.githubrepos.models.GithubLanguages
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LanguagesDAOTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: LanguagesDAO


    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.languageDAO()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insert_language_returns_true() {
        val language1 = GithubLanguages(1, "JavaScript", "#FFFF")
        val language2 = GithubLanguages(2, "Kotlin", "#FFFF")
        val language3 = GithubLanguages(3, "Java", "#FFFF")

        val listToInsert = arrayListOf(language1, language2, language3)
        dao.insert(listToInsert)

        val results = dao.getAll()
        assertThat(results).containsExactlyElementsIn(listToInsert)
    }

    @Test
    fun get_all_language_returns_one() {
        val language1 = GithubLanguages(1, "JavaScript", "#FFFF")


        val listToInsert = arrayListOf(language1)

        dao.insert(listToInsert)

        val results = dao.getAll()
        assertThat(results.size).isEqualTo(1)
    }
}