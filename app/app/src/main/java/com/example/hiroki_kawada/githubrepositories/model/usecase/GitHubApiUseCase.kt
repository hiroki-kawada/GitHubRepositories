package com.example.hiroki_kawada.githubrepositories.model.usecase

import android.provider.Settings.Global.getString
import com.example.hiroki_kawada.githubrepositories.R
import com.example.hiroki_kawada.githubrepositories.model.entity.RepositoryListItemData
import com.example.hiroki_kawada.githubrepositories.model.repository.IGitHubApiRepository
import com.example.hiroki_kawada.githubrepositories.model.response.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * GitHubApi関連ユースケース
 */
class GitHubApiUseCase(
    private val iGitHubRepository: IGitHubApiRepository
) {
    suspend fun getGitHubList(): UseCaseResult<MutableList<RepositoryListItemData>, String> =
        withContext(Dispatchers.IO) {
            return@withContext when (val response = iGitHubRepository.getGitHubList()) {
                is Response.Success -> {
                    val list: MutableList<RepositoryListItemData> = mutableListOf()
                    response.value.forEach {
                        val itemData = RepositoryListItemData(it.name)
                        list.add(itemData)
                    }
                    UseCaseResult.Success<MutableList<RepositoryListItemData>, String>(list)
                }
                is Response.Failure -> {
                    if (response.value.code == "504") {
                        UseCaseResult.Failure<MutableList<RepositoryListItemData>, String>(response.value.errorMassage)
                    } else {
                        UseCaseResult.Failure<MutableList<RepositoryListItemData>, String>(response.value.errorMassage)
                    }
                }
            }
        }


    suspend fun getGiHubList(string: String): UseCaseResult<String, String> =
        withContext(Dispatchers.IO) {
            return@withContext when (val response = iGitHubRepository.getReadme(string)) {
                is Response.Success -> {
                    UseCaseResult.Success<String, String>(response.value.htmlUrl)
                }
                is Response.Failure -> {
                    if (response.value.code == "504") {
                        UseCaseResult.Failure<String, String>(response.value.errorMassage)
                    } else {
                        UseCaseResult.Failure<String, String>(response.value.errorMassage)
                    }
                }
            }
        }


}