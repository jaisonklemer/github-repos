package com.klemer.githubrepos.interfaces

import com.klemer.githubrepos.models.RepoInfoModel

interface IssueOrPullRequestClickListener {

    fun onItemClick(item: RepoInfoModel)
}