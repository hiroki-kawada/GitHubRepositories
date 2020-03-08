package com.example.hiroki_kawada.githubrepositories.presenters.readme

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.webkit.WebView
import androidx.fragment.app.DialogFragment
import com.example.hiroki_kawada.githubrepositories.R
import com.example.hiroki_kawada.githubrepositories.presenters.repositories.RepositoriesViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * ReadMeを表示するDialogFragment
 */
class ReadmeFragment : DialogFragment() {

    private val repositoriesViewModel: RepositoriesViewModel by sharedViewModel()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        val inflater = activity!!.layoutInflater
        val readme = inflater.inflate(R.layout.readme_fragment, null)
        builder.setView(readme)
        val a = readme.findViewById<WebView>(R.id.readme_view)
        a.loadUrl(repositoriesViewModel.htmlUrl)
        return builder.create()
    }

}