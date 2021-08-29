package com.klemer.githubrepos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.githubrepos.models.RepositoryResponse
import com.klemer.githubrepos.repositories.GithubRepository

class MainViewModel : ViewModel() {

    val repository = GithubRepository()

    val repositoriesList = MutableLiveData<RepositoryResponse>()


    fun getRepositories() {
        repository.getRepositories {
            repositoriesList.value = it
        }
    }

}