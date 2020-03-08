package com.example.hiroki_kawada.githubrepositories.model.repository

import com.example.hiroki_kawada.githubrepositories.model.response.ErrorResponse
import com.example.hiroki_kawada.githubrepositories.model.response.GitHubRepositoriesResponse
import com.example.hiroki_kawada.githubrepositories.model.response.GithubReadme
import com.example.hiroki_kawada.githubrepositories.model.response.Response
import com.example.hiroki_kawada.githubrepositories.model.service.GitHubApiService
import retrofit2.await
import java.net.SocketTimeoutException

interface IGitHubApiRepository {
    suspend fun getGitHubList(): Response<List<GitHubRepositoriesResponse>>
    suspend fun getReadme(name: String): Response<GithubReadme>
}

/**
 * GitHubApiリポジトリ
 */
class GitHubApiRepository(
    private val gitHubApiService: GitHubApiService
) : IGitHubApiRepository {
    override suspend fun getGitHubList(): Response<List<GitHubRepositoriesResponse>> {
        return try {
            val response = gitHubApiService.getGitHubRepositories("andfactory").await()
            Response.Success(response)
        } catch (t: Throwable) {
            if (t is SocketTimeoutException) {
                Response.Failure(ErrorResponse.createTimeoutError())
            } else {
                Response.Failure(ErrorResponse.createNetworkError())
            }
        }
    }

    override suspend fun getReadme(name: String): Response<GithubReadme> {
        return try {
            val response = gitHubApiService.getReadme("andfactory", name).await()
            Response.Success(response)
        } catch (t: Throwable) {
            if (t is SocketTimeoutException) {
                Response.Failure(ErrorResponse.createTimeoutError())
            } else {
                Response.Failure(ErrorResponse.createNetworkError())
            }
        }
    }

}