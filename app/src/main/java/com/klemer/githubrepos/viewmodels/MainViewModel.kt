package com.klemer.githubrepos.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.githubrepos.models.GithubLanguages
import com.klemer.githubrepos.models.RepositoryResponse
import com.klemer.githubrepos.repositories.GithubRepository
import com.klemer.githubrepos.singletons.APICount

class MainViewModel : ViewModel() {

    val repository = GithubRepository()

    val repositoriesList = MutableLiveData<RepositoryResponse>()

    var languagesList = MutableLiveData<List<GithubLanguages>>()


    fun getRepositories(language: String = "Kotlin") {
        repository.getRepositories(language) {
            repositoriesList.value = it
            APICount.page++
        }
    }

    fun getGithubLangs(context: Context) {
        repository.getAllLanguages { gitLangs ->
            languagesList.value = gitLangs
            repository.insertLanguageIntoDB(context, gitLangs)
        }
    }

    fun fetchAllLangsFromDB(context: Context) {
        val languages = repository.getAllLanguages(context)

        if (languages.isNotEmpty()) {
            languagesList.value = languages
        } else {
            getGithubLangs(context)
        }

    }

}