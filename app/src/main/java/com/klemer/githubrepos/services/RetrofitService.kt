package com.klemer.githubrepos.services

import com.klemer.githubrepos.BuildConfig
import com.klemer.githubrepos.endpoints.GithubEndpoints
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    fun getInstance(path: String): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(path)
            .build()
    }

    fun getGithubServices(): GithubEndpoints =
        getInstance(BuildConfig.GITHUB_API_URL).create(GithubEndpoints::class.java)
}