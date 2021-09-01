package com.klemer.githubrepos.interfaces

import com.klemer.githubrepos.models.RepoInfoModel
import com.klemer.githubrepos.models.Repository

interface RepositoryClickListener {
    fun onRepositoryItemClick(repository: Repository)
}