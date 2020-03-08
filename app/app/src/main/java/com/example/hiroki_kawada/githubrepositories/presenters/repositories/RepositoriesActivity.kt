package com.example.hiroki_kawada.githubrepositories.presenters.repositories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.hiroki_kawada.githubrepositories.R
import com.example.hiroki_kawada.githubrepositories.databinding.RepositoriesActivityBinding
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * リポジトリActivity
 */
class RepositoriesActivity : AppCompatActivity() {

    private val repositoriesViewModel: RepositoriesViewModel by viewModel()

    private val binding: RepositoriesActivityBinding by lazy {
        DataBindingUtil.inflate<RepositoriesActivityBinding>(
            layoutInflater,
            R.layout.repositories_activity,
            null,
            false
        ).also {
            it.lifecycleOwner = this
            it.viewModel = repositoriesViewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    RepositoriesFragment.newInstance()
                )
                .commitNow()
        }
    }
}
