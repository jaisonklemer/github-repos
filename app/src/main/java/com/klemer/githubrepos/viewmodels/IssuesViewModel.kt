package com.klemer.githubrepos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.githubrepos.models.RepoInfoModel
import com.klemer.githubrepos.repositories.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    val issuesList = MutableLiveData<List<RepoInfoModel>>()

    fun getIssues(userName: String, repoName: String) {
        repository.getIssues(userName, repoName) {
            issuesList.value = it
        }
    }
}