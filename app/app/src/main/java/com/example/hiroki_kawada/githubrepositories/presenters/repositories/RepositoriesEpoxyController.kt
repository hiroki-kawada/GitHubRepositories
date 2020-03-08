package com.example.hiroki_kawada.githubrepositories.presenters.repositories

import com.airbnb.epoxy.TypedEpoxyController
import com.example.hiroki_kawada.githubrepositories.RepositoryCellBindingModel_

/**
 * RecyclerViewのController
 */
class RepositoriesEpoxyController(private val callback: ClickListener) :
    TypedEpoxyController<List<String>>() {

    override fun buildModels(data: List<String>?) {
        val Repositories = data ?: return
        Repositories.forEach {
            RepositoryCellBindingModel_()
                .text(it)
                .itemClickListener { _, _, _, _ ->
                    callback.itemClickListener(it)
                }
                .id(modelCountBuiltSoFar)
                .addTo(this)
        }
    }

}