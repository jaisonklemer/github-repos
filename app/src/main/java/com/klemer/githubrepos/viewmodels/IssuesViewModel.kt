package com.klemer.githubrepos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.githubrepos.models.RepoInfoModel
import com.klemer.githubrepos.repositories.GithubRepository

class IssuesViewModel : ViewModel() {
    private val repository = GithubRepository()

    val issuesList = MutableLiveData<List<RepoInfoModel>>()

    fun getIssues(userName: String, repoName: String) {
        repository.getIssues(userName, repoName) {
            issuesList.value = it
        }
    }
}