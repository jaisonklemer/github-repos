package com.klemer.githubrepos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.githubrepos.models.RepoInfoModel
import com.klemer.githubrepos.repositories.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PullRequestsViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    val pullRequestList = MutableLiveData<List<RepoInfoModel>>()

    fun getPullRequests(userName: String, repoName: String) {
        repository.getPullRequests(userName, repoName) {
            pullRequestList.value = it
        }
    }
}