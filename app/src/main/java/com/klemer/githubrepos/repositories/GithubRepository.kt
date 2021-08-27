package com.klemer.githubrepos.repositories

import android.content.Context
import com.klemer.githubrepos.BuildConfig
import com.klemer.githubrepos.database.AppDatabase
import com.klemer.githubrepos.endpoints.GithubEndpoints
import com.klemer.githubrepos.models.GithubLanguages
import com.klemer.githubrepos.services.RetrofitService
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepository {

//    private val api = RetrofitService().getInstance("")

    fun getAllLanguages(callback: (List<GithubLanguages>) -> Unit) {
        val api = RetrofitService().getInstance(BuildConfig.GITHUB_LANGS_URL)
            .create(GithubEndpoints::class.java)

        api.getAllLanguages().enqueue(object : Callback<List<GithubLanguages>> {
            override fun onResponse(
                call: Call<List<GithubLanguages>>,
                response: Response<List<GithubLanguages>>
            ) {
                response.body()?.let {
                    callback(it)
                }
            }

            override fun onFailure(call: Call<List<GithubLanguages>>, t: Throwable) {
                println(t.localizedMessage)
            }

        })
    }

    fun insertLanguageIntoDB(context: Context, languages: List<GithubLanguages>) {
        val dao = AppDatabase.getDatabase(context).languageDAO()

        dao.insert(languages)
    }

    fun getAllLanguages(context: Context): List<GithubLanguages> {
        val dao = AppDatabase.getDatabase(context).languageDAO()
        return dao.getAll()
    }

}