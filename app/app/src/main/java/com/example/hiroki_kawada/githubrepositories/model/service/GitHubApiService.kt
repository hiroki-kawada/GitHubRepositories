package com.example.hiroki_kawada.githubrepositories.model.service

import com.example.hiroki_kawada.githubrepositories.model.response.GitHubRepositoriesResponse
import com.example.hiroki_kawada.githubrepositories.model.response.GithubReadme
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * GitHubApiサービスクラス
 */
interface GitHubApiService {
    @GET("users/{user}/repos")
    fun getGitHubRepositories(@Path("user") user: String): Call<List<GitHubRepositoriesResponse>>

    @GET("repos/{user}/{name}/readme")
    fun getReadme(@Path("user") user: String, @Path("name") name: String): Call<GithubReadme>
}