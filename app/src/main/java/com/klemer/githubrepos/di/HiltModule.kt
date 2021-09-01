package com.klemer.githubrepos.di

import android.content.Context
import com.klemer.githubrepos.database.AppDatabase
import com.klemer.githubrepos.database.dao.LanguagesDAO
import com.klemer.githubrepos.endpoints.GithubEndpoints
import com.klemer.githubrepos.repositories.GithubRepository
import com.klemer.githubrepos.services.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideGithubRepository(service: GithubEndpoints, dao: LanguagesDAO): GithubRepository =
        GithubRepository(service, dao)

    @Provides
    fun provideGithuServices(): GithubEndpoints = RetrofitService().getGithubServices()

    @Provides
    fun provideGithubDao(@ApplicationContext context: Context): LanguagesDAO {
        return AppDatabase.getDatabase(context).languageDAO()
    }
}