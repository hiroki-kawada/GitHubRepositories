package com.example.hiroki_kawada.githubrepositories

import android.app.Application
import com.example.hiroki_kawada.githubrepositories.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * アプリケーションクラス
 */
class GitHubRepositoriesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GitHubRepositoriesApplication)
            modules(appModules)
        }
    }

}