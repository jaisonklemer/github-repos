package com.klemer.githubrepos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.githubrepos.models.GithubLanguages
import com.klemer.githubrepos.models.RepositoryResponse
import com.klemer.githubrepos.repositories.GithubRepository
import com.klemer.githubrepos.singletons.APICount
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    val repositoriesList = MutableLiveData<RepositoryResponse>()

    var languagesList = MutableLiveData<List<GithubLanguages>>()


    fun getRepositories(language: String = "Kotlin") {
        repository.getRepositories(language) {
            repositoriesList.value = it
            APICount.page++
        }
    }

    fun getGithubLangs() {
        repository.getAllLanguages { gitLangs ->
            languagesList.value = gitLangs
            repository.insertLanguageIntoDB(gitLangs)
        }
    }

    fun fetchAllLangsFromDB() {
        val languages = repository.getAllLanguages()

        if (languages.isNotEmpty()) {
            languagesList.value = languages
        } else {
            getGithubLangs()
        }

    }

}