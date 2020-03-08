package com.example.hiroki_kawada.githubrepositories.presenters.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hiroki_kawada.githubrepositories.R
import com.example.hiroki_kawada.githubrepositories.databinding.RepositoriesFragmentBinding
import com.example.hiroki_kawada.githubrepositories.presenters.readme.ReadmeFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * Repository一覧を表示するFragment
 */
class RepositoriesFragment : Fragment(), ClickListener {

    companion object {
        fun newInstance() =
            RepositoriesFragment()
    }

    private val controler by lazy { RepositoriesEpoxyController(this) }

    private val repositoriesViewModel: RepositoriesViewModel by sharedViewModel()

    private val binding: RepositoriesFragmentBinding by lazy {
        DataBindingUtil.inflate<RepositoriesFragmentBinding>(
            layoutInflater,
            R.layout.repositories_fragment,
            null,
            false
        ).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = repositoriesViewModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.repositoryList.let {
            it.adapter = controler.adapter
            it.layoutManager = LinearLayoutManager(this.activity, RecyclerView.VERTICAL, false)
            it.addItemDecoration(
                DividerItemDecoration(
                    this.activity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        repositoriesViewModel.listItems.observe(viewLifecycleOwner, Observer {
            setList()
        })
        repositoriesViewModel.oneTimeEvent.observe(viewLifecycleOwner, Observer {
            when (it) {
                is OneTimeEvent.ShowSnackBar -> {
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                }
                is OneTimeEvent.ShowReadMeDialog -> {
                    val readmeFragment = ReadmeFragment()
                    readmeFragment.show(childFragmentManager, "")
                }
            }
        })
        repositoriesViewModel.tapGetListButton()
    }

    override fun itemClickListener(item: String) {
        repositoriesViewModel.tapListItem(item)
    }

    private fun setList() {
        controler.setData(repositoriesViewModel.listItems.value)
    }

}
