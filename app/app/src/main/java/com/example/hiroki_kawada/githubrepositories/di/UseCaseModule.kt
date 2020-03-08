package com.example.hiroki_kawada.githubrepositories.di

import com.example.hiroki_kawada.githubrepositories.model.usecase.GitHubApiUseCase
import org.koin.dsl.module

/**
 * ユースケースモジュール
 */
val useCaseModule = module {
    single { GitHubApiUseCase(get()) }
}