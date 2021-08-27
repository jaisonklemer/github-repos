package com.klemer.githubrepos.view.fragments

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.githubrepos.models.GithubLanguages
import com.klemer.githubrepos.repositories.GithubRepository

class FiltersDialogViewModel : ViewModel() {
    var languagesList = MutableLiveData<List<GithubLanguages>>()

    private val repository = GithubRepository()

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