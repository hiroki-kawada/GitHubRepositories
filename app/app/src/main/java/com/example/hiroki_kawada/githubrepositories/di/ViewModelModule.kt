package com.example.hiroki_kawada.githubrepositories.di

import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
import com.example.hiroki_kawada.githubrepositories.presenters.repositories.RepositoriesViewModel

/**
 * ビューモデルモジュール作成
 */
val viewModelModule = module {
    viewModel { RepositoriesViewModel(get()) }
}