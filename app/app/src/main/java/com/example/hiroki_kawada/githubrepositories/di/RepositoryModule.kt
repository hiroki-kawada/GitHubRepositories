package com.example.hiroki_kawada.githubrepositories.di

import com.example.hiroki_kawada.githubrepositories.model.repository.GitHubApiRepository
import com.example.hiroki_kawada.githubrepositories.model.repository.IGitHubApiRepository
import org.koin.dsl.module

/**
 * リポジトリーモジュール
 */
val repositoryModule = module {
    single<IGitHubApiRepository>(override = true) { GitHubApiRepository(get()) }
}