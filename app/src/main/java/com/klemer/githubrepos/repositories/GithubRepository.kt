package com.klemer.githubrepos.repositories

import com.klemer.githubrepos.database.dao.LanguagesDAO
import com.klemer.githubrepos.endpoints.GithubEndpoints
import com.klemer.githubrepos.models.GithubLanguages
import com.klemer.githubrepos.models.RepoInfoModel
import com.klemer.githubrepos.models.RepositoryResponse
import com.klemer.githubrepos.singletons.APICount
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val api: GithubEndpoints,
    private val dao: LanguagesDAO

) {

    fun getAllLanguages(callback: (List<GithubLanguages>) -> Unit) {
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

    fun insertLanguageIntoDB(languages: List<GithubLanguages>) {
        dao.insert(languages)
    }

    fun getAllLanguages(): List<GithubLanguages> {
        return dao.getAll()
    }

    fun getRepositories(language: String, callback: (RepositoryResponse) -> Unit) {

        api.getRepositories("language:${language}", APICount.page)
            .enqueue(object : Callback<RepositoryResponse> {
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

    fun getPullRequests(
        gitUser: String,
        repositoryName: String,
        callback: (List<RepoInfoModel>) -> Unit
    ) {
        api.getPullRequests(gitUser, repositoryName)
            .enqueue(object : Callback<List<RepoInfoModel>> {
                override fun onResponse(
                    call: Call<List<RepoInfoModel>>,
                    response: Response<List<RepoInfoModel>>
                ) {
                    response.body()?.let { callback(it) }
                }

                override fun onFailure(call: Call<List<RepoInfoModel>>, t: Throwable) {
                    println(t.localizedMessage)
                }

            })
    }

    fun getIssues(
        gitUser: String,
        repositoryName: String,
        callback: (List<RepoInfoModel>) -> Unit
    ) {

        api.getIssues(gitUser, repositoryName)
            .enqueue(object : Callback<List<RepoInfoModel>> {
                override fun onResponse(
                    call: Call<List<RepoInfoModel>>,
                    response: Response<List<RepoInfoModel>>
                ) {
                    response.body()?.let { callback(it) }
                }

                override fun onFailure(call: Call<List<RepoInfoModel>>, t: Throwable) {
                    println(t.localizedMessage)
                }

            })
    }

}