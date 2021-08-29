package com.klemer.githubrepos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.githubrepos.models.RepoInfoModel
import com.klemer.githubrepos.repositories.GithubRepository

class PullRequestsViewModel : ViewModel() {

    private val repository = GithubRepository()

    val pullRequestList = MutableLiveData<List<RepoInfoModel>>()

    fun getPullRequests(userName: String, repoName: String) {
        repository.getPullRequests(userName, repoName) {
            pullRequestList.value = it
        }
    }
}