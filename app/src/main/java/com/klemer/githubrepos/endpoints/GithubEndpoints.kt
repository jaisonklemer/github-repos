package com.klemer.githubrepos.endpoints

import com.klemer.githubrepos.models.GithubLanguages
import com.klemer.githubrepos.models.Repository
import com.klemer.githubrepos.models.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubEndpoints {

    @GET("jaisonklemer/2a8243e7e8dee14508830f07bb59f55a/raw/ae0c28779a705291a2df29e9e6dc0b64e297bc58/gitlangs.json")
    fun getAllLanguages(): Call<List<GithubLanguages>>

    @GET("search/repositories")
    fun getRepositories(
        @Query("q") language: String,
        @Query("page") page: Int
    ): Call<RepositoryResponse>
}