package com.klemer.githubrepos.repositories

import android.content.Context
import com.klemer.githubrepos.BuildConfig
import com.klemer.githubrepos.database.AppDatabase
import com.klemer.githubrepos.endpoints.GithubEndpoints
import com.klemer.githubrepos.models.GithubLanguages
import com.klemer.githubrepos.models.Repository
import com.klemer.githubrepos.models.RepositoryResponse
import com.klemer.githubrepos.services.RetrofitService
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepository {

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

    fun getRepositories(callback: (RepositoryResponse) -> Unit) {
        val api = RetrofitService().getInstance(BuildConfig.GITHUB_API_URL)
            .create(GithubEndpoints::class.java)

        api.getRepositories("language:Kotlin").enqueue(object : Callback<RepositoryResponse> {
            override fun onResponse(
                call: Call<RepositoryResponse>,
                response: Response<RepositoryResponse>
            ) {
                response.body()?.let { callback(it) }
            }

            override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                println(t.localizedMessage)
            }

        })
    }

}