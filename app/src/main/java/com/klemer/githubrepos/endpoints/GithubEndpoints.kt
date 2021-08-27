package com.klemer.githubrepos.endpoints

import com.klemer.githubrepos.models.GithubLanguages
import retrofit2.Call
import retrofit2.http.GET

interface GithubEndpoints {

    @GET("jaisonklemer/419b11e5ee94d7b01760693c2410feb3/raw/4a5d8097ad9f252d14747f686921470fb802db0d/githublanguages.json")
    fun getAllLanguages() : Call<List<GithubLanguages>>
}